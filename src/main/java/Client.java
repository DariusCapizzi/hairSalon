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

  public Client(String client_name, int stylist_id) {
    this.client_name = client_name;
    created_at = new Timestamp(new Date().getTime());
    updated_at = new Timestamp(new Date().getTime());
    this.stylist_id =  stylist_id;
  }

  @Override
  public boolean equals(Object otherClient) {
    if(!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getClientName().equals(newClient.getClientName()) &&
      this.getId() == newClient.getId() &&
      this.getStylistId() == newClient.getStylistId();
    }
  }

  public int getId(){
    return id;
  }

  public String getClientName(){
    return client_name;
  }

  public Timestamp getCreatedAt(){
    return created_at;
  }

  public Timestamp getUpdatedAt(){
    return updated_at;
  }

  public int getStylistId(){
    return stylist_id;
  }

}
