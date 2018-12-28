package org.wenzhe.javaex.fp.function;

/**
 * @author wen-zhe.liu@asml.com
 *
 */
@FunctionalInterface
public interface FuncE0<R, E extends Exception> {

  R call() throws E;
}
