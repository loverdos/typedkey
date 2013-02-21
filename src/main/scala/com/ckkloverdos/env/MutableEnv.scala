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

final class MutableEnv private[env](private[env] var map: Map[TKey[_], Any]) extends EnvBase[MutableEnv] {
  protected def newEnv(map: Map[TKey[_], Any]): MutableEnv = new MutableEnv(map)

  def +[T : Manifest](key: TKey[T], value: T): this.type = {
    map += key -> value
    this
  }

  def +[T : Manifest](kv: (TKey[T], T)): this.type = {
    map += kv
    this
  }

  def +(kv: (String, Env)): this.type = {
    this + (EnvKey(kv._1), kv._2)
  }

  def ++(other: EnvBase[_]): this.type = {
    map ++= other.map
    this
  }

  def -[T: Manifest](key: TKey[T]): this.type = {
    map -= key
    this
  }

  def getRemove[T: Manifest](key: TKey[T]): Option[T] = {
    get(key) match {
      case some @ Some(_) ⇒
        map -= key
        some

      case None ⇒
        None
    }
  }

  override def toString = "MutableEnv(%s)".format(map.mkString(", "))
}


object MutableEnv {
  def apply() = new MutableEnv(Map())
}

