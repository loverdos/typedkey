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
import typedkey.TKey
import typedkey.env.impl.MapBasedEnv

final class Env private[env](
  private[env] val map: Map[TKey[_], Any]
) extends MapBasedEnv[Env, Map[TKey[_], Any]](map) {

  protected def newEnv(map: Map[TKey[_], Any]) = new Env(map)

  protected def newMap(elems: (TKey[_], Any)*) = Map(elems: _*)
}

object Env {
  def apply(): Env = new Env(Map())

  def apply(env: Env): Env = new Env(Map() ++ env.map)
}

