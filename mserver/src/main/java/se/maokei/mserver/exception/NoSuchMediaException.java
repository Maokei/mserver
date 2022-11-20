package se.maokei.mserver.exception;

import java.io.IOException;

public class NoSuchMediaException extends IOException {

    public NoSuchMediaException(String media) {
        super(String.format("No such media %s", media));
    }
}
