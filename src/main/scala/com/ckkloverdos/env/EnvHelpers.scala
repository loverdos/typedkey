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

package com.ckkloverdos.env

import com.ckkloverdos.key.{TKeyWithDefault, TKeyOnly}
import java.util.Date

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
sealed class EnvHelpers {
  def newTypedKeyOnly[A](name: String, typ: Class[A]): TKeyOnly[A] = {
    new TKeyOnly[A](name)(Manifest.classType[A](typ))
  }

  def newTypedKeyWithDefault[A](name: String, value: A, typ: Class[A]): TKeyWithDefault[A] = {
    new TKeyWithDefault[A](name, value)((Manifest.classType[A](typ)))
  }

  def ByteKey(name: String): TKeyOnly[Byte] =
    new TKeyOnly(name)

  def ByteKey(name: String, value: Byte): TKeyWithDefault[Byte] =
    new TKeyWithDefault(name, value)

  def BooleanKey(name: String): TKeyOnly[Boolean] =
    new TKeyOnly(name)

  def BooleanKey(name: String, value: Boolean): TKeyWithDefault[Boolean] =
    new TKeyWithDefault(name, value)

  def ShortKey(name: String): TKeyOnly[Short] =
    new TKeyOnly(name)

  def ShortKey(name: String, value: Short): TKeyWithDefault[Short] =
    new TKeyWithDefault(name, value)

  def CharKey(name: String): TKeyOnly[Char] =
    new TKeyOnly(name)

  def CharKey(name: String, value: Char): TKeyWithDefault[Char] =
    new TKeyWithDefault(name, value)

  def IntKey(name: String): TKeyOnly[Int] =
    new TKeyOnly(name)

  def IntKey(name: String, value: Int): TKeyWithDefault[Int] =
    new TKeyWithDefault(name, value)

  def LongKey(name: String): TKeyOnly[Long] =
    new TKeyOnly(name)

  def LongKey(name: String, value: Long): TKeyWithDefault[Long] =
    new TKeyWithDefault(name, value)

  def FloatKey(name: String): TKeyOnly[Float] =
    new TKeyOnly(name)

  def FloatKey(name: String, value: Float): TKeyWithDefault[Float] =
    new TKeyWithDefault(name, value)

  def DoubleKey(name: String): TKeyOnly[Double] =
    new TKeyOnly(name)

  def DoubleKey(name: String, value: Double): TKeyWithDefault[Double] =
    new TKeyWithDefault(name, value)

  def StringKey(name: String): TKeyOnly[String] =
    new TKeyOnly(name)

  def StringKey(name: String, value: String): TKeyWithDefault[String] =
    new TKeyWithDefault(name, value)

  def DateKey(name: String): TKeyOnly[Date] =
    new TKeyOnly(name)

  def DateKey(name: String, value: Date): TKeyWithDefault[Date] =
    new TKeyWithDefault(name, value)

}

object EnvHelpers extends EnvHelpers
