package se.zeroplusx.musicapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends MusicApiException {

    public ArtistNotFoundException(String msg) {
        super(msg);
    }

    public ArtistNotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
