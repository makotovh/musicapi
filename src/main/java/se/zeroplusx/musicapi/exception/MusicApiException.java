package se.zeroplusx.musicapi.exception;

import org.springframework.web.reactive.function.client.WebClientException;

public abstract class MusicApiException extends WebClientException {

    public MusicApiException(String msg) {
        super(msg);
    }

    public MusicApiException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
