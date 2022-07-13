package se.maokei.mserver.services;

import reactor.core.publisher.Mono;

public interface QRCodeService {

  Mono<byte[]> generate(String text, int width, int height);
}
