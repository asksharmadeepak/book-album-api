package com.example.api.services;

import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface AlbumService {

    CompletableFuture<ResponseEntity> getAlbums(String query, String sortOrder);
}
