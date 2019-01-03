package br.com.projetoreativo.pos.buscamelhorcaminho.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.projetoreativo.pos.buscamelhorcaminho.repository.Blocks;
import br.com.projetoreativo.pos.buscamelhorcaminho.util.StringUtil;

public class ManagerBlock {

	private String previousHash;
	private String data;
	public String hash;
	private int nonce;

	@Autowired
	private Blocks blocks;

	public ManagerBlock() {

	}

	// Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil
				.applySha256(previousHash + Long.toString(new Date().getTime()) + Integer.toString(nonce) + data);
		return calculatedhash;
	}

	// Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); // Create a
																	// string
																	// with
																	// difficulty
																	// * "0"
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}

	public Block save(Block block, final int difficulty) {

		previousHash = ((Block) blocks.getOne(block.getId() - 1)).getHash();

		previousHash = (previousHash == "") ? "0" : previousHash;

		block.setPreviousHash(previousHash);
		
		data = block.getData();
		
		mineBlock(difficulty);

		if (hash != null) {
			block.setHash(hash);
		}

		return blocks.save(block);
	}

	public List<Block> listar() {
		return blocks.findAll();
	}
	
//	public static Boolean isChainValid() {
//		Block currentBlock; 
//		Block previousBlock;
//		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
//		
//		//loop through blockchain to check hashes:
//		for(int i=1; i < blockchain.size(); i++) {
//			currentBlock = blockchain.get(i);
//			previousBlock = blockchain.get(i-1);
//			//compare registered hash and calculated hash:
//			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
//				System.out.println("Current Hashes not equal");			
//				return false;
//			}
//			//compare previous hash and registered previous hash
//			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
//				System.out.println("Previous Hashes not equal");
//				return false;
//			}
//			//check if hash is solved
//			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
//				System.out.println("This block hasn't been mined");
//				return false;
//			}
//			
//		}
//		return true;
//	}
}