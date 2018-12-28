package org.wenzhe.javaex.tryto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author liuwenzhe2008@qq.com
 *
 */
public class TryTest {

  @Test
  public void test() {
    Try<Integer> ti = Try.to(() -> {
      throw new Exception("");
      ///return 1;
    });
    assertEquals(0, (int) ti.map(i -> i * 2).getOrElse(0));
  }

}
