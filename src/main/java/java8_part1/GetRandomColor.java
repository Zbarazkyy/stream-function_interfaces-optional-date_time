package java8_part1;

import java.util.Random;
import java.util.function.Supplier;

public class GetRandomColor implements Supplier {
  private static final String[] COLORS = {"red", "green", "blue", "yellow", "pink",
    "black", "white"};

  @Override
  public String get() {
    int index = new Random().nextInt(6);
    return COLORS[index];
  }
}