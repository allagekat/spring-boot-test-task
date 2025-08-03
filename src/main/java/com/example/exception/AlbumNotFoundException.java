package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(Long albumId) {
        this("Album not found with id: " + albumId);;
    }

    public AlbumNotFoundException(String message) {
        super(message);
    }
}
