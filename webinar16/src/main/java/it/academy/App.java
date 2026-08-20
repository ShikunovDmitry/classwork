package it.academy;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )

    {
        RandomStringUtils randomStringUtils = new RandomStringUtils();
      List<String> strings = new ArrayList<>();
//      for (int i = 0; i < 10; i++) {
//          String randomString = randomStringUtils.nextAlphabetic(10);
//          String caapitalizedString = StringUtils.capitalize(randomString);
//          strings.add(caapitalizedString);
//      }
        for (int i = 0; i < 10; i++) {
            strings.add(randomStringUtils.nextAlphabetic(10));
        }
        //strings.stream().map(s -> StringUtils.capitalize(s)).forEach(System.out::println);

        List<String> result = strings.stream().map(s -> StringUtils.capitalize(s)).toList();
        result.stream().forEach(System.out::println);


      //strings.forEach(System.out::println);
    }
}
