package icd0004.framework;

import icd0004.framework.request.Booking;
import org.junit.jupiter.api.Test;

public class SmokeTests {

    @Test
    public void getBookingsShouldReturnHttpOKResponse() {
        BookingApi.getBookings().then().statusCode(200);
    }

    @Test
    public void getBookingByIdShouldReturnHttpOKResponse() {
        BookingApi.getBookingById(11).then().statusCode(200);
    }

    @Test
    public void postBookingShouldReturnBookingHttpOKResponse() {
        Booking bookingPayload = Booking.getFullPayload();

        BookingApi.postBooking(bookingPayload).then().statusCode(200);
    }
}
