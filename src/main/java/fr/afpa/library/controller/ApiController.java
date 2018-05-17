package fr.afpa.library.controller;

import fr.afpa.library.model.Book;
import fr.afpa.library.model.Genre;
import fr.afpa.library.service.BookService;
import fr.afpa.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public List<Book> books(){
        return bookService.findAll();
    }
}
