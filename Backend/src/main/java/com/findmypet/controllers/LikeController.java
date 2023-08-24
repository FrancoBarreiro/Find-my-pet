package com.findmypet.controllers;

import com.findmypet.dtos.LikeDto;
import com.findmypet.services.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/likes")
public class LikeController {

    @Autowired
    private ILikeService likeService;

    @PostMapping
    public ResponseEntity<?> addLike(@RequestBody LikeDto like) {

        likeService.addLike(like);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(like.getId())
                .toUri();

        return ResponseEntity.created(location).body(like);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getLikesByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.getLikesByPostId(postId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getLikesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(likeService.getLikesByUserId(userId));
    }

    @GetMapping("/check-like-status")
    public ResponseEntity<Boolean> checkLikeStatus(@RequestParam Long userId, @RequestParam Long postId) {
        boolean userHasLiked = likeService.userHasLikedPost(userId, postId);
        return ResponseEntity.ok(userHasLiked);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }
}
