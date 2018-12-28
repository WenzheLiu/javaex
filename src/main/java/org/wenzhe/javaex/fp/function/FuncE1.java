package org.wenzhe.javaex.fp.function;

/**
 * @author wen-zhe.liu@asml.com
 *
 */
@FunctionalInterface
public interface FuncE1<T, R, E extends Exception> {

  R call(T t) throws E;
}