
import org.sql2o.*;
import org.fluentlenium.adapter.FluentTest;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("add a new stylist:");
  }

  @Test
  public void stylistsAreListedTest() {
    Stylist testStylist = new Stylist("dave");
    testStylist.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("dave");
  }

  @Test
  public void createStylist() {
    goTo("http://localhost:4567/");
    fill("#stylist-name").with("dave");
    submit("#stylist-btn");
    assertThat(pageSource()).contains("dave");
  }

  @Test
  public void createClient() {
    goTo("http://localhost:4567/");
    fill("#stylist-name").with("dave");
    submit("#stylist-btn");
    fill("#client-name").with("flint");
    submit("#client-btn");
    assertThat(pageSource()).contains("flint");
  }

  @Test
  public void deleteStylist() {
    Stylist testStylist = new Stylist("dave");
    testStylist.save();

    goTo("http://localhost:4567/");
    submit("#remove-stylist");
    assertThat(pageSource()).doesNotContain("dave");
  }

  @Test
  public void deleteClient() {
    Stylist testStylist = new Stylist("dave");
    testStylist.save();
    Client myClient = new Client("flint", testStylist.getId());
    myClient.save();

    goTo("http://localhost:4567/");
    submit("#remove-client");
    assertThat(pageSource()).doesNotContain("flint");
  }

  @Test
  public void seeThisStylist() {
    Stylist thisStylist = new Stylist("daave");
    thisStylist.save();

    goTo("http://localhost:4567/");
    click("a", withText("daave"));
    assertThat(pageSource()).contains("daave");
  }

  @Test
  public void seeThisClient() {
    Stylist testStylist = new Stylist("dave");
    testStylist.save();
    Client myClient = new Client("flint", testStylist.getId());
    myClient.save();

    goTo("http://localhost:4567/");
    click("a", withText("flint"));
    assertThat(pageSource()).contains("flint");
  }

  @Test
  public void updateStylist() {
    Stylist testStylist = new Stylist("dave");
    testStylist.save();

    goTo("http://localhost:4567/stylists/"+testStylist.getId());
    fill("#change_stylist_name").with("carl");
    submit("#changeStylistName");
    assertThat(pageSource()).contains("carl");
  }

  @Test
  public void updateClient() {
    Stylist testStylist = new Stylist("dave");
    testStylist.save();
    Client myClient = new Client("flint", testStylist.getId());
    myClient.save();

    goTo("http://localhost:4567/clients/"+myClient.getId());
    fill("#change_client_name").with("clint");
    submit("#changeClientName");
    assertThat(pageSource()).contains("clint");
  }



}
