package com.example.user.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    private String authorName;
    @ManyToMany(mappedBy = "listAuthor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    public List<Book> listBook = new ArrayList<>();


    public void removeBook(Book book) {
        this.listBook.remove(book);
        book.getListAuthor().remove(this);
    }


}
