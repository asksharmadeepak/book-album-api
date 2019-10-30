package com.example.api.services;

import com.example.api.mapper.BookMapper;
import com.example.api.utils.ConsumerEndPoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BookServiceIntegrationTest {

    @Autowired
    BookService bookService;

    @MockBean
    BookMapper bookMapper;

    @MockBean
    ConsumerEndPoint consumerEndPoint;

    @Test
    public void shouldReturnBooksResponse() throws ExecutionException, InterruptedException {
        Mockito.when(consumerEndPoint.getBookEndPoint()).thenReturn("/books/v1/volumes?&maxResults=1&q=");
        CompletableFuture<ResponseEntity> eventualBookApiResponse = bookService.getBooks("john","title");
        Assert.assertNotNull(eventualBookApiResponse.get().getBody());
        Assert.assertEquals(HttpStatus.OK, eventualBookApiResponse.get().getStatusCode());
    }
}