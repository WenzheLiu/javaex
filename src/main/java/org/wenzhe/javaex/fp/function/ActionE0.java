package org.wenzhe.javaex.fp.function;

/**
 * @author wen-zhe.liu@asml.com
 *
 */
@FunctionalInterface
public interface ActionE0<E extends Exception> {

  void call() throws E;
}
