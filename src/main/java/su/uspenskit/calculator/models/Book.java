package su.uspenskit.calculator.models;

import org.hibernate.mapping.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty()
    private String title;
    private String description;
    @Max(2022)
    @Min(1900)
    private int year;

    @Positive
    private int cost;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    public Book() { }

    public Book(String title, String description, int year, int cost, Author author) {
        this.setTitle(title);
        this.setDescription(description);
        this.setYear(year);
        this.setCost(cost);
        this.setAuthor(author);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
