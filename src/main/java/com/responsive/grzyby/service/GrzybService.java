package com.responsive.grzyby.service;

import com.responsive.grzyby.model.Grzyb;
import org.springframework.stereotype.Service;

@Service
public class GrzybService {

    public void wyczyscGrzyba(Grzyb grzyb){

        System.out.println("Czyszcze grzyba " + grzyb.getNazwa());
        grzyb.setCzysty(true);

    }

}
