package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class GrzybService {

    public void wyczyscGrzyba(Grzyb grzyb){

        System.out.println("Czyszcze grzyba " + grzyb.getNazwa());
        grzyb.setCzysty(true);

    }

}
