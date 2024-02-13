package com.ltp.contactbook.dto;

import com.ltp.contactbook.validation.ValidName;
import com.ltp.contactbook.validation.ValidPin;
import com.ltp.contactbook.validation.ValidPinSize;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonDTO {

    private Long id;

    @NotNull(message = "Full name cannot be null")
    @ValidName
    @Size(max = 90,message = "Full name is too long, allowed up to 90 characters.")
    private String fullName;

    @ValidPin
    @ValidPinSize
    private String pin;

    @NotNull(message = "Email type cannot be null")
    @Size(max=5, message = "Email type is too long, allowed up to 5 characters.")
    private String emailType;

    @Size(max=40, message = "Email is too long, allowed up to 40 characters.")
    @javax.validation.constraints.Email(message = "Email is not valid")
    private String email;

    @Size(max=5, message = "Address type is too long, allowed up to 5 characters.")
    @NotNull(message = "Address type cannot be null")
    private String addressType;

    @Size(max=300, message = "Address info is too long, allowed up to 300 characters.")
    private String addressInfo;
}
