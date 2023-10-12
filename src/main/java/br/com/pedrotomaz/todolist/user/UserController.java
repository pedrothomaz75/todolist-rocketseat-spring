package br.com.pedrotomaz.todolist.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
*   Modificadores
*     - public
*     - private
*     - protected
* */


@RestController
@RequestMapping("/users")
public class UserController {

    // criação do usuário
    // As informações do usuário irão vir no corpo da requisição
    // Ou seja, usando o RequestBody

        // Generator que faz a inicialização/Instanciação do meu userRepository
        @Autowired
        // Fazendo a chamada da interface
        private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        // Fazendo a verificação de duplicação de username
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null) {
            System.out.println("Usuário já existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe !");
        }
        // Salva os dados de userModel
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso !");
    }
}
