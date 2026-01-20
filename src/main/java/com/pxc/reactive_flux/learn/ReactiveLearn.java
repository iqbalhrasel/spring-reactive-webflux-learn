package com.pxc.reactive_flux.learn;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ReactiveLearn {
    static void main() {
        run();
    }

    private Mono<String> mono(){
        return Mono.just("hello java").log();
//        return Mono.justOrEmpty(null);
    }

    private Flux<String> flux(){
//        return Flux.just("java", "rust", "cpp", "js").log();
        var langs = List.of("java", "rust", "cpp", "js");
        return Flux.fromIterable(langs).log();
    }

    private Flux<String> fluxMap(){
        var langs = List.of("java", "rust", "cpp", "js");
        var flux = Flux.fromIterable(langs).log();

        return flux.map(l -> l + " lang");
    }

    private Flux<String> fluxFlatMap(){
        var langs = List.of("java", "rust", "cpp", "js");
        var flux = Flux.fromIterable(langs).log();

        return flux.flatMap(l -> Mono.just(l + " lang"));
    }

    private Flux<String> skip(){
        var langs = List.of("java", "rust", "cpp", "js");
        var flux = Flux.fromIterable(langs).log();

//        return flux.skip(2);
        return flux.skip(5);
    }

    private Flux<String> delay(){
        var langs = List.of("java", "rust", "cpp", "js");
        var flux = Flux.fromIterable(langs)
                .delayElements(Duration.ofSeconds(1));

//        return flux.delayElements(Duration.ofSeconds(1));
        return flux.skip(Duration.ofSeconds(2));
    }

    private Flux<Integer> merge(){
        var flux1 = Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
        var flux2 = Flux.range(31, 10).delayElements(Duration.ofSeconds(1));

        return Flux.merge(flux1, flux2);
    }

    private Flux<Tuple2<Integer, Integer>> zip(){
        var flux1 = Flux.range(1, 5).delayElements(Duration.ofSeconds(1));
        var flux2 = Flux.range(31, 2).delayElements(Duration.ofSeconds(1));

        return Flux.zip(flux1, flux2);
    }

    private Mono<List<Integer>> collect(){
        var flux = Flux.range(1, 5);

        return flux.collectList();
    }

    private Mono<Map<Integer, Integer>> collectMap(){
        var flux = Flux.range(1, 10);
        return flux.collectMap(a -> a,ab -> ab*ab);
    }

    private Flux<List<Integer>> buffer(){
        var flux = Flux.range(1, 10).delayElements(Duration.ofSeconds(1));

//        return flux.buffer();
//        return flux.buffer(3);
        return flux.buffer(Duration.ofSeconds(2));
    }

    private Flux<Integer> doOnEach(){
        var flux = Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
        return flux.doOnEach(signal -> System.out.println(signal));
    }

    private Flux<Integer> doOnCancel(){
        var flux = Flux.range(1, 10).delayElements(Duration.ofSeconds(1));
        return flux.doOnEach(signal -> System.out.println(signal));
    }

    private Flux<Integer> doOnError(){
        var flux = Flux.range(1, 10)
                .map(i ->{
                    if(i == 5)
                        throw new IllegalArgumentException("error occurred on 5");

                    return i;
                });

//        return flux.onErrorContinue(((throwable, o) -> System.out.println("dont worry: "+o)));
        return flux.onErrorReturn(-1);
    }

    private static void run() {
        var rl = new ReactiveLearn();
//        rl.doOnEach().subscribe(d -> System.out.println(d));
//        var disposable = rl.doOnCancel().subscribe(d -> System.out.println(d));
        rl.doOnError().subscribe(d -> System.out.println(d));

        System.out.println("ok");
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        disposable.dispose();
    }
}
