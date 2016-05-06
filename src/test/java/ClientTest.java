import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.Date;



public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();
  

  @Test
  public void Client_instantiatesCorrectly_true(){
    Client myClient = new Client("sakdjas");
    assertTrue(myClient instanceof Client);
  }

}
