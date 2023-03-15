package com.example.user.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;
    private String bookPrice;

    private Long count;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_Id", referencedColumnName = "bookId"), inverseJoinColumns = @JoinColumn(name = "author_Id", referencedColumnName = "authorId"))
    public List<Author> listAuthor = new ArrayList<>();


    public void removeAuthor (Author author) {
        this.listAuthor.remove(author);
        author.getListBook().remove(this);
    }

}
