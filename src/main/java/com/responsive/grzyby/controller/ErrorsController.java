package com.responsive.grzyby.controller;

import com.responsive.grzyby.model.Grzyb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ErrorsController {

    @GetMapping("/api/error")
    public Mono<Grzyb> getError1() {

        Grzyb grzyb = new Grzyb(1, "Jadalne", "Pieczrka", false);

        return Mono.just(grzyb).handle((grz, synchronousSink) -> {

            if (!grz.getCzysty()){

                synchronousSink.error(new RuntimeException("Brudny grzyb"));

            } else {

                synchronousSink.next(grz);

            }

        });
    }

    @GetMapping("/api/error2")
    public Mono<Object> getError2() {

        return Mono.empty()
                .switchIfEmpty(Mono.error(new RuntimeException("Pusty mono")));

    }

    @GetMapping("/api/error3")
    public Mono<Grzyb> getError3() {

        Grzyb grzyb = new Grzyb(1, "Jadalne", "Pieczrka", false);

        return Mono.just(grzyb)
                .filter(Grzyb::getCzysty)
                .switchIfEmpty(Mono.error(new RuntimeException("Pusty mono")));

    }

    @GetMapping("/api/error4")
    public Flux<Grzyb> getError4() {

        Grzyb grzyb1 = new Grzyb(1, "Jadalne", "Pieczrka", false);

        Grzyb grzyb2 = new Grzyb(1, "Jadalne", "Pieczrka, ale czysta", true);

        return Flux.just(grzyb1, grzyb2)
                .concatMap(grzyb -> {

                    if(grzyb.getCzysty()) {

                        return Mono.just(grzyb);

                    } else {

                        return Flux.error(new RuntimeException("Grzyb nieczysty"));

                    }
                });
    }

    @GetMapping("/api/error5")
    public Mono<Void> getError5() {

        return Mono.when(Mono.error(new RuntimeException("Test propagacji"))).then();
    }

}
