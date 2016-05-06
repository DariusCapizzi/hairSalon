import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Stylist {

  private int id;
  private String stylist_name;
  private Timestamp created_at;
  private Timestamp updated_at;

  public Stylist(String stylist_name) {
    this.stylist_name = stylist_name;
    created_at = new Timestamp(new Date().getTime());
    updated_at = new Timestamp(new Date().getTime());
  }

}
