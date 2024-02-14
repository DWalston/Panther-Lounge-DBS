package com.panther.details;

import lombok.Data;
import java.time.LocalDate;


@Data
public class checkoutForm {
    String memberID;
    String itemID;
    String codate;
    String returnDate;
}
