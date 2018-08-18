/**
 * The Class BlockChain.
 */

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class BlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Normal blocks
		
		/*Block genesis = new Block("Genesis Block","0");
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
		*/
		
		//ArrayList blocks
		blockchain.add(new Block("Genesis Block","0"));
		blockchain.add(new Block("First Block", blockchain.get(blockchain.size()-1).Hash));
		blockchain.add(new Block("Second Block", blockchain.get(blockchain.size()-1).Hash));
		
		//Displaying in JSON format
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);
		

	}

}
