package icd0004.framework;

import icd0004.framework.request.Booking;
import icd0004.framework.response.BookingResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingTests {

    static Booking bookingPayload;

    @BeforeAll
    static void getBookingPayload() {
        bookingPayload = Booking.getFullPayload();
    }

    @Test
    public void postBookingShouldReturnBookingId() {
        BookingResponse bookingResponse = BookingApi
                .postBooking(bookingPayload)
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isNotNull();
    }

    @Test
    public void postBookingShouldReturnCorrectBookingPerson() {
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
        bookingPayload = Booking.buildWithResource("./test_data/jira_54.json");

        BookingResponse bookingResponse = BookingApi
                .postBooking(bookingPayload)
                .as(BookingResponse.class);

        assertThat(bookingResponse.getBooking())
                .extracting("firstname", "lastname")
                .contains("Jevgeni", "Fenko");
    }

    @Test //Posts booking with wrong "Accept" header and checks if API returns status code 418
    public void postBookingWithWrongAcceptHeaderShouldReturnImATeapotError() {
        String acceptHeader = "application/pdf";

        int statusCode = BookingApi.postBooking(bookingPayload, acceptHeader).getStatusCode();

        assertThat(statusCode).isEqualTo(418);
    }
}
