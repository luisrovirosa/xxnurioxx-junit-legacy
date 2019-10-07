package team.codium.legacycode.tripservice.trip;

import team.codium.legacycode.tripservice.user.User;

import java.util.List;

public class DaoTripRepository implements TripRepository {
    @Override
    public List<Trip> findTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
