package com.findmypet.dtos;

import com.findmypet.utils.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    private String description;

    private PostType type;

    private List<String> imageUrls;

    private String date;

    private Double latitude;

    private Double longitude;

    private UserDto user;

    private List<CommentDto> comments;

    private List<LikeDto> likes;
}
