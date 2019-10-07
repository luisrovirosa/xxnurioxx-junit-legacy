package team.codium.legacycode.tripservice.trip;

import team.codium.legacycode.tripservice.user.User;
import team.codium.legacycode.tripservice.user.UserSession;
import team.codium.legacycode.tripservice.exception.UserNotLoggedInException;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	private final UserSession userSession;
	private final TripRepository tripRepository;

	public TripService(UserSession userSession, TripRepository tripRepository) {
		this.userSession = userSession;
		this.tripRepository = tripRepository;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = userSession.getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = tripRepository.findTrips(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

}