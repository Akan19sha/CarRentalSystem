package com.example.demo.test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Billing;
import com.example.demo.repository.BillingRepository;
import com.example.demo.service.BillingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class BillingServiceTest {

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingService billingService;

    private Billing billing;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
        // Sample data for testing
        billing = new Billing( 1, BigDecimal.valueOf(100.00), LocalDate.now());
    }

    // Test for processPayment method
    @Test
    public void testProcessPayment() {
        // Arrange
        when(billingRepository.save(any(Billing.class))).thenReturn(billing);

        // Act
        Billing processedBilling = billingService.processPayment(1, BigDecimal.valueOf(100.00));

        // Assert
        assertNotNull(processedBilling);
        assertEquals(1, processedBilling.getReservationId());
        assertEquals(BigDecimal.valueOf(100.00), processedBilling.getAmount());
        assertEquals(LocalDate.now(), processedBilling.getPaymentDate());
        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    // Test for getAllBillings method
    @Test
    public void testGetAllBillings() {
        // Arrange
        Billing billing1 = new Billing( 1, BigDecimal.valueOf(100.00), LocalDate.now());
        Billing billing2 = new Billing( 2, BigDecimal.valueOf(200.00), LocalDate.now());
        when(billingRepository.findAll()).thenReturn(Arrays.asList(billing1, billing2));

        // Act
        List<Billing> billings = billingService.getAllBillings();

        // Assert
        assertNotNull(billings);
        assertEquals(2, billings.size());
        verify(billingRepository, times(1)).findAll();
    }

    // Test for getBillingById method
    @Test
    public void testGetBillingById() {
        // Arrange
        when(billingRepository.findById(1)).thenReturn(Optional.of(billing));

        // Act
        Optional<Billing> foundBilling = billingService.getBillingById(1);

        // Assert
        assertTrue(foundBilling.isPresent());
        assertEquals(1, foundBilling.get().getReservationId());
        verify(billingRepository, times(1)).findById(1);
    }

    // Test for deleteBilling method
    @Test
    public void testDeleteBilling() {
        // Arrange
        when(billingRepository.findById(1)).thenReturn(Optional.of(billing));

        // Act
        billingService.deleteBilling(1);

        // Assert
        verify(billingRepository, times(1)).findById(1);
        verify(billingRepository, times(1)).delete(billing);
    }

    // Test for deleteBilling method when Billing not found
    @Test
    public void testDeleteBillingNotFound() {
        // Arrange
        when(billingRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            billingService.deleteBilling(1);
        });
        assertEquals("Billing not found with id 1", exception.getMessage());
        verify(billingRepository, times(1)).findById(1);
        verify(billingRepository, times(0)).delete(any(Billing.class));
    }
}
