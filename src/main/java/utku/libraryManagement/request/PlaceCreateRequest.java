package utku.libraryManagement.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PlaceCreateRequest {
    boolean isItEmpty;

    boolean isItOnBreak;

    public boolean isItEmpty() {
        return isItEmpty;
    }

    public void setItEmpty(boolean itEmpty) {
        isItEmpty = itEmpty;
    }

    public boolean isItOnBreak() {
        return isItOnBreak;
    }

    public void setItOnBreak(boolean itOnBreak) {
        isItOnBreak = itOnBreak;
    }
}
