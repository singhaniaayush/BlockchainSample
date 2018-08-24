import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
	public PrivateKey privatekey;
	public PublicKey publickey;

	public HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

	/**
	 * Instantiates a new wallet.
	 */
	public Wallet() {
		generateKeyPair();
	}

	/**
	 * Generate key pair.
	 */
	public void generateKeyPair() {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecspec = new ECGenParameterSpec("prime192v1");

			keygen.initialize(ecspec, random);
			KeyPair keyPair = keygen.generateKeyPair();
			privatekey = keyPair.getPrivate();
			publickey = keyPair.getPublic();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public float getBalance() {
		float total = 0;

		for (Map.Entry<String, TransactionOutput> item : BlockChain.UTXOs.entrySet()) {
			TransactionOutput UTXO = item.getValue();
			if (UTXO.isMine(publickey)) {
				UTXOs.put(UTXO.id, UTXO);
				total += UTXO.value;
			}
		}

		return total;
	}
	
	
	public Transaction sendFunds(PublicKey _receipient, float value) {
		
		if(getBalance() < value) {
			System.out.println("Not enough funds for transaction / Insuffiecient balance");
			return null;
		}
		
		ArrayList<TransactionInput> input = new ArrayList<TransactionInput>();
		
		float total = 0;
		for(Map.Entry<String, TransactionOutput> item : UTXOs.entrySet()) {
			TransactionOutput UTXO = item.getValue();
			total += UTXO.value;
			input.add(new TransactionInput(UTXO.id));
			if(total > value) break;
		}
		
		Transaction newTransaction = new Transaction(publickey, _receipient, value, input);
		newTransaction.generateSignature(privatekey);
		
		for(TransactionInput i : input) {
			UTXOs.remove(i.transactionOutputId);
		}
		
		return newTransaction;
	}
}
