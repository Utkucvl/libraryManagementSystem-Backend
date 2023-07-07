package utku.libraryManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    boolean isItEmpty ;

    boolean isItOnBreak;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
}
