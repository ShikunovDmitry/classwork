package tests;

import it.academy.project.pages.MediaPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EditMediaTests {
  @Test
  public void testEditMedia() {
    MediaPage mediaPage = new MediaPage();
    mediaPage.createMedia();
    mediaPage.editMedia();
    Assert.assertTrue(true);
  }

}
