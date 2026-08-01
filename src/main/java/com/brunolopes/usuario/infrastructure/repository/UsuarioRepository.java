package com.brunolopes.usuario.infrastructure.repository;

import com.brunolopes.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    // Optional = caixa que trata melhor os retornos quando forem nulos
    Optional<Usuario> findByEmail(String email);

    @Transactional//garante integridade às operações no banco de dados
    @Modifying // diz para o Spring que a operação não é de busca e sim de alteração
    void deleteByEmail(String email);
}
