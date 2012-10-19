/*
 * Copyright 2011 Christos KK Loverdos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ckkloverdos.key

final case class ByteKey   (override val name: String) extends TypedKeyOnly[Byte]   (name)
final case class BooleanKey(override val name: String) extends TypedKeyOnly[Boolean](name)
final case class ShortKey  (override val name: String) extends TypedKeyOnly[Short]  (name)
final case class CharKey   (override val name: String) extends TypedKeyOnly[Char]   (name)
final case class IntKey    (override val name: String) extends TypedKeyOnly[Int]    (name)
final case class LongKey   (override val name: String) extends TypedKeyOnly[Long]   (name)
final case class FloatKey  (override val name: String) extends TypedKeyOnly[Float]  (name)
final case class DoubleKey (override val name: String) extends TypedKeyOnly[Double] (name)
final case class StringKey (override val name: String) extends TypedKeyOnly[String] (name)

final case class ListKey [T: Manifest](override val name: String) extends TypedKeyOnly[List[T]](name)
final case class ArrayKey[T: Manifest](override val name: String) extends TypedKeyOnly[Array[T]](name)
final case class SetKey  [T: Manifest](override val name: String) extends TypedKeyOnly[Set[T]](name)

final case class ByteKeyDefault(override val name: String, override val default: Byte)
  extends TypedKeyWithDefault[Byte](name, default)

final case class BooleanKeyDefault(override val name: String, override val default: Boolean)
  extends TypedKeyWithDefault[Boolean](name, default)

final case class ShortKeyDefault(override val name: String, override val default: Short)
  extends TypedKeyWithDefault[Short](name, default)

final case class CharKeyDefault(override val name: String, override val default: Char)
  extends TypedKeyWithDefault[Char](name, default)

final case class IntKeyDefault(override val name: String, override val default: Int)
  extends TypedKeyWithDefault[Int](name, default)

final case class LongKeyDefault(override val name: String, override val default: Long)
  extends TypedKeyWithDefault[Long](name, default)

final case class FloatKeyDefault(override val name: String, override val default: Float)
  extends TypedKeyWithDefault[Float](name, default)

final case class DoubleKeyDefault(override val name: String, override val default: Double)
  extends TypedKeyWithDefault[Double](name, default)

final case class StringKeyDefault(override val name: String, override val default: String)
  extends TypedKeyWithDefault[String](name, default)