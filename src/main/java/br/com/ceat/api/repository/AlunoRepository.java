package br.com.ceat.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ceat.api.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	List<Aluno> findByNome(String nome);
	List<Aluno> findByNomeContaining(String nome);
	Optional<Aluno> findByMatricula(String matricula);
}
