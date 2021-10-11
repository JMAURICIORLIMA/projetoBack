package br.com.mv.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Column(nullable = false, length = 60)
	private String cafe;

	
	public Funcionario(Long id, String cpf, String nome, String cafe) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.cafe = cafe;
	}
	public Funcionario(String cpf, String nome, String cafe) {
		this.cpf = cpf;
		this.nome = nome;
		this.cafe = cafe;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cafe == null) ? 0 : cafe.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (cafe == null) {
			if (other.cafe != null)
				return false;
		} else if (!cafe.equals(other.cafe))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
