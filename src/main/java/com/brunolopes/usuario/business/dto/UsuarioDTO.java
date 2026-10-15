package com.brunolopes.usuario.business.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //builder = padrão de projeto que gera todos os métodos necessários durante a compilação. Ele garante a entrega de objetos imutáveis, por isso não tem o método setter

public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;
}
