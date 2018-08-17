/*
 * 
 * @author Ayush Singhania
 * 
 */
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
	 * @param data the data
	 * @param prevHash the prev hash
	 */
	public Block(String data, String prevHash) {
		this.data = data;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
	}
	
	
}
