package com.example.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndPoint {

    @Value(value = "${book.max.results}")
    String booksMaxResults;

    @Value(value = "${album.max.results}")
    String albumMaxResults;

    public String getBookEndPoint() {
        return "/books/v1/volumes?&maxResults=" + booksMaxResults + "&q=";
    }

    public String getAlbumEndPoint() {
        return "/search?limit=" + albumMaxResults + "&term=";
    }
}
