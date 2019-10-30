package com.example.api.controllers;


import com.example.api.manager.BookAlbumManager;
import com.example.api.services.AlbumService;
import com.example.api.services.BookService;
import com.example.api.utils.ServiceEndPoint;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@Timed
public class BookAlbumController {

    @Autowired
    BookService bookService;

    @Autowired
    AlbumService albumService;

    @Autowired
    BookAlbumManager bookAlbumManager;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BookAlbumController.class);

    @ApiOperation(value = "list of available book and album based on input", response = List.class)
    @GetMapping(ServiceEndPoint.BOOKS_ALBUMS)
    public ResponseEntity getBookAlbumDetails(@RequestParam String inputQuery,
                                              @RequestParam(required = false) Optional<String> sortBy)
            throws ExecutionException, InterruptedException {
        LOGGER.info("Receive api call for books and album controller");
        CompletableFuture<ResponseEntity> eventualBookResponse = bookService.getBooks(inputQuery, sortBy.orElse("title"));
        CompletableFuture<ResponseEntity> eventualAlbumResponse = albumService.getAlbums(inputQuery, sortBy.orElse("title"));
        return bookAlbumManager.manageResponse(eventualBookResponse, eventualAlbumResponse);
    }
}
