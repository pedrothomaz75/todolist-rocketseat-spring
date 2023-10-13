package br.com.pedrotomaz.todolist.task;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

        @Autowired
        private ITaskRepository taskRepository;

        @PostMapping("/")
        public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
            var idUSer = request.getAttribute("idUSer");
            taskModel.setIdUser((UUID) idUSer);

            // Validando horas
            var currentDate = LocalDateTime.now();

            // Fazendo a verificação para o startAT
            if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início / data de término tem que ser maior que a data atual.");
            }

            // Fazendo a verificação se meu EndAt pode ser maior que meu StartAt
            if(taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início tem que ser menor que a data de término.");
            }

            var task = this.taskRepository.save(taskModel);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        }
}
