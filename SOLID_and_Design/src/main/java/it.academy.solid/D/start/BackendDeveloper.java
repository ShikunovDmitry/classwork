package it.academy.solid.D.start;

public class BackendDeveloper {
  public void writeJavaCode() {

  }
}

class Project {
  private BackendDeveloper backendDeveloper;

  public Project() {
    backendDeveloper = new BackendDeveloper();
  }

  public void implement() {
    backendDeveloper.writeJavaCode();
  }
}
class NewProject  {
  private BackendDeveloper  backendDeveloper;
  public NewProject(BackendDeveloper  backendDeveloper) {
    backendDeveloper = backendDeveloper;
  }
}
