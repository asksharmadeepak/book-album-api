package com.example.api.services;

import com.example.api.mapper.AlbumMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class AlbumServiceIntegrationTest {

    @Autowired
    AlbumService albumService;

    @MockBean
    AlbumMapper albumMapper;

    @Test
    public void shouldReturnSuccessForValidAlbumApiCall() throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity> eventualAlbumApiResponse = albumService.getAlbums("john", "title");
        Assert.assertNotNull(eventualAlbumApiResponse.get().getBody());
        Assert.assertEquals(HttpStatus.OK, eventualAlbumApiResponse.get().getStatusCode());
    }

}