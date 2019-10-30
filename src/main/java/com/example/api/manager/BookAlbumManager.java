package com.example.api.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BookAlbumManager {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BookAlbumManager.class);

    public ResponseEntity<Object> manageResponse(CompletableFuture<ResponseEntity> eventualBookResponse,
                                                 CompletableFuture<ResponseEntity> eventualAlbumResponse) throws ExecutionException, InterruptedException {
        if (eventualAlbumResponse.get().getStatusCode() == HttpStatus.OK
                && eventualBookResponse.get().getStatusCode() == HttpStatus.OK) {
            LOGGER.info("Receive ok status from both downstream service");
            return new ResponseEntity<>(Stream.of(eventualAlbumResponse.get().getBody(),
                    eventualBookResponse.get().getBody()).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            LOGGER.error("Downstream service for album failed with status code "+ eventualAlbumResponse.get().getStatusCode());
            LOGGER.error("Downstream service for books failed with status code "+ eventualBookResponse.get().getStatusCode());
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }
}
