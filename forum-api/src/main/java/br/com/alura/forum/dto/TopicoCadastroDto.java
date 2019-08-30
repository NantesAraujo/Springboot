package br.com.alura.forum.dto;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import lombok.Data;

@Data
public class TopicoCadastroDto {

    private String titulo;

    private String mensagem;

    private String nomeDoCurso;

    public Topico converter(CursoRepository cursoRepository){
        Curso curso = cursoRepository.findByNome(nomeDoCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
