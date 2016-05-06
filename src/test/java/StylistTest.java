import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.Date;



public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true(){
    Stylist myStylist = new Stylist("sakdjas");
    assertTrue(myStylist instanceof Stylist);
  }

}
