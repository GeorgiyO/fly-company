package org.example.repository;

import org.example.entity.Offer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OffersRepository extends DefaultRepository<Offer> {

    final PassengersRepository passengersRepository;

    public OffersRepository(JdbcTemplate jdbc, PassengersRepository passengersRepository, PlacesRepository placesRepository) {
        super(jdbc, "offers",
              (rs, i) -> {
                  try {
                      return new Offer().setId(rs.getInt("id"))
                                        .setPlace(placesRepository.get(rs.getInt("place_id")))
                                        .setPassenger(passengersRepository.get(rs.getInt("passenger_id")));
                  } catch (NotFoundException e) {
                      e.printStackTrace();
                  }
                  return null;
              },
              (o) -> new Object[]{o.getPlace().getId(), o.getPassenger().getId()},
              2
        );
        this.passengersRepository = passengersRepository;
    }

    @Override
    public Offer add(Offer offer) {
        try {
            offer.setPassenger(passengersRepository.add(offer.getPassenger()));
            return super.add(offer);
        } catch (NotFoundException e) {
            throw new Offer.NotFreePlacesFound(offer);
        }
    }
}
