package com.codingshuttle.youtube.LearningRESTAPIs.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class AddStudentRequestDto {

    @NotBlank(message = "name is required")
    @Size(min=3,max = 30,message = "name should be of length 3-30 characters")
    private String name;

    @Email
    @NotBlank(message = "email is required")
    private String email;

}
