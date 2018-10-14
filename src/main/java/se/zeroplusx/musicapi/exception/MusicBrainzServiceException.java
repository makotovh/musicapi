package se.zeroplusx.musicapi.exception;

public class MusicBrainzServiceException extends MusicApiException {

    public MusicBrainzServiceException(String msg) {
        super(msg);
    }

    public MusicBrainzServiceException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
