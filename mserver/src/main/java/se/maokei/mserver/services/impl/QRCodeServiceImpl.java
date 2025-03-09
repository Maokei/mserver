package se.maokei.mserver.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import se.maokei.mserver.services.QRCodeService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeServiceImpl implements QRCodeService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Mono<byte[]> generate(String data) {
        return this.generate(data, 250, 250);
    }

    @Override
    public Mono<byte[]> generate(String data, int width, int height) {
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        return Mono.fromSupplier(() -> {
            try {
                return createQR(data, "UTF-8", hints, height, width);
            } catch (WriterException | IOException e) {
                log.error("Failed to generate QR code data: {} ", data);
                throw new RuntimeException(e);
            }
        });
    }

    public static byte[] createQR(String data,
                                String charset, Map<EncodeHintType, ErrorCorrectionLevel> hints,
                                int height, int width)
            throws WriterException, IOException
    {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height, hints);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", baos);
        return baos.toByteArray();
    }
}