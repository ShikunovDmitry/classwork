package it.academy.solid.L.end;

public abstract class Shape {
  abstract double getArea();

  public static void main(String[] args) {
    Shape shape = new Rectangle();

    shape = new Square();
  }

}

class Rectangle extends Shape {
  protected int width;
  protected int height;

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public double getArea() {
    return width * height;
  }
}

class Square extends Shape {
  private int side;

  public void setSide(int side) {
    this.side = side;
  }

  public double getArea() {
    return side * side;
  }
}
