package lk.ijse.moonstonejewerllary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Supplier {
    private String id;
    private String name;
    private String itemName;
    private String tel;
    private String uId;
}
