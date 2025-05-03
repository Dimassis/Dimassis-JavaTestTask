package model;

import java.util.List;

public record Hotel(
        String name,
        List<Room> rooms
) {
}
