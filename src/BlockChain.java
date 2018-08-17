
/**
 * The Class BlockChain.
 */
public class BlockChain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Block genesis = new Block("Genesis Block","0");
		Block first = new Block("First Block",genesis.Hash);
		Block second = new Block("Second Block",first.Hash);
		
		System.out.println("Genesis hash: " + genesis.Hash);
		System.out.println("Genesis prev hash: " + genesis.prevHash);
		System.out.println();
		System.out.println("First hash: " +first.Hash);
		System.out.println("First hash: " +first.prevHash);
		System.out.println();
		System.out.println("Second hash: " +second.Hash);
		System.out.println("Second hash: " +second.prevHash);
		
		

	}

}
