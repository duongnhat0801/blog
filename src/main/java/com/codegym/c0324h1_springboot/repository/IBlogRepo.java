package com.codegym.c0324h1_springboot.repository;

import com.codegym.c0324h1_springboot.model.Blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBlogRepo extends JpaRepository<Blog, Long> {
    @Query(nativeQuery = true, value = "select * from blog as s where s.title like :title")
    Page<Blog> findAllTitle(String title, Pageable pageable);
}
