package com.product.application.filter.hasanboy;

import com.product.application.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageFilter extends FilterDto {
    private String path;
    private String type;
    private Long image_size;
    private String token;
}
