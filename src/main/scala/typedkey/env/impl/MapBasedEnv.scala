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

package typedkey.env.impl

import typedkey.env.{immutable, mutable, Env}
import typedkey.{env, KeyType, TKey}

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
abstract class MapBasedEnv[E <: Env, Map <: scala.collection.Map[TKey[_], Any]](map: Map) extends Env {
  protected def newEnv(map: Map): E

  protected def newMap(elems: (TKey[_], Any)*): Map

  def get[T](key: TKey[T]) = map.get(key).asInstanceOf[Option[T]]

  def getEx[T](key: TKey[T]) = map(key).asInstanceOf[T]

  def apply[T](key: TKey[T]) = getEx(key)

  def getOr[T](key: TKey[T], default: T) =
    map.get(key) match {
      case Some(value) ⇒
        value.asInstanceOf[T]

      case None ⇒
        default
    }

  def contains[T](key: TKey[T]) = map.contains(key)

  def isEmpty = map.isEmpty

  def size = map.size

  def keySet = map.keySet

  def keysIterator = map.keysIterator

  def keyValueIterator = map.iterator

  def keysOfName(keyName: String) =
    for {
      typedKey ← map.keySet if typedKey.name == keyName
    } yield {
      typedKey
    }

  def toMap = map

  def toMapByName = map.map {
    case (tk, vt) ⇒ (tk.name, vt)
  }

  def getRemove[T](key: TKey[T]) =
    contains(key) match {
      case true ⇒
        (Some(key), this - key)
      case false ⇒
        (None, this)
    }

  def ++(other: typedkey.env.Env) = newEnv((map ++ other.toMap).asInstanceOf[Map])

  def keysOfType(keyType: KeyType): scala.collection.Set[TKey[_]] =
    map.keySet.filter(_.keyType == keyType)

  def selectName(keyName: String) =
    newEnv(newMap(keysOfName(keyName).toSeq.map(tk ⇒ (tk, map(tk))): _*))

  def selectType(keyType: KeyType) =
    newEnv(newMap(keysOfType(keyType).toSeq.map(tk => (tk, map(tk))): _*))

  def toJavaMapByName(fillMe: java.util.Map[String, AnyRef]) = {
    for((tk, vt) ← map) {
      fillMe.put(tk.name, vt.asInstanceOf[AnyRef])
    }
    fillMe
  }

  def valuesOfKeysByName(keyName: String): Seq[Any] = {
    val buf = new scala.collection.mutable.ListBuffer[Any]
    val keys = keysOfName(keyName)
    for(key ← keys) {
      buf += map(key)
    }
    buf.toList
  }

  def toString(separator: String = ", ") = {
    val formattedPairs = map.map {
      case (key, null) ⇒
        s"${key.name}: null [${key.keyType.tpeName}]"

      case (key, value) ⇒
        s"${key.name}: $value [${key.keyType.tpeName}]"
    }

    val pairStr = formattedPairs.mkString(separator)

    s"Env($pairStr)"
  }
}
