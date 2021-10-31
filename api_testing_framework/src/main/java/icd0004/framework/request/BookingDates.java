package icd0004.framework.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.Date;

@Data
@JsonAutoDetect
public class BookingDates {
    private Date checkin;
    private Date checkout;
}
