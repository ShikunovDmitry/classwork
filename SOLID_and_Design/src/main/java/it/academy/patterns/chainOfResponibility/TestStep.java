package it.academy.patterns.chainOfResponibility;

public abstract class TestStep {
  protected TestStep nextStep;

  public TestStep setNextStep(TestStep testStep) {
    this.nextStep = testStep;
    return nextStep;
  }

  public void execute() {
    performAction();
    if (this.nextStep != null) {
      nextStep.execute();
    }
  }

  protected abstract void performAction();
}
