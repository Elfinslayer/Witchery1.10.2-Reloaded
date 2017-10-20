package com.emoniph.witchery.util;

public class Count { protected int count;
  
  public Count() {}
  
  public void increment() { count += 1; }
  
  public void decrement()
  {
    count -= 1;
  }
  
  public int get() {
    return count;
  }
  
  public void incrementBy(int quantity) {
    count += quantity;
  }
}
