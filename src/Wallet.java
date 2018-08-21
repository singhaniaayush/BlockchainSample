import java.security.*;
import java.security.spec.ECGenParameterSpec;


public class Wallet {
	public PrivateKey privatekey;
	public PublicKey publickey;
	
	public Wallet() {
		generateKeyPair();
	}
	
	public void generateKeyPair() {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("ECDSA","BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecspec = new ECGenParameterSpec("prime192v1");
			
			keygen.initialize(ecspec,random);
			KeyPair keyPair = keygen.generateKeyPair();
			privatekey = keyPair.getPrivate();
			publickey = keyPair.getPublic();
			
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
