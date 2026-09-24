package it.academt.at.utils;

import java.io.File;

public class ConnectionStringUtil {

  public static String getConnectionString(){
    File file=new File("src/main/resources/testDb");
    if(file.exists()){
      StringBuilder builder=new StringBuilder();
      builder.append("jdbc");
      builder.append(":");
      builder.append("sqlite");
      builder.append(":");
      builder.append(file.getAbsolutePath());
      return builder.toString();
    } else {
      System.out.println("File does not exist" + file.getAbsolutePath());
      return "";
    }
  }
}
