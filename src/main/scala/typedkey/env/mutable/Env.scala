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
package mutable

import typedkey.{env, TKey}
import typedkey.env.impl.MapBasedEnv

import scala.collection.{mutable ⇒ smut}

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */

final class Env(private[this] val map: smut.Map[TKey[_], Any] ) extends MapBasedEnv[Env, smut.Map[TKey[_], Any]](map) {
  protected def newEnv(map: smut.Map[TKey[_], Any]) = new Env(map)

  protected def newMap(elems: (TKey[_], Any)*) = smut.Map(elems: _*)

  def toImmutable: immutable.Env = new immutable.Env(map.toMap)

  def toMutable: Env = this

  def update[T](key: TKey[T], value: T) = {
    map += key → value
    this
  }

  def delete[T](key: TKey[T]) = {
    map -= key
    this
  }
}

object Env {
  def apply(): Env = new Env(smut.Map())

  def ofOne[T](key: TKey[T], value: T): Env = new Env(smut.Map(key → value))
}


