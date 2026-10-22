package it.academy.solid.I.end;

import java.awt.*;

public interface Worker {
  void work();
}

interface Eater{
  void eat();
}

interface Sleeper{
  void sleep();
}

class Robot implements Worker {
  @Override
  public void work() {

  }
}

class Human implements Worker, Eater, Sleeper {
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
