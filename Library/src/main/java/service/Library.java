package service;

import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.title().equals(title));
    }

    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(book -> book.title().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> listBooks() {
        return new ArrayList<>(books);
    }

    public List<Book> searchByAuthor(String author) {
        return books.stream()
                .filter(book -> book.author().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByGenre(String genre) {
        return books.stream()
                .filter(book -> book.genre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByYear(int year) {
        return books.stream()
                .filter(book -> book.year() == year)
                .collect(Collectors.toList());
    }

    public List<Book> searchByMultipleParams(String title, String author, String genre, Integer year) {
        return books.stream()
                .filter(book -> title == null || book.title().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> author == null || book.author().toLowerCase().contains(author.toLowerCase()))
                .filter(book -> genre == null || book.genre().toLowerCase().contains(genre.toLowerCase()))
                .filter(book -> year == null || book.year() == year)
                .collect(Collectors.toList());
    }


}