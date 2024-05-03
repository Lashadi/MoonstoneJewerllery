package lk.ijse.moonstonejewerllary.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private String tel;
    private String email;
}
