package br.com.projetoreativo.pos.buscamelhorcaminho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoreativo.pos.buscamelhorcaminho.model.Contato;


public interface Contatos extends JpaRepository<Contato, Long> {

}
