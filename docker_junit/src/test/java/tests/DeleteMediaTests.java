package tests;

import it.academy.project.pages.MediaPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeleteMediaTests extends BaseMediaTest{

  @Test
  public void deleteMedia() {
    MediaPage mediaPage = new MediaPage();
    mediaPage.deleteMedia(".*Test.*");
    Assert.assertTrue(true);
  }

}
