package org.wenzhe.javaex.tryto;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author liuwenzhe2008@qq.com
 *
 */
public class Failure<T> extends Try<T> {
  
  private final RuntimeException exception;

  Failure(RuntimeException e) {
    exception = e;
  }

  @Override
  public boolean isFailure() {
    return true;
  }

  @Override
  public boolean isSuccess() {
    return false;
  }

  @Override
  public T get() {
    throw exception;
  }

  @Override
  public void foreach(Consumer<T> f) {
    // do nothing
  }

  @SuppressWarnings("unchecked")
  @Override
  public <U> Try<U> flatMap(Function<T, Try<U>> f) {
    return (Try<U>) this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <U> Try<U> map(Function<T, U> f) {
    return (Try<U>) this;
  }

  @Override
  public Try<T> filter(Predicate<T> p) {
    return this;
  }

  @Override
  public Try<T> recoverWith(Function<RuntimeException, Try<T>> f) {
    try {
      return f.apply(exception);
    } catch (RuntimeException e) {
      return new Failure<>(e);
    }
  }

  @Override
  public Try<T> recover(Function<RuntimeException, T> f) {
    try {
      return Try.to(() -> f.apply(exception));
    } catch (RuntimeException e) {
      return new Failure<>(e);
    }
  }

  @Override
  public Try<RuntimeException> failed() {
    return new Success<>(exception);
  }

  @Override
  public <U> Try<U> transform(Function<T, Try<U>> s, Function<RuntimeException, Try<U>> f) {
    try {
      return f.apply(exception);
    } catch (RuntimeException e) {
      return new Failure<>(e);
    }
  }

}
