package se.zeroplusx.musicapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MusicBrainzServiceTest {

    private MusicBrainzService musicBrainzService = new MusicBrainzService();

    @Test(expected = Exception.class)
    public void testShouldReturnErrorWhenPassedANullMBID() {
        musicBrainzService.getArtist(null);
    }

    @Test(expected = Exception.class)
    public void testShouldReturnErrorWhenPassedAEmptyMBID() {
        musicBrainzService.getArtist("");
    }

}
