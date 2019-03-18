package com.responsive.grzyby.controller;

import com.responsive.grzyby.model.Grzyb;
import com.responsive.grzyby.service.GrzybService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping("/api/grzyb5")
    public Mono<Grzyb> grzybGet5() {

        Grzyb grzyb1 = new Grzyb(1, "Gąbczasty", "Maślak", false);

        return Mono.just(grzyb1)
                .map(grzyb -> {
                    grzyb.setId(2);
                    return grzyb; })
                .log()
                .filter(grzyb -> grzyb.getId() != 2)
                .log();
    }

    @GetMapping("/api/grzyb6")
    public Mono<Grzyb> grzybGet6() {

        Grzyb grzyb1 = new Grzyb(1, "Gąbczasty", "Maślak", false);

        return Mono.just(grzyb1)
                .doOnNext(System.out::println)
                .doOnSuccess(grzyb -> System.out.println("Koniec"))
                .then(Mono.empty());

    }

    @GetMapping("/api/grzyb7")
    public Mono<String> grzybGet7() {

        Grzyb grzyb1 = new Grzyb(1, "Gąbczasty", "Maślak", false);

        Grzyb grzyb2 = new Grzyb(2,  "Trujacy", "Muchomor", false);

        Mono<Grzyb> mono1 = Mono.just(grzyb1).doOnNext(System.out::println);

        Mono<Grzyb> mono2 = Mono.just(grzyb2).doOnNext(System.out::println);

        return Mono.when(mono1, mono2).then(Mono.just("Grzyby pobrane"));

    }

    @GetMapping("/api/grzyb8")
    public Mono<Void> grzybGet8() {

        Grzyb grzyb1 = new Grzyb(1, "Gąbczasty", "Maślak", false);

        Mono<RuntimeException> error = Mono.error(RuntimeException::new);

        Mono<Grzyb> mono1 = Mono.just(grzyb1);

        Mono<Void> finalMono = Mono.when(mono1, error).then(Mono.empty());

        return finalMono.doOnError(err -> System.out.println("Ojej"));

    }

}
