package com.product.application.dto.hasanboy;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDto {
    private Integer id;
    @NotBlank(message = ("The path cannot be empty or null"))
    private String path;
    @NotBlank(message = ("The type cannot be empty or null"))
    private String type;
    @NotBlank(message = ("The token cannot be empty or null"))
    private String token;
    private Long size;
}
