package andrew.projects.influx.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User extends BaseEntity {
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

}