import org.junit.Before;
import org.junit.Test;
import team.codium.legacycode.tripservice.exception.UserNotLoggedInException;
import team.codium.legacycode.tripservice.trip.Trip;
import team.codium.legacycode.tripservice.trip.TripRepository;
import team.codium.legacycode.tripservice.trip.TripService;
import team.codium.legacycode.tripservice.user.User;
import team.codium.legacycode.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private TripRepository tripRepository;
    private UserSession userSession;
    private User loggedUser;
    private User paramUser;

    @Before
    public void setup_testing_environment() {
        // Setup testing environment
        tripRepository = mock(TripRepository.class);
        userSession = mock(UserSession.class);
        loggedUser = mock(User.class);
        paramUser = mock(User.class);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void not_logged_user_throws_exception() {
        // Mock userSession's operation
        when(userSession.getLoggedUser()).thenReturn(null);

        // Call TripService's method
        TripService tripService = new TripService(userSession, tripRepository);
        tripService.getTripsByUser(paramUser);
    }

    @Test
    public void user_without_friends_do_not_return_any_trip() {
        // Mock userSession's operation
        when(userSession.getLoggedUser()).thenReturn(loggedUser);

        // Mock loggedUser's operation
        when(paramUser.getFriends()).thenReturn(new ArrayList<User>());

        // Call TripService's method
        TripService tripService = new TripService(userSession, tripRepository);
        List<Trip> response = tripService.getTripsByUser(paramUser);

        // Assert response was a empty list
        assertTrue(response.isEmpty());
    }

    @Test
    public void user_with_friends_returns_a_trip_list() {
        // Mock loggedUser's operation
        List<User> friendList = new ArrayList<>();
        {
            friendList.add(loggedUser);
        }
        when(paramUser.getFriends()).thenReturn(friendList);

        // Mock TripRepository's operations
        List<Trip> tripList = new ArrayList<>();
        {
            tripList.add(new Trip());
        }
        when(tripRepository.findTrips(paramUser)).thenReturn(tripList);

        // Mock userSession's operation
        when(userSession.getLoggedUser()).thenReturn(loggedUser);

        // Call TripService's method
        TripService tripService = new TripService(userSession, tripRepository);
        List<Trip> response = tripService.getTripsByUser(paramUser);

        // Assert response was a empty list
        assertFalse(response.isEmpty());
    }

}