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
    Client myClient = new Client("sakdjas", 1);
    assertTrue(myClient instanceof Client);
  }

  @Test
  public void getCreatedAtAndgetUpdatedAt_returnTimestamps_hours(){
    Client myClient = new Client("flint", 1);
    Timestamp testCreatedAt = new Timestamp(new Date().getTime());
    assertEquals(myClient.getCreatedAt().getHours(), testCreatedAt.getHours());
    assertEquals(myClient.getUpdatedAt().getHours(), testCreatedAt.getHours());
  }


  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Client firstClient = new Client("flint", 1);
    Client secondClient = new Client("flint", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame_Client() {
    Client testClient = new Client("flint", 1);
    testClient.save();
    assertTrue(Client .all().get(0).equals(testClient));
  }

}
