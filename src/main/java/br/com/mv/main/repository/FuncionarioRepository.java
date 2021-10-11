package br.com.mv.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mv.main.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query(value = "SELECT * FROM Employee", nativeQuery = true)
	List<Funcionario> allFuncionario();
	
	
	@Query(value = "SELECT * FROM Employee WHERE cpf = :cpf", nativeQuery = true)
	Optional<Funcionario> filterByCpf(@Param("cpf") String cpf);

	
	@Query(value = "SELECT * FROM Employee  WHERE cafe like CONCAT(:cafe,'%')", nativeQuery = true)
	List<Funcionario> filterByCafe(@Param("cafe") String cafe);

	
	@Query(value = "SELECT * FROM Employee WHERE id = :id", nativeQuery = true)
	Optional<Funcionario> filterById(@Param("id") Long id);
}
