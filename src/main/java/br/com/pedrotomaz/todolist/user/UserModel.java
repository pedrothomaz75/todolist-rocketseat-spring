package br.com.pedrotomaz.todolist.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data // inclue os getters and setters
@Entity(name = "tb_users") // @Entity é a minha tabela no banco de dados e tb_users é o nome dela
public class UserModel {

    // O meu Id vai ser uma pk que vai usar as funções de UUID
    @Id
    // Gera pra mim um valor UUID de forma aleatória
    @GeneratedValue(generator = "UUID")
    private UUID id;


    private String username;
    private String name;
    private String password;

    // getters and setters
      // - getter = mostrar dado
      // - setter = alterar ou adicionar dado


    /*
    *  Quando os dados forem gerados, o banco vai ter a data e hora de quando isso aconteceu
    * */
    @CreationTimestamp
    private LocalDateTime CreatedAt;
}
