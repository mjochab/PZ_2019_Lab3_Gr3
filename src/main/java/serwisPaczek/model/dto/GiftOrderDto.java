package serwisPaczek.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftOrderDto {
    private Long id;
    private String giftName;
    private String name;
    private String surname;
    private String city;
    private String street;
    private int houseNumber;
    private String zipCode;
    private Long telephoneNumber;
    private String email;
    private String date;
    private String senderName;
    private String status;
}
