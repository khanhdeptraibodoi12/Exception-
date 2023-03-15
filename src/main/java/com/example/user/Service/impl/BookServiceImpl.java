package com.example.user.Service.impl;

import com.example.user.Entity.Author;
import com.example.user.Entity.Book;
import com.example.user.Repository.AuthorRepository;
import com.example.user.Repository.BookRepository;
import com.example.user.Service.BookStoreService;
import com.example.user.dto.AssignAuthorToBookRequestDto;
import com.example.user.error.DeleteAuthorException;
import com.example.user.error.UserNotFoundException;
import jakarta.persistence.PreRemove;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookStoreService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book newBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        Optional<Author> optionalAuthor =  authorRepository.findById(id);
        if(optionalAuthor.isEmpty()) throw  new UserNotFoundException(id);
        return optionalAuthor.get();
    }

    @Override
    public Book addBookToAuthor(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).get();
        Author author  =  authorRepository.findById(authorId).get();
        book.getListAuthor().add(author);
        return bookRepository.save(book);
    }

    @Override
    public Author newAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {

        Author author = authorRepository.findById(id).get();
        List<Book> bookList = author.getListBook();
        for (Book book : new ArrayList<Book>(bookList)) {
            book.removeAuthor(author);
            if (book.getListAuthor().isEmpty()) {
                bookRepository.delete(book);
            }
        }
        authorRepository.deleteById(id);

    }

    @Override
    public void updateAuthor(Long id,String name) {
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()) throw new UserNotFoundException(id);
        Author author1 =  author.get();
        author1.setAuthorName(name);
        authorRepository.save(author1);
    }

    @Override
    public List<Book> getBookToAuthors(Long id) {
       Author author =  authorRepository.findById(id).get();
      return author.getListBook();
    }

    @Override
    public Book assignAuthorToBook(AssignAuthorToBookRequestDto assignAuthorToBookRequestDto) {
        Book book = bookRepository.findById(assignAuthorToBookRequestDto.bookId).get();
        Author author = authorRepository.findById(assignAuthorToBookRequestDto.authorId).get();
        book.getListAuthor().add(author);
        return bookRepository.save(book);

    }


}
