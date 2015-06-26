package usertrip;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author Ivan Kulekov (ivankulekov10@gmail.com)
 * @since Jun 26 , 2015 13:34
 */
public class UserTripTest {
  public Travel userTrip;

  @Before
  public void setUp() {
    userTrip = new Travel();
  }


  @Test
  public void addNewUserTripToDatabase() throws SQLException {

    userTrip.clearDataFromTable();

    User user = new User(22, "Ivan", "ivan@abv.bg", "1254879658");

    Trip userTravel = new Trip("2154789654", "2015-04-26", "2015-04-27", "Veliko Turnovo");

    userTrip.addUserTravel(user, userTravel);
  }
}
