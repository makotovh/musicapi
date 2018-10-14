# MusicApi

## Technologies used

 - Java 10
 - Spring Boot
 - Undertow
 - Lombok
 - EhCache
 - WebFlux (WebClient)
 - Google Guava

## Shortcut

I found on the documentation of "the music brainz cover art archive api" the endpoint "/release-group/{mbid}/front" that returns a front image of the album.
Than I decided to use it to avoid multiples call to that api and get a better performance. I used the mbid of the album to build a url of the endpoint on the Album class.

## Run Tests

`mvn clean test`

## Run Application

`mvn spring-boot:run`

## Usage

Open `http://localhost:8080/artists/29b550f2-04a3-4733-b6f5-4e347fac7e11` on the browser or Postman


### MBIDs for test 

```
5b11f4ce-a62d-471e-81fc-a69a8278c7da
0383dadf-2a4e-4d10-a46a-e9e041da8eb3
65f4f0c5-ef9e-490c-aee3-909e7ae6b2ab
ca891d65-d9b0-4258-89f7-e6ba29d83767
1c80f885-e832-4b84-864b-5deeda6a1248
29b550f2-04a3-4733-b6f5-4e347fac7e11
```


 




