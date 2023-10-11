package br.com.pedrotomaz.todolist.user;

public class UserModel {
    private String username;
    private String name;
    private String password;

    // getters and setters
      // - getter = mostrar dado
      // - setter = alterar ou adicionar dado


    // Insere valor
    public void setUsername(String username) {
        this.username = username;
    }

    // Retorna valor
    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
