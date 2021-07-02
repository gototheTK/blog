package com.cos.blog.dto;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RestController
@Builder
public class ResponseDto<T> {
    int status;
    T data;
}
