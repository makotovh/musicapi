package se.zeroplusx.musicapi.exception;

import org.springframework.web.reactive.function.client.WebClientException;

public class MusicBrainzServiceException extends WebClientException {

    public MusicBrainzServiceException(String msg) {
        super(msg);
    }

    public MusicBrainzServiceException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
