package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class GrzybController {

    private GrzybService grzybService;

    @GetMapping("/api/grzyb")
    public Grzyb grzybGet() throws InterruptedException {

        Grzyb grzyb = new Grzyb(1, "Gąbczasty", "Maślak", false);
        TimeUnit.SECONDS.sleep(10);


        return grzyb;

    }

    @GetMapping("/api/grzyb2")
    public Grzyb grzybGet2() {

        Grzyb grzyb = new Grzyb(1, "Gąbczasty", "Maślak", false);

        return grzyb;

    }

    @GetMapping("/api/grzyb3")
    public Flux<Grzyb> grzybGet3() {

        Grzyb grzyb1 = new Grzyb(1, "Gąbczasty", "Maślak", false);

        Grzyb grzyb2 = new Grzyb(2, "Blaszkowy", "Pieczarka", false);

        Mono<Grzyb> grzybMono = Mono.just(grzyb1);

        Mono<Grzyb> grzybMono2 = Mono.just(grzyb2);

        grzybMono = grzybMono.map(grzyb -> {
            grzybService.wyczyscGrzyba(grzyb);
            return grzyb;
        });

        grzybMono2 = grzybMono2.map(grzyb -> {
            grzybService.wyczyscGrzyba(grzyb);
            return grzyb;
        });

        return Flux.concat(grzybMono, grzybMono2).doOnComplete(() -> System.out.println("Koniec"));
    }

    @GetMapping("/api/grzyb4")
    public Mono<String> grzybGet4() {

        Grzyb grzyb1 = new Grzyb(1, "Gąbczasty", "Maślak", false);

        Grzyb grzyb2 = new Grzyb(2, "Blaszkowy", "Pieczarka", false);

        Mono<Grzyb> grzybMono = Mono.just(grzyb1);

        Mono<Grzyb> grzybMono2 = Mono.just(grzyb2);

        grzybMono = grzybMono.map(grzyb -> {
            grzybService.wyczyscGrzyba(grzyb);
            return grzyb;
        });

        grzybMono2 = grzybMono2.map(grzyb -> {
            grzybService.wyczyscGrzyba(grzyb);
            return grzyb;
        });

        return Mono.when(grzybMono, grzybMono2).then(
                Mono.just("osiem")
        );
    }


}
