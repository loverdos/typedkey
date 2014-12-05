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
package immutable

import scala.collection.immutable.Map
import typedkey.{env, TKey}
import typedkey.env.impl.MapBasedEnv

final class Env(private[this] val map: Map[TKey[_], Any]) extends MapBasedEnv[Env, Map[TKey[_], Any]](map) {
  protected def newEnv(map: Map[TKey[_], Any]) = new Env(map)

  protected def newMap(elems: (TKey[_], Any)*) = Map(elems: _*)

  def toImmutable: Env = this

  def toMutable: mutable.Env = new mutable.Env(scala.collection.mutable.Map() ++= map)

  def update[T](key: TKey[T], value: T) = {
    val newMap = map.updated(key, value)
    new Env(newMap)
  }

  def delete[T](key: TKey[T]) =
    if(contains(key)) newEnv(map - key)
    else this
}

object Env {
  def apply(): Env = new Env(Map())

  def ofOne[T](key: TKey[T], value: T): Env = new Env(Map(key → value))
}

