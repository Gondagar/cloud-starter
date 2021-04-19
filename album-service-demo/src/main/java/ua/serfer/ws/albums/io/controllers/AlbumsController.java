/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.serfer.ws.albums.io.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.serfer.ws.albums.data.AlbumEntity;
import ua.serfer.ws.albums.service.AlbumsService;
import ua.serfer.ws.albums.service.AlbumsServiceImpl;
import ua.serfer.ws.albums.ui.model.AlbumResponseModel;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class AlbumsController {

    @Autowired
    AlbumsServiceImpl albumsService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/{id}/albums", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<AlbumResponseModel> userAlbums(@PathVariable String id) {

        List<AlbumResponseModel> returnValue = new ArrayList<>();

        List<AlbumEntity> albumsEntities = albumsService.getAlbums(id);

        if (albumsEntities == null || albumsEntities.isEmpty()) {
            return returnValue;
        }

        Type listType = new TypeToken<List<AlbumResponseModel>>() {
        }.getType();

        returnValue = new ModelMapper().map(albumsEntities, listType);
        logger.info("Returning " + returnValue.size() + " albums");
        return returnValue;
    }

    @GetMapping(path = "/test", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity testDemo() {
        System.out.println("Demo");
        return new ResponseEntity("Demo", HttpStatus.OK);
    }

}
