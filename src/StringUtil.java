import java.security.MessageDigest;
import java.security.PublicKey;

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
	
	public static String getStringFromKey(PublicKey key) {
		return key.toString();
	}
}
