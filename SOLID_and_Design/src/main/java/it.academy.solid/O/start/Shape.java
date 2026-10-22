package it.academy.solid.O.start;

public class Shape {

  String shapeName;

  public double calculateArea(){
    if(shapeName.equals("SQUARE")){
      System.out.println("calculate square area");
      return 0;
    } else if(shapeName.equals("CIRCLE")){
      System.out.println("calculate circle area");
    }
    return 0;
  }

  class Curcle  extends Shape{
    private double radius;
  }

  class Rectangle  extends Shape{
    private double a;
    private double b;

  }
}
