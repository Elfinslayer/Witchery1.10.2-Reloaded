package com.emoniph.witchery.util;

import java.util.NavigableMap;
import java.util.Random;

public class RandomCollection<E>
{
  private final NavigableMap<Double, E> map = new java.util.TreeMap();
  private final Random random;
  private double total = 0.0D;
  
  public RandomCollection() {
    this(new Random());
  }
  
  public RandomCollection(Random random) {
    this.random = random;
  }
  
  public void add(double weight, E result) {
    if (weight > 0.0D) {
      total += weight;
      map.put(Double.valueOf(total), result);
    }
  }
  
  public E next() {
    double value = random.nextDouble() * total;
    return map.ceilingEntry(Double.valueOf(value)).getValue();
  }
}
