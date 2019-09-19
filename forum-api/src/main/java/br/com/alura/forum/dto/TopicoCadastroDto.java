package br.com.alura.forum.dto;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TopicoCadastroDto {

    @NotEmpty @NotNull @Length(min = 5)
    private String titulo;

    @NotEmpty @NotNull
    private String mensagem;

    @NotEmpty @NotNull
    private String nomeDoCurso;

    public Topico converter(CursoRepository cursoRepository){
        Curso curso = cursoRepository.findByNome(nomeDoCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
