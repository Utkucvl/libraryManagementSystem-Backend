package utku.libraryManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utku.libraryManagement.entity.Place;
import utku.libraryManagement.exceptions.PlaceIsNotValidToCreate;
import utku.libraryManagement.exceptions.PlaceIsNotValidToUpdate;
import utku.libraryManagement.exceptions.PlaceNotFoundException;
import utku.libraryManagement.request.PlaceCreateRequest;
import utku.libraryManagement.request.PlaceUpdateRequest;
import utku.libraryManagement.response.PlaceResponse;
import utku.libraryManagement.services.PlaceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/place")
public class PlaceController {
    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
    @GetMapping
    public List<PlaceResponse> getAllPlaces(){
        List<Place> places;
        places = placeService.getAllPlaces();

        return places.stream().map(report -> new PlaceResponse(report)).collect(Collectors.toList());
    }
    @GetMapping("/{placeId}")
    public PlaceResponse getOnePlace(@PathVariable Long placeId){
        Place place = placeService.getPlaceById(placeId);
        if(place == null) {
           throw new PlaceNotFoundException();
        }
        return new PlaceResponse(place);

    }
    @PostMapping
    public Place createPlace(@RequestBody PlaceCreateRequest place){
        if(!isPlaceValid(place)){
            throw new PlaceIsNotValidToCreate();

        }

        return placeService.createOnePlace(place);
    }
    public boolean isPlaceValid(PlaceCreateRequest place){
        if(place.isItEmpty() == true){
            return true;}
            return false;
    }
    public boolean isPlaceUpdateValid(PlaceUpdateRequest place){
        if(place.isItEmpty() == false)
            return true;
        return false;
    }
    public boolean isPlaceUpdateValidReverse(PlaceUpdateRequest place){
        if(place.isItEmpty() == true)
            return true;
        return false;
    }
    @PutMapping("/{placeId}")
    public Place updateOnePlace(@PathVariable Long placeId , @RequestBody PlaceUpdateRequest place){
        // Checks if the required fields to update the report are filled
        if(!isPlaceUpdateValid(place)){
            throw new PlaceIsNotValidToUpdate();
        }

        //Updates report
        return placeService.updateOnePlace(place , placeId);
    }
    @PutMapping("/break/{placeId}")
    public Place updateOnePlaceForBreak(@PathVariable Long placeId , @RequestBody PlaceUpdateRequest place){
        // Checks if the required fields to update the report are filled
        if(place.isItEmpty() == true){
            throw new PlaceIsNotValidToUpdate();
        }

        //Updates report
        return placeService.updateOnePlace(place , placeId);
    }
    @PutMapping("/reverseUpdate/{placeId}")
    public Place updateOnePlaceReverse(@PathVariable Long placeId , @RequestBody PlaceUpdateRequest place){
        // Checks if the required fields to update the report are filled
        if(!isPlaceUpdateValidReverse(place)){
            throw new PlaceIsNotValidToUpdate();
        }

        //Updates report
        return placeService.updateOnePlaceReverse(place , placeId);
    }
    @ExceptionHandler(PlaceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handlePlaceNotFoundException() {

    }
    @ExceptionHandler(PlaceIsNotValidToUpdate.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handlePlaceIsNotValidToUpdate() {

    }
    @ExceptionHandler(PlaceIsNotValidToCreate.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    private void handlePlaceIsNotValidToCreate() {

    }
}
