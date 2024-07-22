package com.codegym.c0324h1_springboot.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BlogFormCreateDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private MultipartFile avatar;
}
