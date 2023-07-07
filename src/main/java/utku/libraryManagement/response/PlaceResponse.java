package utku.libraryManagement.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;
import utku.libraryManagement.entity.Place;

import java.util.Date;

@Data
public class PlaceResponse {
    Long id ;

    boolean isItEmpty ;

    boolean isItOnBreak;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date createdDate;
    public PlaceResponse(Place place){
        this.id = place.getId();
        this.isItOnBreak = place.isItOnBreak();
        this.isItEmpty = place.isItEmpty();
        this.createdDate=place.getCreatedDate();
    }
}
