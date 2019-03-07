package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grzyb {

    private Integer id;

    private String gatunek;

    private String nazwa;

    private Boolean czysty = false;
}
