package br.com.projetoreativo.pos.buscamelhorcaminho.model;

import java.util.Date;
import java.util.List;

import br.com.projetoreativo.pos.buscamelhorcaminho.util.StringUtil;

public class ManagerBlock {

//	private String previousHash;
//	private String data;
//	public String hash;
	private int nonce;

	public ManagerBlock() {

	}

	// Calculate new hash based on blocks contents
	public String calculateHash(Block block) {
		
		block.setDateTime(new Date().getTime());
		String calculatedhash = StringUtil
				.applySha256(block.getPreviousHash() + Long.toString(block.getDateTime()) + Integer.toString(nonce) + block.getData());
		return calculatedhash;
	}

	public void mineBlock(Block block, int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); // Create a
																	// string
																	// with
																	// difficulty
																	// * "0"
		String hash = block.getHash();
		
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash(block);
		}
		
		block.setHash(hash);
		System.out.println("Block Mined!!! : " + hash);
	}

	public Block save(Block block, final int difficulty) {

		block.setHash(calculateHash(block)); 
		mineBlock(block, difficulty);

		return block;
	}

	
	public Boolean isChainValid(List<Block> blockchain, int difficulty) {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.getHash().equals(calculateHash(currentBlock)) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.getHash().equals(currentBlock.getPreviousHash()) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.getHash().substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
}
