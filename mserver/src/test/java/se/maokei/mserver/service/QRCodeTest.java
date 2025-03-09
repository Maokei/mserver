package se.maokei.mserver.service;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import se.maokei.mserver.TestcontainersConfiguration;
import se.maokei.mserver.services.QRCodeService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class QRCodeTest {
  @Autowired
  private QRCodeService qrCodeService;

  @Test
  public void generateQrCodeTest() {
      String fileName = "qr_code_test";
      qrCodeService.generate("https://maokei.se", 250, 250).doOnNext(data -> {
          try {
              final Path path = Files.createTempFile(fileName, ".png");
              Files.write(path, data);
              path.toFile().deleteOnExit();

              File file = new File(path.toUri());
              Assert.isTrue(file.exists());
              file.delete();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }).subscribe();
  }
}