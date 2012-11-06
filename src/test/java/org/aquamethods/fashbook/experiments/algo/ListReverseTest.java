package org.aquamethods.fashbook.experiments.algo;


import java.util.*;

public class ListReverseTest {
  public static void main(String args[]) {
    ArrayList simpsons = new ArrayList();
    simpsons.add("Bart");
    simpsons.add("Hugo");
    simpsons.add("Lisa");
    simpsons.add("Marge");
    simpsons.add("Homer");
    simpsons.add("Maggie");
    simpsons.add("Roy");
    Comparator comp = Collections.reverseOrder();
    Collections.sort(simpsons,comp);
    System.out.println(simpsons);
  }
}
    