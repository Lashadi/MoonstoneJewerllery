package lk.ijse.moonstonejewerllary.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemTm {
    private String code;
    private String itemName;
    private String category;
    private int qtyOnHand;
    private double price;
}
