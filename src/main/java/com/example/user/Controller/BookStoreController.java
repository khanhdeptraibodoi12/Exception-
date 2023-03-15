package com.example.user.Controller;

import com.example.user.Entity.Author;
import com.example.user.Entity.Book;
import com.example.user.Repository.AuthorRepository;
import com.example.user.Service.BookStoreService;
import com.example.user.dto.AssignAuthorToBookRequestDto;
import com.example.user.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookStore")
public class BookStoreController {

    @Autowired
    BookStoreService bookStoreService;
    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public Page<Book> getAllBooks(Pageable pageable){
        return bookStoreService.getAllBooks(pageable);
    }
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public Book newBook(@RequestBody Book book){
        return bookStoreService.newBook(book);
    }


    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id) {
        bookStoreService.deleteBook(id);
    }


    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable Long id){
        return bookStoreService.getAuthorById(id);
    }


    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors(){
        return bookStoreService.getAllAuthor();
    }



    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public Author newAuthor(@RequestBody Author author){
        return bookStoreService.newAuthor(author);
    }



    @PostMapping("/{bookId}/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    public Book addBookToAuthor(@PathVariable Long bookId,@PathVariable Long authorId) {
        return bookStoreService.addBookToAuthor(bookId,authorId);
    }

    @PostMapping("/assign")
    @ResponseStatus(HttpStatus.OK)
    public Book assignAuthorToBook(@RequestBody AssignAuthorToBookRequestDto assignAuthorToBookRequestDto) {
        return bookStoreService.assignAuthorToBook(assignAuthorToBookRequestDto);
    }


    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@PathVariable Long id) {
         bookStoreService.deleteAuthor(id);
    }

    @PatchMapping("/authors/{id}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAuthor(@PathVariable Long id,@PathVariable String name) {
        bookStoreService.updateAuthor(id,name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBookToAuthors(@PathVariable Long id) {
       return bookStoreService.getBookToAuthors(id);
    }
}
