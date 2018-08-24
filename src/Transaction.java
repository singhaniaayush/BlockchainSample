import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
	public String transactionId;
	public PublicKey sender;
	public PublicKey reciepient;
	public float value;
	public byte[] signature;

	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

	private static int sequence = 0;

	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}

	/**
	 * Cal hash.
	 *
	 * @return the string
	 */
	private String calHash() {
		sequence++;
		return StringUtil.applySHA(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient)
				+ Float.toString(value) + sequence);
	}

	/**
	 * Generate signature.
	 *
	 * @param privatekey the privatekey
	 */
	public void generateSignature(PrivateKey privatekey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient)
				+ Float.toString(value);
		signature = StringUtil.applyECDSASig(privatekey, data);
	}

	/**
	 * Verify signature.
	 *
	 * @return true, if successful
	 */
	public boolean verifySignature() {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient)
				+ Float.toString(value);
		return StringUtil.verifySig(sender, data, signature);

	}
	
	
	/**
	 * Gets the inputs value.
	 *
	 * @return the inputs value
	 */
	public float getInputsValue() {
		float total = 0;
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) continue;
			total += i.UTXO.value;
		}
		return total;
	}
	
	
	/**
	 * Gets the outputs value.
	 *
	 * @return the outputs value
	 */
	public float getOutputsValue() {
		float total = 0;
		for(TransactionOutput o : outputs) {
			total += o.value;
		}
		return total;
	}
	
	
	/**
	 * Process transaction.
	 *
	 * @return true, if successful
	 */
	public boolean processTransaction() { 		
		
		if(verifySignature() == false){
			System.out.println("Transaction failed to verify");
			return false;
		}
		
		for(TransactionInput i : inputs) {
			i.UTXO = BlockChain.UTXOs.get(i.transactionOutputId);
		}
		
		//if(getInputsValue() < BlockChain.mi)
		
		float leftover = getInputsValue() - value;
		transactionId = calHash();
		
		outputs.add(new TransactionOutput(this.reciepient,value, transactionId));
		outputs.add(new TransactionOutput(this.sender, leftover, transactionId));
		
		for(TransactionOutput o : outputs) {
			BlockChain.UTXOs.put(o.id, o);
		}
		
		for(TransactionInput i : inputs) {
			if(i.UTXO==null) continue;
			BlockChain.UTXOs.remove(i.UTXO.id);
		}
		
		return true;
		
	}

}
