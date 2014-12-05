/*
 * Copyright 2011-2014 Christos KK Loverdos
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

package typedkey.env

import typedkey.{TKey, KeyType}

/**
 * An environment of [[typedkey.TKey]]s.
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
trait Env {
  def get[T](key: TKey[T]): Option[T]

  def getEx[T](key: TKey[T]): T

  def apply[T](key: TKey[T]): T

  def getOr[T](key: TKey[T], default: T): T

  def contains[T](key: TKey[T]): Boolean

  def isEmpty: Boolean

  def size: Int

  def selectName(keyName: String): Env

  def selectType(keyType: KeyType): Env

  def valuesOfKeysByName(keyName: String): Seq[Any]

  def keySet: scala.collection.Set[TKey[_]]

  def keysIterator: Iterator[TKey[_]]

  def keyValueIterator: Iterator[(TKey[_], Any)]

  def keysOfName(keyName: String): scala.collection.Set[TKey[_]]

  def toMap: scala.collection.Map[TKey[_], Any]

  def toMapByName: scala.collection.Map[String, Any]

  def toJavaMapByName(fillMe: java.util.Map[String, AnyRef]): java.util.Map[String, AnyRef]

  def toImmutable: immutable.Env

  def toMutable: mutable.Env

  def ++(other: Env): Env

  def getRemove[T](key: TKey[T]): (Option[TKey[T]], Env)

  def keysOfType(keyType: KeyType): scala.collection.Set[TKey[_]]

  def update[T](key: TKey[T], value: T): Env

  def delete[T](key: TKey[T]): Env

  def +[T](key: TKey[T], value: T): Env = update(key, value)
  def -[T](key: TKey[T]): Env = delete(key)
}
