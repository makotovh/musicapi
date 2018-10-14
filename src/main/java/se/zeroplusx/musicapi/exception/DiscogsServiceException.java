package se.zeroplusx.musicapi.exception;

public class DiscogsServiceException extends MusicApiException {
    public DiscogsServiceException(String msg) {
        super(msg);
    }

    public DiscogsServiceException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
