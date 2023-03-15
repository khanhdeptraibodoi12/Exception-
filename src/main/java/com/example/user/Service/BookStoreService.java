package com.example.user.Service;
import com.example.user.Entity.Author;
import com.example.user.Entity.Book;
import com.example.user.dto.AssignAuthorToBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BookStoreService {
    public Page<Book> getAllBooks(Pageable pageable);
    public Book newBook(Book book);
    public void deleteBook(Long id);
    public List<Author> getAllAuthor();
    public Author getAuthorById(Long id);
    public Book addBookToAuthor(Long bookId, Long authorId);
    public Author newAuthor(Author author);
    public void deleteAuthor(Long id);

    public void updateAuthor(Long id,String name);
    public List<Book> getBookToAuthors(Long id);
    public Book assignAuthorToBook(AssignAuthorToBookRequestDto assignAuthorToBookRequestDto);


}
