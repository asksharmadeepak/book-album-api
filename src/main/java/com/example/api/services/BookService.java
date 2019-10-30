package com.example.api.services;

import org.springframework.http.ResponseEntity;
import java.util.concurrent.CompletableFuture;

public interface BookService {

    CompletableFuture<ResponseEntity> getBooks(String query, String sortOrder);
}