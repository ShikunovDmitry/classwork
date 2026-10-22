package it.academy.solid.I.start;

public interface Worker {
  void work();

  void eat();

  void sleep();
}

class Robot implements Worker {
  @Override
  public void work() {

  }

  @Override
  public void eat() {
// can't
  }

  @Override
  public void sleep() {
//can't
  }
}

class Human implements Worker {
  @Override
  public void work() {

  }

  @Override
  public void eat() {

  }

  @Override
  public void sleep() {

  }
}
