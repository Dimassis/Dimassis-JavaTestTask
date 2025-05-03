package service;

import model.Hotel;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingServiceTest {

    private BookingService bookingService;
    private Hotel testHotel;
    private Room economyRoom;
    private LocalDate today;
    private LocalDate tomorrow;


    @BeforeEach
    public void setUp() {
        bookingService = new BookingService();
        testHotel = bookingService.getHotels().get(0);
        economyRoom = testHotel.rooms().get(0);
        today = LocalDate.now();
        tomorrow = today.plusDays(1);
    }


    @Test
    @DisplayName("Проверка метода isRoomAvailable")
    void givenRoomAndCheckInDate_whenCheckAvailable_thenReturnTrue() {
        // Given

        // When
        boolean isAvailable = bookingService.isRoomAvailable(economyRoom, today, tomorrow);

        // Then
        assertTrue(isAvailable);
    }

    @Test
    @DisplayName("Проверка метода isRoomAvailable")
    void givenRoomAndCheckInDate_whenCheckAvailable_thenReturnFalse() {
        // Given
        bookingService.bookRoom(testHotel, economyRoom, today, tomorrow);

        // When
        boolean isAvailable = bookingService.isRoomAvailable(economyRoom, today, tomorrow);

        // Then
        assertFalse(isAvailable);
    }

    @Test
    @DisplayName("Проверка функциональности bookRoom")
    void givenRoomAndCheckDates_whenBook_thenReturnTrue() {
        // When
        boolean booked = bookingService.bookRoom(testHotel, economyRoom, today, tomorrow);

        // Then
        assertTrue(booked);
    }

    @Test
    @DisplayName("Проверка функциональности bookRoom")
    void givenRoomAndCheckDates_whenBook_thenReturnFalse() {
        // Given
        bookingService.bookRoom(testHotel, economyRoom, today, tomorrow);

        // When
        boolean bookedAgain = bookingService.bookRoom(testHotel, economyRoom, today, tomorrow);

        // Then
        assertFalse(bookedAgain);
    }

    @Test
    @DisplayName("Проверка функциональности calculatePrice")
    void givenRoomAndCheckDates_whenCalculateSum_thenReturnSum() {
        // When
        double price = bookingService.calculatePrice(economyRoom, today, today);

        // Then
        assertEquals(1000, price);
    }
}