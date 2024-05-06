package lk.ijse.moonstonejewerllary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
    private String orderId;
    private String itemCode;
    private double unitPrice;
    private int qty;
    private double totalAmount;
}
