package andrew.projects.influx.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User extends BaseEntity implements Serializable {
    private String username;
    private String password;
    private String email;
    private String emailConfirmation;

    private List<Authority> authorities ;
    @JsonIgnore
    private List<Company> companies ;

    public User(String login, String pass) {
        this.username=login;
        this.password=pass;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}