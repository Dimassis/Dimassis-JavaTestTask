package model;

import java.util.Objects;

public record Book(
        String title,
        String author,
        String genre,
        int year
) {
}