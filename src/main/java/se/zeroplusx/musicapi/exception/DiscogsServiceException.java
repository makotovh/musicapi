package se.zeroplusx.musicapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DiscogsServiceException extends MusicApiException {
    public DiscogsServiceException(String msg) {
        super(msg);
    }

    public DiscogsServiceException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
