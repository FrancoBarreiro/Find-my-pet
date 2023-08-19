package com.findmypet.controllers;

import com.findmypet.dtos.LikeDto;
import com.findmypet.services.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/likes")
public class LikeController {

    @Autowired
    private ILikeService likeService;

    @PostMapping
    public ResponseEntity<?> addLike(@RequestBody LikeDto like){

        likeService.addLike(like);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(like.getId())
                .toUri();

        return ResponseEntity.created(location).body(like);
    }

}
