package com.findmypet.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {

    private Long id;
    @NotBlank
    private String type;
    @NonNull
    private Long sender;
    @NonNull
    private Long recipient;
    @NotBlank
    private String date;
    @NotBlank
    private String hour;

    private boolean isRead;
    @NonNull
    private Long postId;
}
