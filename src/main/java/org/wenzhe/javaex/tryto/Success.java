package org.wenzhe.javaex.tryto;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author liuwenzhe2008@qq.com
 *
 */
public class Success<T> extends Try<T> {
  
  private final T value;

  Success(T value) {
    this.value = value;
  }

  @Override
  public boolean isFailure() {
    return false;
  }

  @Override
  public boolean isSuccess() {
    return true;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public void foreach(Consumer<T> f) {
    f.accept(value);
  }

  @Override
  public <U> Try<U> flatMap(Function<T, Try<U>> f) {
    try {
      return f.apply(value);
    } catch (RuntimeException e) {
      return new Failure<>(e);
    }
  }

  @Override
  public <U> Try<U> map(Function<T, U> f) {
    return Try.to(() -> f.apply(value));
  }

  @Override
  public Try<T> filter(Predicate<T> p) {
    try {
      if (p.test(value)) {
        return this;
      } else {
        return new Failure<>(new NoSuchElementException("Predicate does not hold for " + value));
      }
    } catch (RuntimeException e) {
      return new Failure<>(e);
    }
  }

  @Override
  public Try<T> recoverWith(Function<RuntimeException, Try<T>> f) {
    return this;
  }

  @Override
  public Try<T> recover(Function<RuntimeException, T> f) {
    return this;
  }

  @Override
  public Try<RuntimeException> failed() {
    return new Failure<>(new UnsupportedOperationException("Success.failed"));
  }

  @Override
  public <U> Try<U> transform(Function<T, Try<U>> s, Function<RuntimeException, Try<U>> f) {
    try {
      return s.apply(((Success<T>) this).get());
    } catch (RuntimeException e) {
      return new Failure<>(e);
    }
  }

}
