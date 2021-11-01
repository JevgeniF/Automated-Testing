package icd0004.framework;

import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteBookingTest {

    @Test //Deletes Booking via "delete" message and checks if API returns HTTP_CREATED(201) status response.
    public void deleteBookingShouldReturnHttpCreatedResponse() {
        AuthenticationApi.authenticate();
        int bookingId = 11;

        int statusCode = BookingApi.deleteBooking(bookingId).getStatusCode();

        assertThat(statusCode).isEqualTo(HTTP_CREATED);
    }
}
