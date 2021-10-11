package br.com.mv.main.dto;
import javax.validation.constraints.Size;

import br.com.mv.main.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

	private Long id;
	
	private String cpf;
	
	@Size(min = 3, max = 50, message = "Nome é obrigatório, deve ter entre {min} e " + "{max} caracteres.")
	private String nome;
	
	@Size(min = 3, max = 60, message = "Nome é obrigatório, deve ter entre {min} e " + "{max} caracteres.")
	private String cafe;
	
	public Funcionario fromModel(FuncionarioDTO salvarColaboradorDto) {
		return new Funcionario(salvarColaboradorDto.getId(),salvarColaboradorDto.getCpf(), salvarColaboradorDto.getNome(), salvarColaboradorDto.getCafe());
	}
	
	public static FuncionarioDTO fromDTO(Funcionario funcionario) {
		return new FuncionarioDTO(funcionario.getId(),funcionario.getCpf(), funcionario.getNome(), funcionario.getCafe());
	}
}
