
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
	
	private int nounce;

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
		String calhash = StringUtil.applySHA(prevHash + data + Long.toString(timeStamp) + Integer.toString(nounce));
		return calhash;
	}
	
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		while(!Hash.substring(0,difficulty).equals(target)) {
			nounce++;
			Hash = calHash();
		}
		
		System.out.println("BlockMined "+Hash);
	}

}
