package com.findmypet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    private List<PostDto> posts;

    private List<LikeDto> givenLikes;

    private List<CommentDto> comments;
}
