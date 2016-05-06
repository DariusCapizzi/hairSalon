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

  //create
  public void save(){
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stylists ( stylist_name, created_at, updated_at) VALUES ( :stylist_name, :created_at, :updated_at)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("stylist_name", stylist_name)
      .addParameter("created_at", created_at)
      .addParameter("updated_at", updated_at)
      .executeUpdate()
      .getKey();
    }
  }

  //read
  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, stylist_name, created_at, updated_at FROM stylists";
     return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
    }
  }

  public List<Client> thisStylistsClients(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, client_name, created_at, updated_at, stylist_id FROM clients WHERE stylist_id = :id";
      return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Client.class);
    }
  }

  //update

  //delete
  public void remove(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE stylist_id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }

    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }


  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylistName().equals(newStylist.getStylistName()) &&
      this.getId() == newStylist.getId();
    }
  }

  public int getId(){
    return id;
  }

  public String getStylistName(){
    return stylist_name;
  }

  public Timestamp getCreatedAt(){
    return created_at;
  }

  public Timestamp getUpdatedAt(){
    return updated_at;
  }
}
