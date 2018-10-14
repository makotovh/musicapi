package se.zeroplusx.musicapi.exception;

public class ArtistNotFoundException extends MusicApiException {

    public ArtistNotFoundException(String msg) {
        super(msg);
    }

    public ArtistNotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
