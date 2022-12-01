package su.uspenskit.calculator.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  Role() { }

    public Role(String name) {
        this.setName(name);
    }

    public Role(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }
}
