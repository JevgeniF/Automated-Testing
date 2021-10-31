package icd0004.framework;

import icd0004.framework.request.Booking;
import icd0004.framework.response.BookingResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingTests {

    @Test
    public void postBookingShouldReturnBookingId() {
        Booking bookingPayload = Booking.getFullPayload();

        BookingResponse bookingResponse = BookingApi
                .postBooking(bookingPayload)
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isNotNull();
    }

    @Test
    public void postBookingShouldReturnCorrectBookingPerson() {
        Booking bookingPayload = Booking.getFullPayload();
        bookingPayload.setFirstname("Maili");
        bookingPayload.setLastname("Hodorkovsky");

        BookingResponse bookingResponse = BookingApi
                .postBooking(bookingPayload)
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Maili");
        assertThat(bookingResponse.getBooking().getLastname()).isEqualTo("Hodorkovsky");
    }

    @Test
    public void createBookingFromFile() throws IOException {
        Booking bookingPayload = Booking.buildWithResource("./test_data/jira_54.json");

        BookingResponse bookingResponse = BookingApi
                .postBooking(bookingPayload)
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBooking())
                .extracting("firstname", "lastname")
                .contains("Jevgeni", "Fenko");
    }
}
