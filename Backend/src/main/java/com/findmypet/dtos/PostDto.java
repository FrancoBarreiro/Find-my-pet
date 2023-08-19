package com.findmypet.dtos;

import com.findmypet.utils.PostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;
    @NotBlank
    private String description;
    @NotNull
    private PostType type;
    @NotEmpty
    private List<String> imageUrls;
    @NotBlank
    private String date;
    @NotBlank
    private String hour;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Long userId;

    private List<CommentDto> comments;

    private List<LikeDto> likes;
}
