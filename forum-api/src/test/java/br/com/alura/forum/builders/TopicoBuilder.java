package br.com.alura.forum.builders;

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
        topico1.setMensagem("segundo topico");
        topico1.setDataCriacao(LocalDateTime.now());

        return Arrays.asList(topico1, topico2);
    }
}
