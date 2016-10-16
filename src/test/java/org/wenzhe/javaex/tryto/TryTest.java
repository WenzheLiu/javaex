package org.wenzhe.javaex.tryto;

import static org.junit.Assert.*;

import org.junit.Test;
import org.wenzhe.javaex.tryto.Try;

/**
 * @author liuwenzhe2008@qq.com
 *
 */
public class TryTest {

  @Test
  public void test() {
    Try<Integer> ti = Try.to(() -> {
      throw new RuntimeException("");
      ///return 1;
    });
    System.out.println(ti.map(i -> i * 2).getOrElse(0));
  }

}
