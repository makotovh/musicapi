package se.zeroplusx.musicapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class ProfileNotFound extends MusicApiException {

    public ProfileNotFound(String msg) {
        super(msg);
    }

    public ProfileNotFound(String msg, Throwable ex) {
        super(msg, ex);
    }
}
