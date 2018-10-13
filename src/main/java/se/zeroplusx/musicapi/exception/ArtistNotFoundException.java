package se.zeroplusx.musicapi.exception;

import org.springframework.web.reactive.function.client.WebClientException;

public class ArtistNotFoundException extends WebClientException {

    public ArtistNotFoundException(String msg) {
        super(msg);
    }

    public ArtistNotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
