package com.alphabare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogUpdateRequest {
    private String blogHeading;
    private String blogContent;

    
}
