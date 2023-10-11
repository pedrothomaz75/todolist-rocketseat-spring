package br.com.pedrotomaz.todolist.user;
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
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel) {
        System.out.println(userModel.getUsername());
    }
}
