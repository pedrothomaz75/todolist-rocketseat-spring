package br.com.pedrotomaz.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/*
 *    A tarefa pode ter:
 *
 *       - ID
 *       - Usuário (ID_USUARIO)
 *       - Descrição
 *       - Titulo
 *       - Data de Início
 *       - Data de Término
 *       - Prioridade
 * */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    // Colocando limite na quantidade de caracteres
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String Priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private void setTitle(String title) throws Exception{
        if (title.length() > 50) {
            throw new Exception("O campo title deve conter no máximo 50 caractéres");
        }
        this.title = title;
    }

}
