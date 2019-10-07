# Trip Service Kata - Test doubles
## Goals:
- How to use test doubles
- Practice: 
  - Use a stub with Mockito
  - Create a stub manually
## Description:
This kata simulates a social network of trips.

We have used parametrize constructor technique to reduce the couplings inside [TripService](./src/main/java/team/codium/legacycode/tripservice/trip/TripService.java).

We want to create and use test doubles to test it.

### Example of stub

    @Test
    public void should_success_when_password_is_valid() {
        PasswordValidator passwordValidator = mock(PasswordValidator.class);
        when(passwordValidator.isValid(‘validPassword’)).thenReturn(true);
        UserRegistration userRegistration = new UserRegistration(passwordValidator);

        bool success = userRegistration.register();

        assertTrue(success);
    }

## Original source code from:
https://github.com/sandromancuso/trip-service-kata

Sandro Mancuso @sandromancuso