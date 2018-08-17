import java.util.Date;

public class Block {

	public String Hash;
	public String prevHash;
	private String data;
	private long timeStamp;
	
	public Block(String data, String prevHash) {
		this.data = data;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
	}
	
	
}
