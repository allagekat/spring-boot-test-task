package com.example.controller;

import com.example.model.Album;
import com.example.model.User;
import com.example.service.AlbumService;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;
    private final UserService userService;

    public AlbumController(AlbumService albumService, UserService userService) {
        this.albumService = albumService;
        this.userService = userService;
    }

    @GetMapping
    public List<Album> getAll() {
        return albumService.findAllAlbums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getById(@PathVariable Long id) {
        Album album = albumService.findAlbumById(id);
        if(album!=null) {
            return ResponseEntity.ok(album);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Album>> getAlbumsByUserId(@PathVariable Long userId) {
        if (userService.findUserById(userId)==null) {
            return ResponseEntity.notFound().build();
        }
        List<Album> albums = albumService.findAlbumsByUserId(userId);
        return ResponseEntity.ok(albums);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album) {
        return albumService.saveAlbum(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }


    // внешнее api

    @GetMapping("/external")
    public ResponseEntity<List<Album>> getExternalAlbums() {
        return ResponseEntity.ok(albumService.getAllExternalAlbums());
    }

    @GetMapping("/external/user/{userId}")
    public ResponseEntity<List<Album>> getExternalAlbumsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(albumService.getExternalAlbumByUserId(userId));
    }

    @GetMapping("/external/{id}")
    public ResponseEntity<Album> getExternalAlbumById(@PathVariable Long id) {
        Album album = albumService.getExternalAlbumById(id);
        return album != null ?
                ResponseEntity.ok(album) : ResponseEntity.notFound().build();
    }
}
