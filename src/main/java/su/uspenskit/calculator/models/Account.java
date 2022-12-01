package su.uspenskit.calculator.models;

import su.uspenskit.calculator.repos.AccountRepository;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String password;

    @ManyToOne
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account() { }

    public Account(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }
}
