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

  @Test
  public void getCreatedAtAndgetUpdatedAt_returnTimestamps_hours(){
    Stylist myStylist = new Stylist("dave");
    Timestamp testCreatedAt = new Timestamp(new Date().getTime());
    assertEquals(myStylist.getCreatedAt().getHours(), testCreatedAt.getHours());
    assertEquals(myStylist.getUpdatedAt().getHours(), testCreatedAt.getHours());
  }


  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Stylist firstStylist = new Stylist("dave");
    Stylist secondStylist = new Stylist("dave");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame_Stylist() {
    Stylist testStylist = new Stylist("dave");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

}
