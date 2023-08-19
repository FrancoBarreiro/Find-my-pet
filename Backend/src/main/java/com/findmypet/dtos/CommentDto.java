package com.findmypet.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private Long id;

    @NotBlank
    private String text;
    @NotBlank
    private String date;
    @NotBlank
    private String hour;
    @NotNull
    private Long userId;
    @NotNull
    private Long postId;
}
