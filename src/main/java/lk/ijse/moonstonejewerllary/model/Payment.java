package lk.ijse.moonstonejewerllary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    private String paymentId;
    private String customerId;
    private String orderId;
    private String paymentAmount;
    private Date date;
}
