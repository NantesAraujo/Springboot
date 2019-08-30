package br.com.alura.forum.builders;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public abstract class TopicoBuilder {

    public static List<Topico> retornarUmaListaComDoisTopicos(){
        Topico topico1 = new Topico();
        topico1.setMensagem("primeiro topico");
        topico1.setDataCriacao(LocalDateTime.now());

        Topico topico2 = new Topico();
        topico2.setMensagem("segundo topico");
        topico2.setDataCriacao(LocalDateTime.now());

        return Arrays.asList(topico1, topico2);
    }

    public  static List<Curso> retornarListaComDoisCursos(){
        Curso curso1 = new Curso();
        curso1.setNome("Spring Boot");

        Curso curso2 = new Curso();
        curso2.setNome("HTML 5");

        return Arrays.asList(curso1, curso2);
    }
}
