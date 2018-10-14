package se.zeroplusx.musicapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiscogsServiceTest {

    private DiscogsService discogsService = new DiscogsService();

    @Test(expected = Exception.class)
    public void testShouldReturnErrorWhenPassedANullArtist() {
        discogsService.getProfileFormArtist(null);
    }
}