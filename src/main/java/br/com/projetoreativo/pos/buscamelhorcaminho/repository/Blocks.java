package br.com.projetoreativo.pos.buscamelhorcaminho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoreativo.pos.buscamelhorcaminho.model.Block;


public interface Blocks extends JpaRepository<Block, Long> {

}
