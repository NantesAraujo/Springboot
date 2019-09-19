package br.com.alura.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandlerDto {

    private String campo;

    private String mensagem;
}
