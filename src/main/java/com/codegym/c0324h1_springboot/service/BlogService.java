package com.codegym.c0324h1_springboot.service;

import com.codegym.c0324h1_springboot.model.Blog;
import com.codegym.c0324h1_springboot.repository.IBlogRepo;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepo blogRepo;

    @Override
    public Page<Blog> findAll() {
        return (Page<Blog>) blogRepo.findAll();
    }

    @Override
    public void save(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        blogRepo.deleteById(id);
    }

    @Override
    public void update(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public Page<Blog> findAllTitle(HtmlStyle htmlStyle, PageRequest of) {
        return blogRepo.findAllTitle(title);
    }
}
