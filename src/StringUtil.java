import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * The Class StringUtil.
 */
public class StringUtil {
	/**
	 * Apply SHA.
	 *
	 * @param input the input
	 * @return the string
	 */

	public static String applySHA(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// Applies sha256 to our input,
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Apply ECDSA signature.
	 *
	 * @param key the key
	 * @param input the input
	 * @return the byte[]
	 */
	public static byte[] applyECDSASig(PrivateKey key, String input) {
		Signature dsa;
		byte[] output = new byte[0];

		try {
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(key);
			byte[] strbyte = input.getBytes();
			dsa.update(strbyte);
			byte[] realsig = dsa.sign();
			output = realsig;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}
	
	/**
	 * Verify signature.
	 *
	 * @param publicKey the public key
	 * @param data the data
	 * @param signature the signature
	 * @return true, if successful
	 */
	public static boolean verifySig(PublicKey publicKey, String data, byte[] signature) {
		try {
			Signature ecdsaverify = Signature.getInstance("ECDSA","BC");
			ecdsaverify.initVerify(publicKey);
			ecdsaverify.update(data.getBytes());
			return ecdsaverify.verify(signature);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the string from key.
	 *
	 * @param key the key
	 * @return the string from key
	 */
	public static String getStringFromKey(PublicKey key) {
		return key.toString();
	}
	
	public static String getStringFromKey(PrivateKey key) {
		return key.toString();
	}
}
