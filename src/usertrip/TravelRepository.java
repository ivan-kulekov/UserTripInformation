package usertrip;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author Ivan Kulekov (ivankulekov10@gmail.com)
 * @since Jun 26 , 2015 14:15
 */
public interface TravelRepository {

  void addUserTravel(User user, Trip userTrip);

  List<User> getUsers();


}
