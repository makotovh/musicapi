package se.zeroplusx.musicapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DiscogsServiceTest {

    private DiscogsService discogsService = new DiscogsService();

    @Test
    void testShouldReturnErrorWhenPassedANullArtist() {
        Assertions.assertThrows(Exception.class, () -> discogsService.getProfileFormArtist(null));
    }
}