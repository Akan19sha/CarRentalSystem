package com.example.demo.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;

@SpringBootTest
public class CarServiceTest {
	@Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAvailableCars() {
        // Arrange
        Car car1 = new Car(1, "Maruti", "Nexa", true);
        Car car2 = new Car(2, "Honda", "City", true);
        when(carRepository.findByAvailable(true)).thenReturn(Arrays.asList(car1, car2));

        // Act
        List<Car> availableCars = carService.getAvailableCars();

        // Assert
        assertNotNull(availableCars);
        assertEquals("Maruti", availableCars.get(0).getMake());
        assertEquals("Honda", availableCars.get(1).getMake());
        verify(carRepository, times(1)).findByAvailable(true);
    }

    @Test
    public void testGetAllCars() {
        // Arrange
        Car car1 = new Car(1, "Maruti", "Nexa", true);
        Car car2 = new Car(2, "Honda", "City", false);
        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        // Act
        List<Car> allCars = carService.getAllCars();

        // Assert
        assertNotNull(allCars);
        assertEquals("Maruti", allCars.get(0).getMake());
        assertEquals("Honda", allCars.get(1).getMake());
        verify(carRepository, times(1)).findAll();
    }

    private void assertEquals(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void testGetCarById() {
        // Arrange
        Car car = new Car(1, "Maruti", "Nexa", true);
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        // Act
        Optional<Car> foundCar = carService.getCarById(1);

        // Assert
        assertTrue(foundCar.isPresent());
        assertEquals("Toyota", foundCar.get().getMake());
        verify(carRepository, times(1)).findById(1);
    }

    @Test
    public void testCreateCar() {
        // Arrange
        Car car = new Car(1, "Toyota", "Camry", true);

        // Act
        carService.createCar(car);

        // Assert
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testDeleteCar() {
        // Arrange
        Car car = new Car(1, "Toyota", "Camry", true);
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        // Act
        carService.deleteCar(1);

        // Assert
        verify(carRepository, times(1)).delete(car);
    }
}
