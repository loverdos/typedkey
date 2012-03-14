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

package com.ckkloverdos.env

import com.ckkloverdos.key.TypedKey
import com.ckkloverdos.maybe.{NoVal, Maybe}

class Env(val map: Map[TypedKey[_], Any] = Map()) {
  /**
   * Get a value or throw an exception if it does not exist.
   */
  @throws(classOf[NoSuchElementException])
  def getEx[T : Manifest](key: TypedKey[T]): T = {
    map(key).asInstanceOf[T]
  }

  def get[T : Manifest](key: TypedKey[T]): Maybe[T] = {
    map.get(key) match {
      case Some(value) ⇒ Maybe(value.asInstanceOf[T])
      case None        ⇒ NoVal
    }
  }

  def ++(other: Map[TypedKey[_], Any]): Env = new Env(map ++ other)
  def ++(other: Env): Env = new Env(this.map ++ other.map)

  override def hashCode() = map.##
  override def equals(any: Any): Boolean = {
    any match {
      case that: Env ⇒ this.map == that.map && this.getClass == that.getClass
      case _         ⇒ false
    }
  }
  override def toString = "Env(%s)".format(map.mkString(", "))

  def size = map.size

  def keySet = map.keySet

  def keysIterator = map.keysIterator

  def keysOfName(keyName: String): Set[TypedKey[_]] = {
    for {
      typedKey <- map.keySet if(typedKey.name == keyName)
    } yield {
      typedKey
    }
  }

  def selectName(keyName: String): Env =
    new Env(Map(keysOfName(keyName).toSeq.map(tk => (tk, map(tk))): _*))

  def keysOfType[T : Manifest]: Set[TypedKey[T]] = {
    for {
      typedKey <- map.keySet if(manifest[T] == typedKey.keyType)
    } yield {
      typedKey.asInstanceOf[TypedKey[T]]
    }
  }

  def selectType[T : Manifest]: Env =
    new Env(Map(keysOfType[T].toSeq.map(tk => (tk, map(tk))): _*))

  def contains[T : Manifest](key: TypedKey[T]): Boolean = {
    map.contains(key)
  }

  def toBuilder = new EnvBuilder(map)


}

