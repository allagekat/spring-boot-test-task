package com.example.service;

import com.example.exception.AlbumNotFoundException;
import com.example.model.Album;
import com.example.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ExternalApiService externalApiService;

    @Autowired
    public AlbumService(AlbumRepository albumRepository,
                        ExternalApiService externalApiService) {
        this.albumRepository = albumRepository;
        this.externalApiService = externalApiService;
    }

    public Album findAlbumById(Long id) {
        return this.albumRepository.findById(id).orElse(null);
    }

    public List<Album> findAllAlbums() {
        return this.albumRepository.findAll();
    }

    public List<Album> findAlbumsByUserId(Long userId) {
        return this.albumRepository.findByUserId(userId);
    }

    public Album saveAlbum(Album album) {
        return this.albumRepository.save(album);
    }

    public void deleteAlbum(Long albumId) {
        if(!albumRepository.existsById(albumId))
            throw new AlbumNotFoundException(albumId);
        albumRepository.deleteById(albumId);
    }

    // внешнее api
    public List<Album> getAllExternalAlbums() {
        return externalApiService.fetchAlbumsFromExternalApi();
    }

    public List<Album> getExternalAlbumByUserId(Long userId) {
        return externalApiService.fetchAlbumsByUserIdFromExternalApi(userId);
    }

    public Album getExternalAlbumById(Long albumId) {
        return externalApiService.fetchAlbumByIdFromExternalApi(albumId);
    }
}