package br.com.alura.forum.controllers;

import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @RequestMapping(path = "/topicos")
    public List<TopicoDto> lista(){
        Curso curso = new Curso("Spring", "programacao");
        Topico topico = new Topico("Dúvidas", "duvida com spring no curso ", curso);

        return TopicoDto.converter(Arrays.asList(topico, topico, topico));
    }
}
