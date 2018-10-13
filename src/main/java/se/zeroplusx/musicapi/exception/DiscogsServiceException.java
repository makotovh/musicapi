package se.zeroplusx.musicapi.exception;

import org.springframework.web.reactive.function.client.WebClientException;

public class DiscogsServiceException extends WebClientException {
    public DiscogsServiceException(String msg) {
        super(msg);
    }

    public DiscogsServiceException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
