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

import com.ckkloverdos.key.TKey

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */

abstract class EnvBase[E <: EnvBase[E]] { self: E ⇒
  private[env] def map: Map[TKey[_], Any]
  protected def newEnv(map: Map[TKey[_], Any]): E

  def selectName(keyName: String): E =
    newEnv(Map(keysOfName(keyName).toSeq.map(tk => (tk, map(tk))): _*))

  def selectType[T : Manifest]: E =
    newEnv(Map(keysOfType[T].toSeq.map(tk => (tk, map(tk))): _*))

  def contains[T : Manifest](key: TKey[T]): Boolean = {
    map.contains(key)
  }

  def keysOfType[T : Manifest]: Set[TKey[T]] = {
    for {
      typedKey <- map.keySet if(manifest[T] == typedKey.keyType)
    } yield {
      typedKey.asInstanceOf[TKey[T]]
    }
  }

  /**
   * Returns a `Map` whose keys are the names of the typed keys. Beware that typed keys may have the same names,
   * so this can lead to loss of key-value pairs.
   */
  def toMap: Map[String, Any] = {
    map.map{case (tk, vt) ⇒ (tk.name, vt)}
  }

  protected def fillJMap[M <: java.util.Map[String, AnyRef]](jmap: M): M = {
    for((tk, vt) ← map) {
      jmap.put(tk.name, vt.asInstanceOf[AnyRef])
    }
    jmap
  }

   /**
    * Returns a [[java.util.Map]] whose keys are the names of the typed keys. Beware that typed keys may have the
    * same names, so this can lead to loss of key-value pairs.
    */
   def toJavaMap: java.util.Map[String, AnyRef] = {
     fillJMap(new java.util.HashMap[String, AnyRef](size))
   }

  /**
   * Get a value or throw an exception if it does not exist.
   */
  @throws(classOf[NoSuchElementException])
  @throws(classOf[ClassCastException])
  def apply[T : Manifest](key: TKey[T]): T = {
    map(key).asInstanceOf[T]
  }

  /**
   * Get a value or throw an exception if it does not exist.
   */
  @inline
  @throws(classOf[NoSuchElementException])
  @throws(classOf[ClassCastException])
  def getEx[T : Manifest](key: TKey[T]): T = {
    this apply key
  }

  def get[T : Manifest](key: TKey[T]): Option[T] = {
    map.get(key).asInstanceOf[Option[T]]
  }

  def getOrElse[T : Manifest](key: TKey[T], default: T): T = {
    map.get(key) match {
      case Some(value) ⇒
        value.asInstanceOf[T]

      case None ⇒
        default
    }
  }

  def getByName(keyName: String): Set[Any] = {
    keysOfName(keyName).map(map.apply)
  }

  def size = map.size

  def keySet = map.keySet

  def keysIterator = map.keysIterator

  def keysOfName(keyName: String): Set[TKey[_]] = {
    for {
      typedKey <- map.keySet if(typedKey.name == keyName)
    } yield {
      typedKey
    }
  }


  override def hashCode() = map.##

  override def equals(any: Any): Boolean = {
    any match {
      case that: EnvBase[_] ⇒
        this.map == that.map && this.getClass == that.getClass

      case _ ⇒
        false
    }
  }
}
