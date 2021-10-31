package icd0004.framework;

import icd0004.framework.request.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Java6BDDSoftAssertionsProvider;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookingApi {
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String BOOKING_API = BASE_URL + "/booking/";

    public static Response getBookings() {
        return given().get(BOOKING_API);
    }

    public static Response getBookingById(int bookingId) {
        return getBookingById(bookingId, JSON);
    }

    public static Response getBookingById(int bookingId, ContentType contentType) {
        return given()
                .accept(contentType.toString())
                .when()
                .get(BOOKING_API + bookingId);
    }

    public static Response postBooking(Booking bookingPayload) {
        return given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(bookingPayload)
                .when()
                .post(BOOKING_API);
    }
}
