package br.com.alura.forum.controllers;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @RequestMapping(path = "/topicos")
    public List<Topico> lista(){
        Curso curso = new Curso("Spring", "programacao");
        Topico topico = new Topico("Duvida", "duvida com spring", curso);

        return Arrays.asList(topico, topico, topico);
    }
}
