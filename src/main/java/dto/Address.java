package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String streetAddress;
    private String city;
    private String country;
    private String zipCode;
    private String phone;
}
