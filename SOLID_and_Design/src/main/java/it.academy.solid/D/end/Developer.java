package it.academy.solid.D.end;

public interface Developer {
  void develop();
}

class BackendDeveloper implements Developer{
  @Override
  public void develop() {
    //Write code on Java
  }
}

class DotNetDeveloper implements Developer{
  @Override
  public void develop() {
    // Write  code on C#
  }
}

class Project{
  private Developer developer;
  public Project(Developer developer){
    this.developer = developer;
  }

  public void implement(){
    this.developer.develop();
  }

  public static void main(String[] args) {
    Project project = new Project(new BackendDeveloper());

    project.implement();
  }
}
