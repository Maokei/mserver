package se.maokei.mserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Utils {

    /**
     * calculateMD5Hash
     * */
    public static byte[] calculateMD5Hash(String filePath) throws NoSuchAlgorithmException, IOException {
        filePath = filePath.replace("file:", "");
        File target = new File(filePath);
        if (target.exists()) {
            byte[] data = Files.readAllBytes(Paths.get(filePath));
            byte[] hash = MessageDigest.getInstance("MD5").digest(data);
            String checksum = new BigInteger(1, hash).toString(16);
            return checksum.getBytes();
        }
        return "".getBytes();
    }

    public static long calculateFileSize(String filePath) throws IOException {
        filePath = filePath.replace("file:", "");
        Path path = Paths.get(filePath);
        try (FileChannel fileChannel = FileChannel.open(path)) {
            return fileChannel.size();
        }
    }

    public static void getSizeOfFile(String pathname) {
        File file = new File(pathname);
        if (file.exists()) {
            // File size in bytes
            long bytes = file.length();
            double kilobytes = (double) bytes / 1024;
            double megabytes = kilobytes / 1024;
            double gigabytes = megabytes / 1024;
            System.out.println("File size in bytes: " + bytes + " bytes");
            System.out.println("File size in kilobytes: " + kilobytes + " KB");
            System.out.println("File size in megabytes: " + megabytes + " MB");
            System.out.println("File size in gigabytes: " + gigabytes + " GB");
        } else {
            System.out.println("File not found.");
        }
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
