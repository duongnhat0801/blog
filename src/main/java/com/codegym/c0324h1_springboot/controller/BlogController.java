package com.codegym.c0324h1_springboot.controller;

import com.codegym.c0324h1_springboot.dto.BlogFormCreateDTO;
import com.codegym.c0324h1_springboot.dto.BlogFormUpdateDTO;
import com.codegym.c0324h1_springboot.model.Blog;
import com.codegym.c0324h1_springboot.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Value("${upload.path}")
    private String fileUpload;

    @Autowired
    private IBlogService blogService;

    @GetMapping("")
    public String home(Model model) {
        Iterable<Blog> blogList = blogService.findAll();
        model.addAttribute("blogList", blogList);
        return "home";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        Iterable<Blog> blogList = blogService.findAll();
        model.addAttribute("blogList", blogList);
        return "list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("blogFormCreateDto", new BlogFormCreateDTO());
        return "create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute BlogFormCreateDTO blogFormCreateDto,
                       RedirectAttributes redirect) {
        MultipartFile multipartFile = blogFormCreateDto.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            System.out.println(fileUpload + fileName);
            FileCopyUtils.copy(blogFormCreateDto.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Blog blog = new Blog(blogFormCreateDto.getId(), blogFormCreateDto.getTitle(), blogFormCreateDto.getContent(), blogFormCreateDto.getAuthor(), fileName);
        blogService.save(blog);
        redirect.addFlashAttribute("noti", "Thêm mới thành công!");
        return "redirect:/blog";
    }

    @GetMapping("{id}/content")
    public String showContent(Model model, @PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "redirect:/blog";
        }
        model.addAttribute("blog", blog);
        return "content";
    }

    @GetMapping("{id}/delete")
    public String showFormDelete(Model model, @PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "redirect:/blog/list";
        }
        model.addAttribute("blog", blog);
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Blog blog, RedirectAttributes redirect) {
        blogService.delete(blog.getId());
        redirect.addFlashAttribute("noti", "Xóa thành công!");
        return "redirect:/blog/list";
    }

    @GetMapping("{id}/update")
    public String showFormUpdate(Model model, @PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "redirect:/blog/list";
        }
        model.addAttribute("blog", blog);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BlogFormUpdateDTO blogFormUpdateDto, RedirectAttributes redirect) {
        MultipartFile multipartFile = blogFormUpdateDto.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            System.out.println(fileUpload + fileName);
            FileCopyUtils.copy(blogFormUpdateDto.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Blog blog = new Blog(blogFormUpdateDto.getId(), blogFormUpdateDto.getTitle(), blogFormUpdateDto.getContent(), blogFormUpdateDto.getAuthor(), fileName);
        blogService.save(blog);
        redirect.addFlashAttribute("noti", "Cập nhật thành công!");
        return "redirect:/blog/list";
    }
}
