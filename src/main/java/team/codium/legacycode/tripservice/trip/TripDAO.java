package team.codium.legacycode.tripservice.trip;

import team.codium.legacycode.tripservice.exception.CollaboratorCallException;
import team.codium.legacycode.tripservice.user.User;

import java.util.List;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
}