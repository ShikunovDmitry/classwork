package it.academy.solid.O.end;

public interface Shape {

  double calculateArea();

  class Curcle  implements Shape{
    private double radius;
    public double calculateArea(){
      return Math.PI * radius * radius;
    }
  }
  class Rectangle  implements Shape{
    private double a;
    private double b;

    public double calculateArea(){
      return a * b;
    }
  }
}
