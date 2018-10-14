# musicapi

# Technologies used

 - Java 10
 - Spring Boot
 - Undertow
 - Lombok
 - EhCache
 - WebFlux (WebClient)
 - Google Guava

# Shortcut

I found on the documentation of "the music brainz cover art archive api" the endpoint "/release-group/{mbid}/front" that returns a front image of the album.
Than I decided to use it to avoid multiples call to that api and get a better performance. I used the mbid of the album to build a url of the endpoint on the Album class.


