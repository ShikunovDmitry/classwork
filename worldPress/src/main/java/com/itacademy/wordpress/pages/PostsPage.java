package com.itacademy.wordpress.pages;

import com.itacademy.wordpress.elements.NewPostForm;
import com.itacademy.wordpress.elements.Table;

public class PostsPage extends BasePage{

  private final By postsTableLocator;
  private NewPostForm newPostForm;
  private Table postsTable;


  public PostsPage() {
    postsTable = new Table(driver.findElementsBy(postsTableLocator));
  }


  public NewPostForm addNewPost() {
    //buttonClick
    return newPostForm;
  }
}
