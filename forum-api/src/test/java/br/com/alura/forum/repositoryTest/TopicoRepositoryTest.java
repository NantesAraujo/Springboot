package br.com.alura.forum.repositoryTest;

import br.com.alura.forum.builders.TopicoBuilder;
import br.com.alura.forum.config.GenericTest;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TopicoRepositoryTest extends GenericTest {

    @Autowired
    private TopicoRepository topicoRepository;

    @Test
    public void whenFindAll(){

        List<Topico> topicosMock = TopicoBuilder.retornarUmaListaComDoisTopicos();
        topicoRepository.saveAll(topicosMock);

        List<Topico> topicos = topicoRepository.findAll();

        assertTrue(Objects.nonNull(topicos));
        assertEquals(topicos.size(), 2);
    }
}
