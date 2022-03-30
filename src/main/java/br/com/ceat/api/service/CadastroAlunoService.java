package br.com.ceat.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ceat.api.exception.EntidadeNaoEncontradaException;
import br.com.ceat.api.exception.NegocioException;
import br.com.ceat.api.model.Aluno;
import br.com.ceat.api.repository.AlunoRepository;

@Service
public class CadastroAlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public Aluno salvar(Aluno aluno) {
		boolean matriculaIgual = alunoRepository.findByMatricula(aluno.getMatricula())
				.stream()
				.anyMatch(m -> m.equals(aluno));
				
		
		if (matriculaIgual) {
			throw new NegocioException("Já existe um aluno com essa matrícula");
		}
		
		return alunoRepository.save(aluno);
	}
	
	public void excluir(Long alunoId) {
		alunoRepository.deleteById(alunoId);
	}
	
	public Aluno buscar(Long alunoId) {
		return alunoRepository.findById(alunoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno não encontrado"));
	}
}
