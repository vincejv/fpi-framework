package com.abavilla.fpi.fw.util;

import java.time.Duration;
import java.util.function.Predicate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public final class UniUtil {

  private static int backoffRetrySec;

  private static double jitterFactorRetry;

  private static long maxRetries;

  @ConfigProperty(name = "retry.back-off-sec", defaultValue = "3")
  private int backoffRetrySecConfig;

  @ConfigProperty(name = "retry.jitter", defaultValue = "0.2")
  private double jitterFactorRetryConfig;

  @ConfigProperty(name = "retry.max-retries", defaultValue = "5")
  private long maxRetriesConfig;

  public synchronized void init(@Observes StartupEvent evt) {
    backoffRetrySec = backoffRetrySecConfig;
    jitterFactorRetry = jitterFactorRetryConfig;
    maxRetries = maxRetriesConfig;
  }

  public static <T> Uni<T> retry(Uni<T> uni, Class<? extends Throwable> causingException) {
    return uni.onFailure(causingException).retry()
      .withBackOff(Duration.ofSeconds(backoffRetrySec))
      .withJitter(jitterFactorRetry)
      .atMost(maxRetries);
  }

  public static <T> Uni<T> retry(Uni<T> uni, Predicate<? super Throwable> predicate) {
    return uni.onFailure(predicate).retry()
      .withBackOff(Duration.ofSeconds(backoffRetrySec))
      .withJitter(jitterFactorRetry)
      .atMost(maxRetries);
  }

  public static <T> Uni<T> retry(Uni<T> uni) {
    return uni.onFailure().retry()
      .withBackOff(Duration.ofSeconds(backoffRetrySec))
      .withJitter(jitterFactorRetry)
      .atMost(maxRetries);
  }

  public static <T> Uni<T> retryMax(Uni<T> uni, Class<? extends Throwable> causingException) {
    return uni.onFailure(causingException).retry()
      .withBackOff(Duration.ofSeconds(backoffRetrySec))
      .withJitter(jitterFactorRetry)
      .indefinitely();
  }

  public static <T> Uni<T> retryMax(Uni<T> uni) {
    return uni.onFailure().retry()
      .withBackOff(Duration.ofSeconds(backoffRetrySec))
      .withJitter(jitterFactorRetry)
      .indefinitely();
  }

  public static <T> Uni<T> retryUntil(Uni<T> uni, Class<? extends Throwable> causingException, 
                                      Predicate<? super Throwable> bound) {
    return uni.onFailure(causingException).retry()
      .withBackOff(Duration.ofSeconds(backoffRetrySec))
      .withJitter(jitterFactorRetry)
      .until(bound);
  }

  public static <T> Uni<T> retryUntil(Uni<T> uni, Predicate<? super Throwable> bound) {
    return uni.onFailure().retry()
      .withBackOff(Duration.ofSeconds(backoffRetrySec))
      .withJitter(jitterFactorRetry)
      .until(bound);
  }
}
