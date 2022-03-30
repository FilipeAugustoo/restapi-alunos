package br.com.ceat.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ceat.api.model.Aluno;
import br.com.ceat.api.repository.AlunoRepository;
import br.com.ceat.api.service.CadastroAlunoService;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "https://filipeaugustoo.github.io", allowedHeaders = "*")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CadastroAlunoService cadastroAlunoService;

	@GetMapping
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	@GetMapping("/{alunoId}")
	public ResponseEntity<Aluno> buscar(@PathVariable Long alunoId) {
		return alunoRepository.findById(alunoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno adiciona(@Valid @RequestBody Aluno aluno) {
		return cadastroAlunoService.salvar(aluno);
	}

	@PutMapping("/{alunoId}")
	public ResponseEntity<Aluno> atualizar(@Valid @PathVariable Long alunoId, @Valid @RequestBody Aluno aluno) {
		if (!alunoRepository.existsById(alunoId)) {
			return ResponseEntity.notFound().build();
		}

		aluno.setId(alunoId);
		aluno = cadastroAlunoService.salvar(aluno);

		return ResponseEntity.ok(aluno);
	}

	@DeleteMapping("/{alunoId}")
	public ResponseEntity<Void> remover(@PathVariable Long alunoId) {
		if (!alunoRepository.existsById(alunoId)) {
			return ResponseEntity.notFound().build();
		}

		cadastroAlunoService.excluir(alunoId);

		return ResponseEntity.noContent().build();
	}
}
