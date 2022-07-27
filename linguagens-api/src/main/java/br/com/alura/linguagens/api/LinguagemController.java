package br.com.alura.linguagens.api;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.lang.Integer;

@RestController
public class LinguagemController implements Comparable<Linguagem> {
    @Autowired
    private LinguagemRepository repository;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguaguens(){
        List<Linguagem> linguagens = repository.findAll();
        linguagens.sort(Comparator.comparing(Linguagem::getVotes).reversed());
        return linguagens;
    }

    @PatchMapping("/linguagens/{id}")
    public Linguagem atualizarLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem){
        Linguagem linguagemAtual = repository.findById(id).get();
        System.out.println(linguagem);
        int votes = linguagemAtual.getVotes() + 1;
        linguagemAtual.setVotes(votes);
        return repository.save(linguagemAtual);
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem){
        repository.save(linguagem);
        return new ResponseEntity<Linguagem>(HttpStatus.CREATED);
    }

    @DeleteMapping("/linguagens/")
    public Linguagem deletarLinguagem(@RequestBody Linguagem linguagem){
        repository.delete(linguagem);
        return linguagem;
    }

    @Override
    public int compareTo(Linguagem o){
        return 0;
    }
}

