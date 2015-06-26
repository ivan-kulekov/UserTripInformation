package usertrip;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ivan Kulekov (ivankulekov10@gmail.com)
 * @since Jun 26 , 2015 13:33
 */
public class Trip {
  public final String egn;
  public final Date dateOfArrived;
  public final Date departureDate;
  public final String city;

  public Trip(String egn, String dateOfArrived, String departureDate, String city) {
    this.egn = egn;
    this.dateOfArrived = createDate(dateOfArrived);
    this.departureDate = createDate(departureDate);
    this.city = city;
  }


  private Date createDate(String date) {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date dateFormat = null;

    try {
      dateFormat = formatter.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    if (dateFormat != null) {
      return new Date(dateFormat.getTime());
    }

    return null;
  }
}
