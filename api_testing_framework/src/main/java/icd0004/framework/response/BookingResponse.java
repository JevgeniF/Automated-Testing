package icd0004.framework.response;

import icd0004.framework.request.Booking;
import lombok.Data;

@Data
public class BookingResponse {
    private int bookingid;
    private Booking booking;
}
