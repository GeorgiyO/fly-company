package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.example.repository.NotFoundException;

@Getter
@Setter
@Accessors(chain = true)
public class Offer {
    private int id;
    private Place place;
    private Passenger passenger;

    public static class NotFreePlacesFound extends NotFoundException {
        public NotFreePlacesFound(Offer offer) {
            super("There is no free places for " + offer.getPlace().getId());
        }
    }
}
