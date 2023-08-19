package com.findmypet.dtos;

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

    private String text;

    private String date;

    private String hour;

    private Long userId;

    private Long postId;
}
