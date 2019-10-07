import org.junit.Before;
import org.junit.Test;
import team.codium.legacycode.tripservice.exception.UserNotLoggedInException;
import team.codium.legacycode.tripservice.trip.Trip;
import team.codium.legacycode.tripservice.trip.TripRepository;
import team.codium.legacycode.tripservice.trip.TripService;
import team.codium.legacycode.tripservice.user.User;
import team.codium.legacycode.tripservice.user.UserSession;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private TripRepository tripRepository;
    private UserSession userSession;

    @Before
    public void setup_testing_environment() {
        tripRepository = mock(TripRepository.class);
        userSession = mock(UserSession.class);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void throws_an_exception_when_there_is_no_logged_user() {
        TripService tripService = new TripService(userSession, tripRepository);

        tripService.getTripsByUser(new User());
    }

    @Test
    public void do_not_return_any_trip_for_a_user_that_is_not_friend_of_logged_user() {
        loggedAs(new User());
        when(tripRepository.findTrips(any(User.class)))
                .thenReturn(Arrays.asList(new Trip()));
        TripService tripService = new TripService(userSession, tripRepository);

        List<Trip> response = tripService.getTripsByUser(new User());

        assertTrue(response.isEmpty());
    }

    @Test
    public void returns_the_trip_for_a_user_that_is_friend_of_logged_user() {
        User loggedUser = new User();
        loggedAs(loggedUser);
        User user = new User();
        user.addFriend(loggedUser);
        List<Trip> trips = Arrays.asList(new Trip());
        when(tripRepository.findTrips(any(User.class))).thenReturn(trips);
        TripService tripService = new TripService(userSession, tripRepository);

        List<Trip> response = tripService.getTripsByUser(user);

        assertEquals(trips, response);
    }

    private void loggedAs(User loggedUser) {
        when(userSession.getLoggedUser()).thenReturn(loggedUser);
    }

}