package com.codegym.c0324h1_springboot.service;

import com.codegym.c0324h1_springboot.model.Blog;

public interface IBlogService {
    Iterable<Blog> findAll();
    void save(Blog blog);
    Blog findById(Long id);
    void delete(Long id);
    void update(Blog blog);
}

