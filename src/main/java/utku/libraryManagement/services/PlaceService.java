package utku.libraryManagement.services;

import org.springframework.stereotype.Service;
import utku.libraryManagement.entity.Place;
import utku.libraryManagement.repository.PlaceRepository;
import utku.libraryManagement.request.PlaceCreateRequest;
import utku.libraryManagement.request.PlaceUpdateRequest;

import java.util.List;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }



    public List<Place> getAllPlaces() {
        return placeRepository.findAllByOrderByIdAsc();
    }

    public Place getPlaceById(Long placeId) {
        return placeRepository.findById(placeId).orElse(null);
    }

    public Place createOnePlace(PlaceCreateRequest place) {
        Place toSave = new Place();
        toSave.setItEmpty(place.isItEmpty());
        toSave.setItOnBreak(place.isItOnBreak());
        toSave.setCreatedDate(null);

        return placeRepository.save(toSave);
    }

    public Place updateOnePlace(PlaceUpdateRequest place, Long placeId) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis+10800000);
        Place willBeUpdated = placeRepository.findById(placeId).orElse(null);
        if(willBeUpdated != null){
            willBeUpdated.setCreatedDate(date);
            willBeUpdated.setItOnBreak(place.isItOnBreak());
            willBeUpdated.setItEmpty(place.isItEmpty());
        }
        return placeRepository.save((willBeUpdated));

    }
    public Place updateOnePlaceReverse(PlaceUpdateRequest place, Long placeId) {
        Place willBeUpdated = placeRepository.findById(placeId).orElse(null);
        if(willBeUpdated != null){
            willBeUpdated.setCreatedDate(null);
            willBeUpdated.setItOnBreak(place.isItOnBreak());
            willBeUpdated.setItEmpty(place.isItEmpty());
        }
        return placeRepository.save((willBeUpdated));

    }
}
