package com.codegym.c0324h1_springboot.repository;

import com.codegym.c0324h1_springboot.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepo extends JpaRepository<Blog, Long> {
}
