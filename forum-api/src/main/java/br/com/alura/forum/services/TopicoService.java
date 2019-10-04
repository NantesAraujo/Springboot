package br.com.alura.forum.services;

import br.com.alura.forum.dto.AtualizarDto;
import br.com.alura.forum.dto.DetalhesTopicoDto;
import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public ResponseEntity<DetalhesTopicoDto> findById(Long id){
        Optional<Topico> topico = topicoRepository.findById(id);

        if(topico.isPresent())
            return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));

        return  ResponseEntity.notFound().build();
    }


    @Transactional
    public ResponseEntity<TopicoDto> update(Long id, AtualizarDto atualizarDto){
        Optional<Topico> topico = topicoRepository.findById(id);

        if(topico.isPresent()) {
            topico.get().setMensagem(atualizarDto.getMensagem());
            topico.get().setTitulo(atualizarDto.getTitulo());

            return ResponseEntity.ok(new TopicoDto(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<?> delete(Long id){
        Optional<Topico> topico = topicoRepository.findById(id);

        if(topico.isPresent()) {
            topicoRepository.deleteById(id);
            return  ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
