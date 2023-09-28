package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private int    stdId;
    private String fullName;
    private String age;
    private String address;
    private String contact;
    private String gender;
    

}
