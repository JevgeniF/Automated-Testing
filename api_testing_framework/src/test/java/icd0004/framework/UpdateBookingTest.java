package icd0004.framework;

import icd0004.framework.request.Booking;
import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateBookingTest {

    @Test //Updates Booking via "put" message and checks if API returns HTTP_OK(200) status response.
    public void putBookingShouldReturnHttpOKResponse() {
        AuthenticationApi.authenticate();
        int bookingId = 10;
        Booking bookingPayload = Booking.getFullPayload();

        int statusCode = BookingApi.putBooking(bookingPayload, bookingId).getStatusCode();

        assertThat(statusCode).isEqualTo(HTTP_OK);
    }
}
