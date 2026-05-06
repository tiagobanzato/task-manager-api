package com.banzato.tarefas_api.service;

import com.banzato.tarefas_api.model.Tarefa;
import com.banzato.tarefas_api.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private GeminiService geminiService;

    public Tarefa criar(Tarefa tarefa) {
        String prioridade = geminiService.classificarPrioridade(
                tarefa.getTitulo(),
                tarefa.getDescricao()
        );
        tarefa.setPrioridade(prioridade);
        return repository.save(tarefa);
    }

    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Tarefa atualizar(Long id, Tarefa tarefaNova) {
        Tarefa tarefaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefaExistente.setTitulo(tarefaNova.getTitulo());
        tarefaExistente.setDescricao(tarefaNova.getDescricao());
        String prioridade = geminiService.classificarPrioridade(
                tarefaNova.getTitulo(),
                tarefaNova.getDescricao()
        );
        tarefaExistente.setPrioridade(prioridade);
        return repository.save(tarefaExistente);
    }
}