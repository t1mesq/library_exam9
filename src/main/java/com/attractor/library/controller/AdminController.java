package com.attractor.library.controller;

import com.attractor.library.entity.Book;
import com.attractor.library.entity.Category;
import com.attractor.library.entity.User;
import com.attractor.library.service.BookService;
import com.attractor.library.service.CategoryService;
import com.attractor.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String adminDashboard(Model model) {
        List<Book> books = bookService.getAllBooks();
        List<User> users = userService.getAllUsers();
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("books", books);
        model.addAttribute("users", users);
        model.addAttribute("categories", categories);
        return "admin";
    }
}
