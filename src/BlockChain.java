/**
 * The Class BlockChain.
 */

import java.security.Security;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class BlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	public static Wallet walletA;
	public static Wallet walletB;
	
	public static Boolean isChainValid() {
		
		Block currentBlock;
		Block prevBlock;
		
		for(int i=1; i<blockchain.size();i++) {
			
			currentBlock = blockchain.get(i);
			prevBlock = blockchain.get(i-1);
			
			if(!currentBlock.Hash.equals(currentBlock.calHash())) {
				System.out.println(i+" block hash is not correct ");
				return false;
			}
			
			if(!prevBlock.Hash.equals(currentBlock.prevHash)) {
				System.out.println(i+ " block prev hash is different for "+ (i-1) + " block");
				return false;
			}
			
		}
		
		
		return true;
	}
	
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
		
		//Setup Bouncey castle as a Security Provider
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		walletA = new Wallet();
		walletB = new Wallet();
		
		System.out.println("Public and private keys: ");
		System.out.println(StringUtil.getStringFromKey(walletA.privatekey));
		System.out.println(StringUtil.getStringFromKey(walletA.publickey));
		
		Transaction trans = new Transaction(walletA.publickey, walletB.publickey, 5, null);
		trans.generateSignature(walletA.privatekey);
		
		System.out.println("Is Sign verified:");
		System.out.println(trans.verifySignature());
		
//		
//		//ArrayList blocks
//		blockchain.add(new Block("Genesis Block","0"));
//		System.out.println("\n Trying to mine genesis block");
//		blockchain.get(0).mineBlock(difficulty);
//		
//		blockchain.add(new Block("First Block", blockchain.get(blockchain.size()-1).Hash));
//		System.out.println("\n Trying to mine first block");
//		blockchain.get(1).mineBlock(difficulty);
//		
//		blockchain.add(new Block("Second Block", blockchain.get(blockchain.size()-1).Hash));
//		System.out.println("\n Trying to mine second block");
//		blockchain.get(2).mineBlock(difficulty);
//		
//		System.out.println("\n Chain validity: "+ isChainValid());
//		
//		//Displaying in JSON format
//		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//		System.out.println("\nThe BlockChain");
//		System.out.println(blockchainJson);
//		
//
	}

}
