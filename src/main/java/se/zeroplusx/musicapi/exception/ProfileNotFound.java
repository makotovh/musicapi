package se.zeroplusx.musicapi.exception;

import org.springframework.web.reactive.function.client.WebClientException;

public class ProfileNotFound extends WebClientException {

    public ProfileNotFound(String msg) {
        super(msg);
    }

    public ProfileNotFound(String msg, Throwable ex) {
        super(msg, ex);
    }
}
