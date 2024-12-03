package com.kltn.product_service.dto;

import lombok.*;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String id;
    private String email;
    private String firstname;
    private String lastname;
    private String phoneNumber;
}
