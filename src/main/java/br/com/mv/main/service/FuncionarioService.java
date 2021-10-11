package br.com.mv.main.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.mv.main.dto.FuncionarioListDTO;
import br.com.mv.main.dto.MessageResponseDTO;
import br.com.mv.main.dto.FuncionarioDTO;
import br.com.mv.main.exception.DataBaseException;
import br.com.mv.main.model.Funcionario;
import br.com.mv.main.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FuncionarioService {
	
	FuncionarioRepository funcionarioRepository;

	public List<FuncionarioListDTO> listCafe() {
		
		List<Funcionario> employee = funcionarioRepository.allFuncionario();
				
		return FuncionarioListDTO.convert(employee);
	}
	
	public Optional<Funcionario> listById(Long id) {
		Optional<Funcionario> employee = funcionarioRepository.filterById(id);
		if(employee.isPresent()) {
			return employee;
		}else {
			throw new DataBaseException("Colaborador não encontrado.");		
		}
	}
	
	public MessageResponseDTO createFuncionario(FuncionarioDTO salvarColaboradorDto) {
		try {
			Optional<Funcionario> filterCPF = funcionarioRepository.filterByCpf(salvarColaboradorDto.getCpf());
			List<Funcionario> filterCafe = funcionarioRepository.filterByCafe(salvarColaboradorDto.getCafe());
		
			
			if (filterCPF.isEmpty()) {
				if(filterCafe.isEmpty()) {
					Funcionario funcionario = new FuncionarioDTO().fromModel(salvarColaboradorDto);
					funcionarioRepository.save(funcionario);
					return messageResponse("Usuário criado!");
				}else {
					return messageResponse("O café já foi escolhido.");
				}
			}else {
				return messageResponse("Voce já foi cadastrado.");
			}
			
		}
		catch (DataIntegrityViolationException  e) {
			throw new DataBaseException("Algo deu errado. :( ");
		}
	}
	
	
	public MessageResponseDTO updateFuncionario(Long id, @Valid FuncionarioDTO funcionarioDTO) {
		
		if(filtrarId(id)) {
			Funcionario funcionarioDto = new FuncionarioDTO().fromModel(funcionarioDTO);
			Funcionario funcionarioUpdate = funcionarioRepository.save(funcionarioDto);
			return MessageResponseDTO
					.builder()
					.message("Informações atualizadas. ID do colaborador: " + funcionarioUpdate.getId())
					.build();
		}else {
			return messageResponse("Colaborador não encontrado.");	
		}
		
	}
	
	public MessageResponseDTO deleteFuncionario(Long id){
		if(filtrarId(id)) {
			funcionarioRepository.deleteById(id);
			return messageResponse("Colaborador deletado.");
		}else {
			return messageResponse("Colaborador não encontrado.");	
		}
	}
	
	
	private Boolean filtrarId(Long id) {
		Optional<Funcionario> result = funcionarioRepository.filterById(id);
		return result.isPresent() ? true : false;
	}

	private MessageResponseDTO messageResponse(String mensagem) {
		return MessageResponseDTO.builder().message(mensagem).build();
	}
}
