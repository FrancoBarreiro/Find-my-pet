package com.findmypet.controllers;

import com.findmypet.persistence.entities.Like;
import com.findmypet.services.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/likes")
public class LikeController {

    @Autowired
    private ILikeService likeService;

    @PostMapping
    public ResponseEntity<?> addLike(@RequestBody Like like){
        return ResponseEntity.ok(likeService.addLike(like));
    }

}
