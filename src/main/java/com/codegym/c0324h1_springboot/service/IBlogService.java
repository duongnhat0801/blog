package com.codegym.c0324h1_springboot.service;

import com.codegym.c0324h1_springboot.model.Blog;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBlogService {
    Page<Blog> findAll();
    void save(Blog blog);
    Blog findById(Long id);
    void delete(Long id);
    void update(Blog blog);

    Page<Blog> findAllTitle(HtmlStyle htmlStyle, PageRequest of);
}

