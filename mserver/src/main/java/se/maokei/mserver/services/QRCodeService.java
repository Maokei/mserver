package se.maokei.mserver.services;

import reactor.core.publisher.Mono;

public interface QRCodeService {
  Mono<byte[]> generate(String data);
  Mono<byte[]> generate(String data, int width, int height);
}
