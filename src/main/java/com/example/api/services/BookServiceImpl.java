package com.example.api.services;

import com.example.api.domains.Book;
import com.example.api.mapper.BookMapper;
import com.example.api.utils.BookTitleSorter;
import com.example.api.utils.ConsumerEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BookServiceImpl implements BookService {

    @Value(value = "${book.api}")
    public String baseUrl;

    @Autowired
    ConsumerEndPoint consumerEndPoints;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    TrackCounterService trackCounterService;

    RestTemplate restTemplate = new RestTemplate();

    protected static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getBooks(String query, String sortOrder) {
        long startTime = System.currentTimeMillis();
        trackCounterService.startTracking();
        ResponseEntity<String> booksResponse = restTemplate.getForEntity(baseUrl + consumerEndPoints.getBookEndPoint() + query, String.class);
        trackCounterService.stopTracking(startTime, booksResponse.getStatusCode());
        if (booksResponse.getStatusCode() == HttpStatus.OK) {
            LOGGER.info("Receive ok response from books api");
            List<Book> books = bookMapper.mapBooks(booksResponse.getBody());
            BookTitleSorter.sortByTitle(books, sortOrder);
            return CompletableFuture.completedFuture(new ResponseEntity<>(books, HttpStatus.OK));
        } else {
            return CompletableFuture.completedFuture(booksResponse);
        }
    }
}
