package br.com.mv.main.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mv.main.dto.FuncionarioDTO;
import br.com.mv.main.dto.FuncionarioListDTO;
import br.com.mv.main.dto.MessageResponseDTO;
import br.com.mv.main.model.Funcionario;
import br.com.mv.main.service.FuncionarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FuncionarioController {

	private FuncionarioService funcionarioService;
	
	
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<FuncionarioListDTO> listFuncionarios() {
		return funcionarioService.listCafe();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Funcionario> listFuncionarioId(@PathVariable Long id) {
		return funcionarioService.listById(id);
	}
	
	@PostMapping("/cafe")
	public ResponseEntity<MessageResponseDTO> createFunciorario(@RequestBody @Valid FuncionarioDTO createEmployee) {
		return new ResponseEntity<MessageResponseDTO>(funcionarioService.createFuncionario(createEmployee),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public MessageResponseDTO changeFuncionario(@PathVariable Long id, @RequestBody @Valid FuncionarioDTO editEmployee) {
		editEmployee.setId(id);
		return funcionarioService.updateFuncionario(id, editEmployee);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MessageResponseDTO deleteFuncionario(@PathVariable Long id) {
		return funcionarioService.deleteFuncionario(id);
	}
}
