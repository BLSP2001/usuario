package com.brunolopes.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "idade", length = 100)
    private String idade;
    @Column(name = "senha")
    private String senha;
    @Column(name = "email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL) // relação em cascata = no momento que eu excluir o usuário, o endereço será excluído automaticamente
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private com.brunolopes.usuario.infrastructure.entity.Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<com.brunolopes.usuario.infrastructure.entity.Telefone> telefone;

    @Override //anotação que significa "Substituir" e diz que esse metodo veio de outro lugar
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
