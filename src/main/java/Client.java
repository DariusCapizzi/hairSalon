import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Client {

  private int id;
  private String client_name;
  private Timestamp created_at;
  private Timestamp updated_at;
  private int stylist_id;

  public Client(String client_name) {
    this.client_name = client_name;
    created_at = new Timestamp(new Date().getTime());
    updated_at = new Timestamp(new Date().getTime());
  }

}
