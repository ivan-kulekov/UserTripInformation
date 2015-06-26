package usertrip;

import javax.jws.soap.SOAPBinding.Use;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Kulekov (ivankulekov10@gmail.com)
 * @since Jun 26 , 2015 13:33
 */
public class TravelPersistence {
  private Connection connection;

  /**
   * Make the constructor and set the database connection.
   */
  public TravelPersistence() {
    String dburl = "jdbc:postgresql://localhost/user_travel";
    String userName = "postgres";
    String userPassword = "ivan";
    try {


      Class.forName("org.postgresql.Driver");

    } catch (ClassNotFoundException e) {

      System.out.println("Where is your PostgreSQL JDBC Driver? "
              + "Include in your library path!");
      e.printStackTrace();
      return;

    }

    System.out.println("PostgreSQL JDBC Driver Connected!");


    try {

      connection = DriverManager.getConnection(
              dburl, userName, userPassword);

    } catch (SQLException e) {

      System.out.println("Connection Failed! Check output console");
      e.printStackTrace();
      return;

    }

    if (connection != null) {
      System.out.println("You made it, take control your database now!");
    } else {
      System.out.println("Failed to make connection!");
    }
  }

  /**
   * Add new travel to the each user and execute the query.
   *
   * @param user     is the user witch set the travel.
   * @param userTrip is the trip on the user.
   * @throws SQLException
   */
  public void addUserTravel(User user, Trip userTrip) throws SQLException {

    try {
      PreparedStatement statementAddUserTravel = connection.prepareStatement("INSERT INTO  user values(?, ?, ?, ?)");

      statementAddUserTravel.setString(1, user.userName);
      statementAddUserTravel.setString(2, user.egn);
      statementAddUserTravel.setInt(3, user.age);
      statementAddUserTravel.setString(4, user.email);

      statementAddUserTravel.executeUpdate();

      PreparedStatement statementAddTripInformation = connection.prepareStatement("INSERT INTO trip values(?, ?, ?, ?)");

      statementAddTripInformation.setString(1, userTrip.egn);
      statementAddTripInformation.setDate(2, (Date) userTrip.dateOfArrived);
      statementAddTripInformation.setDate(3, (Date) userTrip.departureDate);
      statementAddTripInformation.setString(4, userTrip.city);

      statementAddTripInformation.executeUpdate();


      statementAddTripInformation.close();
      statementAddUserTravel.close();

    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  /**
   * Clear data from the tables trip and user.
   *
   * @throws SQLException
   */

  public void clearDataFromTable() throws SQLException {


    try {
      PreparedStatement clearDataFromTrip = connection.prepareStatement("DELETE FROM trip");
      PreparedStatement clearDataFromUser = connection.prepareStatement("DELETE FROM user");

      clearDataFromUser.executeUpdate();
      clearDataFromTrip.executeUpdate();

      clearDataFromUser.close();
      clearDataFromTrip.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }

  }

  public List<User> getUsers() throws SQLException {
    List<User> listOfUsers = new ArrayList<User>();

    PreparedStatement statementUser = null;
    ResultSet result = null;
    try {

      statementUser = connection.prepareStatement("SELECT * FROM user");
      result = statementUser.executeQuery();

      while (result.next()) {
        User tempUser = convertRowToEmployee(result);
        listOfUsers.add(tempUser);
      }

      return listOfUsers;

    } finally {
      close(statementUser, result);
    }
  }

  private User convertRowToEmployee(ResultSet result) throws SQLException {


    int userAge = result.getInt("user_age");
    String userName = result.getString("user_name");
    String email = result.getString("user_email_address");
    String egn = result.getString("user_egn");
    User tempUser = new User(userAge, userName, email, egn);


    return tempUser;
  }

  /**
   * Close the statement and result set.
   *
   * @param statementPeople is the statement , witch execute the query.
   * @param result          is the result set.
   * @throws SQLException
   */
  private void close(PreparedStatement statementPeople, ResultSet result) throws SQLException {
    close(null, statementPeople, result);
  }

  /**
   * Close the connection , statement and the result set.
   *
   * @param connection is the connection to the database.
   * @param statement  is the statement witch is execute the query.
   * @param resultSet  is the result set.
   * @throws SQLException
   */
  private void close(Connection connection, Statement statement, ResultSet resultSet)
          throws SQLException {

    if (resultSet != null) {
      resultSet.close();
    }

    if (statement != null) {
      statement.close();
    }

    if (connection != null) {
      connection.close();
    }
  }

}
