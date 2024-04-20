package se.maokei.mserver.api.v1.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.AuthRequest;
import se.maokei.mserver.dto.AuthResponse;
import se.maokei.mserver.security.JwtUtility;
import se.maokei.mserver.services.UserService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthController {
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());
  private JwtUtility jwtUtil;

  //private PBKDF2Encoder passwordEncoder;
  private PasswordEncoder passwordEncoder;
  private UserService userService;

  @PostMapping("/login")
  public Mono<ResponseEntity<AuthResponse>> login(@Valid @RequestBody AuthRequest ar) {
    LOGGER.debug("Login AuthRequest dto {}", ar);
    return userService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.matches(ar.getPassword(), userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
  }

  @GetMapping(value="qr", produces = MediaType.IMAGE_JPEG_VALUE)
  public Mono<ServerResponse> generateQRCodeImage() throws Exception {

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode("Hello", BarcodeFormat.QR_CODE, 250, 250);

    //return MatrixToImageWriter.toBufferedImage(bitMatrix);
    //MatrixToImageWriter.toBufferedImage(bitMatrix).get
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "jpg", baos);
    byte[] bytes = baos.toByteArray();

    DataBuffer buffer = new DefaultDataBufferFactory().wrap(bytes);

    return ServerResponse
            .ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(BodyInserters.fromDataBuffers(Flux.just(buffer)));
  }
}
