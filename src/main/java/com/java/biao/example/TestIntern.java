package com.java.biao.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TestIntern {
    public static void main(String[] args) throws JsonProcessingException {
        int size = 10;
        System.out.println(--size);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Address {

    private String street;
    private String city;
    private String country;

    // standard constructors, getters and setters
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class User1 {

    private String firstName;
    private String lastName;
    private Address address;

    // standard constructors, getters and setters
}
