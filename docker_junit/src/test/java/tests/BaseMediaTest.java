package tests;

import it.academy.project.pages.MediaPage;
import org.junit.After;
import org.junit.Before;

public class BaseMediaTest {
  @Before
  public void before() {
    MediaPage mediaPage = new MediaPage();
    mediaPage.createMedia();
  }

  @After
  public void after() {
    MediaPage mediaPage = new MediaPage();
    mediaPage.deleteMedia(".*Test.*");
  }
}
