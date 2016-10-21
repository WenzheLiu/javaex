package org.wenzhe.javaex.patternmatcher;

import static org.junit.Assert.*;

import org.junit.Test;
import org.wenzhe.javaex.tryto.Try;

/**
 * @author liuwenzhe2008@qq.com
 *
 */
public class MatcherTest {
  
  static class A {
    
  }
  
  static class B extends A {
    boolean condition = true;
  }

  @Test
  public void test() {
    A o = new B();
    String s = Matcher.<A, String>match(o)
    .when(B.class, b -> b.condition, b -> {
      System.out.println("condition for " + b);
      return "good";
    })
    .when(B.class, b -> {
      return b.toString();
    })
    .otherwise()
    ;
    System.out.println(s);
  }

}
