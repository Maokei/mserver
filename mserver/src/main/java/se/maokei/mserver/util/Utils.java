package se.maokei.mserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Utils {

    /**
     * calculateMD5Hash
     * */
    public static byte[] calculateMD5Hash(String filePath) throws NoSuchAlgorithmException, IOException {
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        byte[] hash = MessageDigest.getInstance("MD5").digest(data);
        String checksum = new BigInteger(1, hash).toString(16);
        return checksum.getBytes();
    }


    public static <T> String toJson(T obj) {
        String json = null;
        if (obj != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                json = objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                log.warn(e.getMessage(), e);
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return json;
    }
}
