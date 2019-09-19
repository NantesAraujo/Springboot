package br.com.alura.forum.controllerTest;

import br.com.alura.forum.builders.TopicoBuilder;
import br.com.alura.forum.config.GenericTest;
import br.com.alura.forum.dto.TopicoCadastroDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(webClientEnabled = true, webDriverEnabled = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

        topicoRepository.save(topicoMocks.get(0));
        topicoRepository.save(topicoMocks.get(1));

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

    @Test
    public void devePersisitenciaDeTopico() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        TopicoCadastroDto topico = new TopicoCadastroDto();
        topico.setTitulo("JAVA 8");
        topico.setMensagem("Estou com duvida de java");
        topico.setNomeDoCurso("Aprendendo Java 8");


        String objetoJson = objectMapper.writeValueAsString(topico);

        mockMvc.perform(post("/topicos/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objetoJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deveRetornarUmErroDeBadRequestParaCadastroComParametrosFaltando() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Topico novoTopico = new Topico();

        String jsonTopico = objectMapper.writeValueAsString(novoTopico);

        mockMvc.perform(post("/topicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonTopico))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }
}
