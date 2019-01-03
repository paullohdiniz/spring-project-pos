package br.com.projetoreativo.pos.buscamelhorcaminho.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoreativo.pos.buscamelhorcaminho.model.Block;
import br.com.projetoreativo.pos.buscamelhorcaminho.model.ManagerBlock;



@RestController
@RequestMapping("/blockchair")
public class NoobChain {

	public static int difficulty = 3;

	private ManagerBlock managerBlock = new ManagerBlock();

	@PostMapping
	public Block adiciona(@Valid @RequestBody Block block) {
		return managerBlock.save(block, difficulty);
	}

	@GetMapping
	public List<Block> lista() {
		return managerBlock.listar();
	}


}
