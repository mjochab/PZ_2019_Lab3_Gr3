package serwisPaczek.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import serwisPaczek.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDto {
    private Long id;
    private String name;
    private String surname;
    private String city;
    private String street;
    private int houseNumber;
    private String zipCode;
    private Long telephoneNumber;
    private String email;
    private String date;
    private String courier;
    private Status status;
    private String senderName;
    private String senderSurname;
}