package br.com.projetoreativo.pos.buscamelhorcaminho.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoreativo.pos.buscamelhorcaminho.model.Block;
import br.com.projetoreativo.pos.buscamelhorcaminho.model.BlockList;
import br.com.projetoreativo.pos.buscamelhorcaminho.model.BlockBusiness;
import br.com.projetoreativo.pos.buscamelhorcaminho.repository.Blocks;
import br.com.projetoreativo.pos.buscamelhorcaminho.repository.BlockListRepository;



@RestController
@RequestMapping("/blockchair")
public class NoobChain {

	public static int difficulty = 3;

	private BlockBusiness managerBlock = new BlockBusiness();

	@Autowired
	private Blocks blocks;

	@Autowired
	private BlockListRepository blockListRepository;

	@PostMapping(path = "/valida", consumes = "application/json", produces = "application/json")
	public ResponseEntity valida(@RequestBody BlockList blocksList) {
		
		Optional<BlockList> block = blockListRepository.findById(blocksList.getId());
		boolean isValido = managerBlock.isChainValid(block.get().getBlocks(), difficulty);
		
		if(isValido)
			return ResponseEntity.ok(HttpStatus.OK);
		else
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity adiciona(@RequestBody List<Block> blocksList) {

		//int randow = 3;//(int) Math.floor(Math.random() * (3 - 1 + 1)) + 1;
		
		int randow = new Random().nextInt(10);
		
		Iterator<Block> blockIterator = blocksList.iterator();
		List<Block> blockList = new ArrayList<>();
		int count = 0;
		//int countBlockManager = 1;
		String previousHash = "0";
		
		while (blockIterator.hasNext()) {
			
			Block block = blockIterator.next();
			block.setPreviousHash(previousHash);
			
			block = managerBlock.save(block, difficulty);
			
			previousHash = block.getHash();
			
			blockList.add(block);
			count++;
			
			if(count == randow)
			{
				BlockList blockListPr = new BlockList();
				blockListPr.setNome("BLOCK_CHAIR");
				blockListPr.setBlocks(blockList);
				this.blockListRepository.save(blockListPr);
				randow = new Random().nextInt(10);
				count = 0;
				//countBlockManager++;
				previousHash = "0";
				blockList = new ArrayList<>();
			}
		}
		
		if(count != randow){
			BlockList blockListPr = new BlockList();
			blockListPr.setNome("BLOCK_CHAIR");
			blockListPr.setBlocks(blockList);
			this.blockListRepository.save(blockListPr);
		}
		
		
		return ResponseEntity.ok(HttpStatus.CREATED);
	}


	@GetMapping(path = "/")
	public List<BlockList> lista() {
		return blockListRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Optional<BlockList> obterBlockChair(@PathVariable Long id) {
		
		return blockListRepository.findById(id);
	}
	
	@DeleteMapping(path = "/")
	public void delete(){
		blockListRepository.deleteAll();
	}
	
	@PutMapping(path = "/{id}")
	public void atualiza(@PathVariable Long id, @Validated @RequestBody BlockList blocksList){
		
		blockListRepository.findById(blocksList.getId());
	}
	
//	@PutMapping(path = "/blockchair/{id}")
//	public void atualiza(@RequestBody bockChair, @PathVariable Long id)
//	{
//		//blocks.;
//	}

}
