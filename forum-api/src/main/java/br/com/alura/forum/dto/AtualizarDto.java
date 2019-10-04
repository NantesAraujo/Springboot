package br.com.alura.forum.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AtualizarDto {

    @NotEmpty @NotNull @Length(min = 5)
    private String titulo;

    @NotEmpty @NotNull
    private String mensagem;
}
