package team.codium.legacycode.tripservice.trip;

import team.codium.legacycode.tripservice.user.User;

import java.util.List;

public interface TripRepository {
    List<Trip> findTrips(User user);
}
