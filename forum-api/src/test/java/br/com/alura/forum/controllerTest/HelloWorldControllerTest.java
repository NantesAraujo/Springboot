package br.com.alura.forum.controllerTest;

import br.com.alura.forum.builders.TopicoBuilder;
import br.com.alura.forum.config.GenericTest;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.h2.command.ddl.CreateUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(webClientEnabled = true, webDriverEnabled = true)
public class HelloWorldControllerTest extends GenericTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void deveRetonarStatus200ParaEndPointDefault() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
    }

    @Test
    public void retornarQuantidadeEsperadaDeTopicos() throws Exception {
        List<Topico> topicoMocks = TopicoBuilder.retornarUmaListaComDoisTopicos();

        topicoRepository.saveAll(topicoMocks);

        mockMvc.perform(get("/topicos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void retornarQuantidadeEsperadaDeTopicosPassandoFiltroNomeDoCurso() throws Exception {
        List<Topico> topicoMocks = TopicoBuilder.retornarUmaListaComDoisTopicos();
        List<Curso> cursosMocks = TopicoBuilder.retornarListaComDoisCursos();

        Curso curso1 = cursoRepository.save(cursosMocks.get(0));
        Curso curso2 = cursoRepository.save(cursosMocks.get(1));

        topicoMocks.get(0).setCurso(curso1);
        topicoMocks.get(1).setCurso(curso2);

        topicoRepository.saveAll(topicoMocks);

        mockMvc.perform(get("/topicos").param("nomeCurso", "Spring Boot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
