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

import com.ckkloverdos.key._

final class Env private[env](private[env] val map: Map[TypedKey[_], Any]) extends EnvBase[Env] {

  protected def newEnv(map: Map[TypedKey[_], Any]): Env = new Env(map)

  def +[T : Manifest](
      key: TypedKeyOnly[T],
      value: T
  ): Env = {

    new Env(map + (key -> value))
  }

  def +[T : Manifest](
      key: TypedKeyWithDefault[T],
      value: Option[T]
  ): Env = {

    new Env(map + (key -> value.getOrElse(key.default)))
  }

  def +[T : Manifest](
      key: TypedKeyWithDefault[T],
      value: T
  ): Env = {

    new Env(map + (key -> value))
  }

  def +[T : Manifest](kv: (TypedKeyOnly[T], T)): Env = new Env(map + kv)

  def ++(other: EnvBase[_]): Env = new Env(other.map ++ map)

  def -[T: Manifest](key: TypedKey[T]): Env = new Env(map - key)

  def getRemove[T: Manifest](key: TypedKey[T]): (Option[T], Env) = {
    get(key) match {
      case some @ Some(_) ⇒
        (some, this - key)

      case None ⇒
        (None, this)
    }
  }

  override def toString = "Env(%s)".format(map.mkString(", "))
}

object Env {
  def apply() = new Env(Map())
}

