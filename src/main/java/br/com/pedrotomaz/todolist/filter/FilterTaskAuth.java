package br.com.pedrotomaz.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.pedrotomaz.todolist.user.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

// Generator de gerenciamento de classe do Spring
@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                // Pega a autenticação e senha
                var authorization = request.getHeader("Authorization");
                var authEncoded = authorization.substring("Basic".length()).trim();

                // Decoda o authorization
                byte[] authDecode = Base64.getDecoder().decode(authEncoded);
                // Transforma em String
                var authString = new String(authDecode);

                // Separa usuário da senha
                String[] credentials = authString.split(":");

                // Instanciando o username na posição dele no array
                String username = credentials[0];
                // Instanciando o password na posição dele no array
                String password = credentials[1];

                System.out.println("Authorization:");
                System.out.println(username);
                System.out.println(password);


                // Valida usuário
                var user = this.userRepository.findByUsername(username);

                // Verificação de existência de usuário
                if(user == null) {
                    response.sendError(401);
                } else {
                    // valida senha
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    if(passwordVerify.verified) {
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }
                    // Entrou na Home Page

                }
    }
}
