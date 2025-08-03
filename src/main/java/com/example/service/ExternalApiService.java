package com.example.service;

import com.example.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ExternalApiService {
    private final RestTemplate restTemplate;
    private static final String URL_API = "https://jsonplaceholder.typicode.com/albums";

    @Autowired
    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Album> fetchAlbumsFromExternalApi() {
        Album[] albums = restTemplate.getForObject(URL_API, Album[].class);
        return Arrays.asList(albums != null ? albums : new Album[0]);
    }

    public List<Album> fetchAlbumsByUserIdFromExternalApi(Long userId) {
        String url = URL_API + "?userId=" + userId;
        System.out.println("Making request to: " + url);

        Album[] albums = restTemplate.getForObject(url, Album[].class);
        System.out.println("Received: " + Arrays.toString(albums));

        return albums != null ? Arrays.asList(albums) : Collections.emptyList();
    }

    public Album fetchAlbumByIdFromExternalApi(Long albumId) {
       return restTemplate.getForObject(URL_API + "/" + albumId, Album.class);
    }
}
