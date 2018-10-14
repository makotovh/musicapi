package se.zeroplusx.musicapi.exception;

public class ProfileNotFound extends MusicApiException {

    public ProfileNotFound(String msg) {
        super(msg);
    }

    public ProfileNotFound(String msg, Throwable ex) {
        super(msg, ex);
    }
}
