/*
 * Copyright 2011-2013 Christos KK Loverdos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ckkloverdos.env;

import com.ckkloverdos.key.TKeyOnly;
import com.ckkloverdos.key.TKeyWithDefault;

/**
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
public final class JEnvHelpers {
  private JEnvHelpers() {}

  private static final EnvHelpers envHelpers = new EnvHelpers();

  public static Env newEnv() {
    return Env$.MODULE$.apply();
  }

  public static <A> Env add(Env env, TKeyOnly<A> key, A value) {
    return env.$plus(key, value, key.keyType());
  }

  public static <A> Env remove(Env env, TKeyOnly<A> key) {
    return env.$minus(key, key.keyType());
  }

  public static <A> TKeyOnly<A> newTypedKeyOnly(String name, Class<A> typ) {
    return envHelpers.newTypedKeyOnly(name, typ);
  }

  public static <A> TKeyWithDefault<A> newTypedKeyWithDefault(String name, A value, Class<A> typ) {
    return envHelpers.newTypedKeyWithDefault(name, value, typ);
  }

  public static <A, B> Env add(
      Env env,
      TKeyOnly<A> keyA, A valueA,
      TKeyOnly<B> keyB, B valueB
  ) {

    return env.
      $plus(keyA, valueA, keyA.keyType()).
      $plus(keyB, valueB, keyB.keyType());
  }

  public static <A, B, C> Env add(
      Env env,
      TKeyOnly<A> keyA, A valueA,
      TKeyOnly<B> keyB, B valueB,
      TKeyOnly<C> keyC, C valueC
  ) {

    return env.
      $plus(keyA, valueA, keyA.keyType()).
      $plus(keyB, valueB, keyB.keyType()).
      $plus(keyC, valueC, keyC.keyType());
  }

  public static <A, B, C, D> Env add(
      Env env,
      TKeyOnly<A> keyA, A valueA,
      TKeyOnly<B> keyB, B valueB,
      TKeyOnly<C> keyC, C valueC,
      TKeyOnly<D> keyD, D valueD

  ) {

    return env.
      $plus(keyA, valueA, keyA.keyType()).
      $plus(keyB, valueB, keyB.keyType()).
      $plus(keyC, valueC, keyC.keyType()).
      $plus(keyD, valueD, keyD.keyType());
  }
}
