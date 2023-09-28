package edu.icet.dao;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "StudentDetails")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    stdId;
    private String fullName;
    private String age;
    private String address;
    private String contact;
    private String gender;

}
