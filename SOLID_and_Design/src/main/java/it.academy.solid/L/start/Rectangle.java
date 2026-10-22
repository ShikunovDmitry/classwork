package it.academy.solid.L.start;

public class Rectangle {
  protected int width;
  protected int height;

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getArea() {
    return width * height;
  }

  public static void main(String[] args) {
    Rectangle rectangle = new Square();
  }

}

class Square extends Rectangle {
  public void setWidth(int width) {
    this.width = width;
    this.height = width;
  }

  public void setHeight(int height) {
    this.height = height;
    this.width = height;
  }
}
