package it.academy.solid.I.end;

interface Printer {
  void print(String doc);
}

interface Scanner {
  String scan();
}

class MfpDevice implements Printer, Scanner {
  public void print(String doc) { System.out.println(doc); }
  public String scan() { return "scanned"; }
}

class SimplePrinter implements Printer {
  public void print(String doc) { System.out.println(doc); }
}
