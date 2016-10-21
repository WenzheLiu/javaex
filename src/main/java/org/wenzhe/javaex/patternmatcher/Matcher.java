package org.wenzhe.javaex.patternmatcher;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * pattern match, learn from Scala's match case
 * 
 * @author liuwenzhe2008@qq.com
 *
 * @param <T>
 * @param <R>
 */
public class Matcher<T, R> {
  
  private T obj;
  private R result;
  private boolean matched = false;

  Matcher(T o) {
    this.obj = o;
  }
  
  public static <T, R> Matcher<T, R> match(T o) {
    return new Matcher<>(o);
  }
  
  public <P extends T> Matcher<T, R> when(Class<P> cls, Consumer<P> procedure) {
    if (!matched && cls.isInstance(obj)) {
      procedure.accept(cls.cast(obj));
      matched = true;
    }
    return this;
  }
  
  public <P extends T> Matcher<T, R> when(Class<P> cls, Function<P, R> function) {
    if (!matched && cls.isInstance(obj)) {
      result = function.apply(cls.cast(obj));
      matched = true;
    }
    return this;
  }
  
  public <P extends T> Matcher<T, R> when(Class<P> cls, Predicate<P> condition, Consumer<P> procedure) {
    if (!matched && cls.isInstance(obj)) {
      P p = cls.cast(obj);
      if (condition.test(p)) {
        procedure.accept(cls.cast(obj));
        matched = true;
      }
    }
    return this;
  }
  
  public <P extends T> Matcher<T, R> when(Class<P> cls, Predicate<P> condition, Function<P, R> function) {
    if (!matched && cls.isInstance(obj)) {
      P p = cls.cast(obj);
      if (condition.test(p)) {
        result = function.apply(cls.cast(obj));
        matched = true;
      }
    }
    return this;
  }
  
  public <P extends T> Matcher<T, R> when(P t, Consumer<P> procedure) {
    if (!matched && Objects.equals(obj, t)) {
      procedure.accept(t);
      matched = true;
    }
    return this;
  }
  
  public <P extends T> Matcher<T, R> when(P t, Function<P, R> function) {
    if (!matched && Objects.equals(obj, t)) {
      result = function.apply(t);
      matched = true;
    }
    return this;
  }
  
  public void otherwise(Consumer<T> procedure) {
    if (!matched) {
      procedure.accept(obj);
    }
  }
  
  public R otherwise(Function<T, R> function) {
    if (!matched) {
      result = function.apply(obj);
    }
    return result;
  }
  
  public R otherwise() {
    return result;
  }
}
