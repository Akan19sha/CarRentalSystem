package com.example.demo.test;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Car;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringBootTest
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;
    private Car car;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
        // Sample data for testing
        car = new Car(1, "Honda", "City", true);
        reservation = new Reservation(1, "Parth Singh", LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5), BigDecimal.valueOf(150.00) , car);
    }

    // Test for getAllReservations method
    @Test
    public void testGetAllReservations() {
        // Arrange
        Reservation reservation1 = new Reservation(1, "Parth Singh",LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5), BigDecimal.valueOf(150.00), car);
        Reservation reservation2 = new Reservation(2, "Shivam Kumar",LocalDate.of(2024, 11, 2), LocalDate.of(2024, 11, 5), BigDecimal.valueOf(200.00), car);
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        // Act
        List<Reservation> reservations = reservationService.getAllReservations();

        // Assert
        assertNotNull(reservations);
        assertEquals(2, reservations.size());
        verify(reservationRepository, times(1)).findAll();
    }

    // Test for getReservationById method
    @Test
    public void testGetReservationById() {
        // Arrange
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        // Act
        Optional<Reservation> foundReservation = reservationService.getReservationById(1);

        // Assert
        assertTrue(foundReservation.isPresent());
        assertEquals("John Doe", foundReservation.get().getCustomerName());
        verify(reservationRepository, times(1)).findById(1);
    }

    // Test for updateReservation method
    @Test
    public void testUpdateReservation() {
        // Arrange
        Reservation updatedReservation = new Reservation(1, "Parth Singh",LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5), BigDecimal.valueOf(150.00), car);;
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
       // when(reservationRepository.save(any(Reservation.class))).thenReturn(updatedReservation);

        // Act
        Reservation updated = reservationService.updateReservation(1, updatedReservation);

        // Assert
        assertNotNull(updated);
        assertEquals("Parth Singh", updated.getCustomerName());
        assertEquals(120.0, updated.getTotalPrice());
        verify(reservationRepository, times(1)).findById(1);
        verify(reservationRepository, times(1)).save(updatedReservation);
    }

    // Test for deleteReservation method
    @Test
    public void testDeleteReservation() {
        // Arrange
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));

        // Act
        reservationService.deleteReservation(1);

        // Assert
        verify(reservationRepository, times(1)).findById(1);
        verify(reservationRepository, times(1)).delete(reservation);
    }

    // Test for bookCar method
    @Test
    public void testBookCar() {
        // Arrange
        Reservation newReservation = new Reservation(2, "Shivam Kumar",LocalDate.of(2024, 11, 2), LocalDate.of(2024, 11, 5), BigDecimal.valueOf(200.00), car);;
        when(carRepository.findById(1)).thenReturn(Optional.of(car));
     //   when(reservationRepository.save(any(Reservation.class))).thenReturn(newReservation);

        // Act
        Reservation bookedReservation = reservationService.bookCar(1, newReservation);

        // Assert
        assertNotNull(bookedReservation);
        assertEquals("Charlie", bookedReservation.getCustomerName());
        assertFalse(car.isAvailable()); // Car should now be marked as unavailable
        verify(carRepository, times(1)).findById(1);
        verify(reservationRepository, times(1)).save(newReservation);
    }

    // Test for booking a car when the car is unavailable
  /*  @Test
    public void testBookCarWhenCarUnavailable() {
        // Arrange
        car.setAvailable(false); // Make the car unavailable
        Reservation newReservation = new Reservation(2, "Shivam Kumar",LocalDate.of(2024, 11, 2), LocalDate.of(2024, 11, 5), BigDecimal.valueOf(200.00), car);;
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        // Act & Assert
       /* RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reservationService.bookCar(1, newReservation);
        });
        assertEquals("Car not available", exception.getMessage());
        verify(carRepository, times(1)).findById(1);
        verify(reservationRepository, times(0)).save(newReservation); // The reservation should not be saved
    }

	private RuntimeException assertThrows(Class<RuntimeException> class1, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	
}