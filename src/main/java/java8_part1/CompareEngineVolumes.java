package java8_part1;


import java.util.function.BinaryOperator;


public class CompareEngineVolumes implements BinaryOperator<Integer> {


  @Override
  public Integer apply(Integer integer, Integer integer2) {
    return integer - integer2;
  }
}

//