
/*
 * 
 * @author Ayush Singhania
 * 
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Block.
 */
public class Block {

	/** The Hash. */
	public String Hash;

	/** The prev hash. */
	public String prevHash;

	/** The data. */
	private String data;

	/** The time stamp. */
	private long timeStamp;

	/**
	 * Instantiates a new block.
	 *
	 * @param data     the data
	 * @param prevHash the prev hash
	 */
	public Block(String data, String prevHash) {
		this.data = data;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
		this.Hash = calHash();
	}

	
	/**
	 * Cal hash.
	 *
	 * @return the string
	 */
	public String calHash() {
		String calhash = applySHA(prevHash + data + Long.toString(timeStamp));
		return calhash;
	}
	
	
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

}
