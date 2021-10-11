package br.com.mv.main.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.mv.main.model.Funcionario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FuncionarioListDTO {
	private Long id;
	private String cpf;
	private String nome;
	private String cafe;
	
	FuncionarioListDTO(Funcionario funcionario){
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.cafe = funcionario.getCafe();
		this.cpf = funcionario.getCpf();
	}
	
	public static List<FuncionarioListDTO> convert(List<Funcionario> funcionario) {
		return funcionario.stream().map(FuncionarioListDTO::new).collect(Collectors.toList());
	}
	
	public static FuncionarioDTO fromDTO(Funcionario funcionario) {
		return new FuncionarioDTO(funcionario.getId(),funcionario.getCpf(), funcionario.getNome(), funcionario.getCafe());
	}
}
