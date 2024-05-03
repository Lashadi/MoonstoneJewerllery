package lk.ijse.moonstonejewerllary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String userId;
    private String userName;
    private String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
