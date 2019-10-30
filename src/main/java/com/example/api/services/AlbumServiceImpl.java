package com.example.api.services;

import com.example.api.domains.Album;
import com.example.api.mapper.AlbumMapper;
import com.example.api.utils.AlbumTitleSorter;
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
public class AlbumServiceImpl implements AlbumService {

    @Value(value = "${album.api}")
    private String baseUrl;

    @Autowired
    ConsumerEndPoint consumerEndPoints;

    @Autowired
    AlbumMapper albumMapper;

    @Autowired
    TrackCounterService trackCounterService;

    RestTemplate restTemplate = new RestTemplate();

    protected static final Logger LOGGER = LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAlbums(String query, String sortOrder) {
        long startTime = System.currentTimeMillis();
        trackCounterService.startTracking();
        ResponseEntity<String> albumResponse = restTemplate.getForEntity(baseUrl + consumerEndPoints.getAlbumEndPoint() + query, String.class);
        trackCounterService.stopTracking(startTime, albumResponse.getStatusCode());
        if (albumResponse.getStatusCode() == HttpStatus.OK) {
            LOGGER.info("Receive ok response from album api");
            List<Album> albums = albumMapper.mapAlbums(albumResponse.getBody());
            AlbumTitleSorter.sortByTitle(albums, sortOrder);
            return CompletableFuture.completedFuture(new ResponseEntity<>(albums, HttpStatus.OK));
        } else {
            return CompletableFuture.completedFuture(albumResponse);
        }
    }
}
