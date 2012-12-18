package com.ckkloverdos.env;

import com.ckkloverdos.key.TypedKeyOnly;

/**
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
public final class JEnvHelpers {
  private JEnvHelpers() {}

  public static Env newEnv() {
    return Env$.MODULE$.apply();
  }

  public static <A> Env add(Env env, TypedKeyOnly<A> key, A value) {
    return env.$plus(key, value, key.keyType());
  }

  public static <A> Env remove(Env env, TypedKeyOnly<A> key) {
    return env.$minus(key, key.keyType());
  }

  public static <A, B> Env add(
      Env env,
      TypedKeyOnly<A> keyA,
      A valueA,
      TypedKeyOnly<B> keyB,
      B valueB
  ) {

    return env.
      $plus(keyA, valueA, keyA.keyType()).
      $plus(keyB, valueB, keyB.keyType());
  }

  public static <A, B, C> Env add(
      Env env,
      TypedKeyOnly<A> keyA,
      A valueA,
      TypedKeyOnly<B> keyB,
      B valueB,
      TypedKeyOnly<C> keyC,
      C valueC
  ) {

    return env.
      $plus(keyA, valueA, keyA.keyType()).
      $plus(keyB, valueB, keyB.keyType()).
      $plus(keyC, valueC, keyC.keyType());
  }

  public static <A, B, C, D> Env add(
      Env env,
      TypedKeyOnly<A> keyA,
      A valueA,
      TypedKeyOnly<B> keyB,
      B valueB,
      TypedKeyOnly<C> keyC,
      C valueC,
      TypedKeyOnly<D> keyD,
      D valueD

  ) {

    return env.
      $plus(keyA, valueA, keyA.keyType()).
      $plus(keyB, valueB, keyB.keyType()).
      $plus(keyC, valueC, keyC.keyType()).
      $plus(keyD, valueD, keyD.keyType());
  }
}
