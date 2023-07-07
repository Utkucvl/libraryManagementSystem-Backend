package utku.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utku.libraryManagement.entity.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Long> {
    public List<Place> findAllByOrderByIdAsc();
}
