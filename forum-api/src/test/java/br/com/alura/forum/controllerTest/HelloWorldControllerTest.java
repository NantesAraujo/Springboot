package br.com.alura.forum.controllerTest;

import br.com.alura.forum.builders.TopicoBuilder;
import br.com.alura.forum.config.GenericTest;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
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
}
