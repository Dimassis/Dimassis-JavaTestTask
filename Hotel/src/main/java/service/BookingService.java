package service;

import model.Hotel;
import model.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final List<Room> bookedRooms = new ArrayList<>();
    private final List<LocalDate> checkInDates = new ArrayList<>();
    private final List<LocalDate> checkOutDates = new ArrayList<>();
    private final List<Hotel> hotels;

    public BookingService() {
        List<Room> rooms = List.of(
                new Room(1, "Economy", 1000),
                new Room(2, "Economy", 1000),
                new Room(3, "Economy", 1000),
                new Room(4, "Business", 2000),
                new Room(5, "Business", 2000),
                new Room(6, "VIP", 4000));

        this.hotels = List.of(
                new Hotel("Hotel1", new ArrayList<>(rooms)),
                new Hotel("Hotel2", new ArrayList<>(rooms)),
                new Hotel("Hotel3", new ArrayList<>(rooms)));
    }

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Даты не могут быть null");
        }
        if (checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("Дата заезда должна быть раньше даты выезда");
        }
        for (int i = 0; i < bookedRooms.size(); i++) {
            if (bookedRooms.get(i).equals(room)) {
                LocalDate bookedCheckIn = checkInDates.get(i);
                LocalDate bookedCheckOut = checkOutDates.get(i);

                if (!checkOut.isBefore(bookedCheckIn) && !checkIn.isAfter(bookedCheckOut)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean bookRoom(Hotel hotel, Room room, LocalDate checkIn, LocalDate checkOut) {
        if (hotel == null || room == null) {
            throw new IllegalArgumentException("Отель и номер не могут быть null");
        }
        if (!hotels.contains(hotel)) {
            throw new IllegalArgumentException("Отель не существует");
        }
        if (!hotel.rooms().contains(room)) {
            throw new IllegalArgumentException("Номер не принадлежит отелю");
        }

        if (!isRoomAvailable(room, checkIn, checkOut)) {
            return false;
        }
        bookedRooms.add(room);
        checkInDates.add(checkIn);
        checkOutDates.add(checkOut);
        return true;
    }

    public double calculatePrice(Room room, LocalDate checkIn, LocalDate checkOut) {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);

        if (nights == 0) {
            nights = 1;
        }

        double price = room.pricePerNight() * nights;
        return price;
    }

    public List<Hotel> getHotels() {
        return new ArrayList<>(hotels);
    }
}