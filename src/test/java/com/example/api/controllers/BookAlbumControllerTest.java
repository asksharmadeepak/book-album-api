package com.example.api.controllers;

import com.example.api.manager.BookAlbumManager;
import com.example.api.services.AlbumService;
import com.example.api.services.BookService;
import com.example.api.utils.ServiceEndPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.CompletableFuture;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookAlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @MockBean
    AlbumService albumService;

    @MockBean
    BookAlbumManager bookAlbumManager;

    CompletableFuture<ResponseEntity> bookApiResponse;
    CompletableFuture<ResponseEntity> albumApiResponse;

    @Before
    public void setUp() {
        bookApiResponse = CompletableFuture.supplyAsync(() -> new ResponseEntity("book", HttpStatus.OK));
        albumApiResponse = CompletableFuture.supplyAsync(() -> new ResponseEntity("album", HttpStatus.OK));
    }

    @Test
    public void shouldReturnOkResponseWithBooksAndAlbums() throws Exception {
        String input = "katy";
        Mockito.when(bookService.getBooks(input, "title")).thenReturn(bookApiResponse);
        Mockito.when(albumService.getAlbums(input, "title")).thenReturn(albumApiResponse);
        Mockito.when(bookAlbumManager.manageResponse(bookApiResponse, albumApiResponse)).thenReturn(new ResponseEntity("book+album", HttpStatus.OK));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ServiceEndPoint.BOOKS_ALBUMS).param("inputQuery", input);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorResponseWhenRequestParamIsIncorrect() throws Exception {
        String input = "katy perry";
        Mockito.when(bookService.getBooks(input, "title")).thenReturn(bookApiResponse);
        Mockito.when(albumService.getAlbums(input, "title")).thenReturn(albumApiResponse);
        Mockito.when(bookAlbumManager.manageResponse(bookApiResponse, albumApiResponse)).thenReturn(new ResponseEntity("book+album", HttpStatus.OK));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ServiceEndPoint.BOOKS_ALBUMS).requestAttr("q", input);

        mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
    }

}