package br.com.projetoreativo.pos.buscamelhorcaminho.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;


/**
 * Paulo Diniz
 * 02-01-2019
 * Entidade representa banco de dados
 */
@Entity
public class Block {
	
	public Block(){
		
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	@NotBlank
	private String previousHash; 
	
	@NotBlank
	private String data; 
	
	@NotBlank
	private String hash;
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	@JoinColumn(name="global_config_id")
//	private BlockList blockList;
//	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

//	public BlockList getBlockList() {
//		return blockList;
//	}
//
//	public void setBlockList(BlockList blockList) {
//		this.blockList = blockList;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((previousHash == null) ? 0 : previousHash.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (id != other.id)
			return false;
		if (previousHash == null) {
			if (other.previousHash != null)
				return false;
		} else if (!previousHash.equals(other.previousHash))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Block [id=" + id + ", previousHash=" + previousHash + ", data=" + data + ", hash=" + hash
				+ "]";
	}
	
	
}
