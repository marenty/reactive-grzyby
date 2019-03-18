package com.responsive.grzyby.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
public class MapController {

    @GetMapping("/api/mono/map")
    public Mono<String> mapMono() {

        List<String> strings = Arrays.asList("Kot", "Pies", "Post", "Chleb");

        return Mono.just(strings).map(stringsList ->
                String.join(",", stringsList));
    }

    @GetMapping("/api/mono/flat_map")
    public Mono<String> flatMapMono() {

        List<String> strings = Arrays.asList("Kot", "Pies", "Post", "Chleb");

        return Mono.just(strings).flatMap(stringsList ->
                Mono.just(String.join(",", stringsList)));
    }

    @GetMapping("/api/mono/flat_map_many")
    public Flux<String> flatMapManyMono() {

        List<String> strings = Arrays.asList("Kot", "Pies", "Post", "Chleb");

        return Mono.just(strings).flatMapMany(Flux::fromIterable);
    }

    @GetMapping("/api/mono/flat_map_iterable")
    public Flux<String> flatMapIterableMono() {

        List<String> strings = Arrays.asList("Kot", "Pies", "Post", "Chleb");

        return Mono.just(strings).flatMapIterable(str -> str.subList(0, 2));
    }

}
