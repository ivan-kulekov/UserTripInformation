package usertrip;

/**
 * @author Ivan Kulekov (ivankulekov10@gmail.com)
 * @since Jun 26 , 2015 13:34
 */
public class User {

  public final int age;
  public final String userName;
  public final String email;
  public final String egn;

  public User(int age, String userName, String email, String egn) {
    this.age = age;
    this.userName = userName;
    this.email = email;
    this.egn = egn;
  }
}
