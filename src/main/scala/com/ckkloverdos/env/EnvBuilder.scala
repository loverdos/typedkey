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

class EnvBuilder(__map: Map[TypedKey[_], Any] = Map()) {
  @volatile
  private[this] var _map = __map

  def +[T : Manifest](key: TypedKey[T], value: T): this.type = {
    _map += key -> value
    this
  }

  def +[T: Manifest](kv: (TypedKey[T], T)): this.type = {
    _map += kv
    this
  }

  def ++(other: Env): this.type = {
    _map = _map ++ other.map
    this
  }

  def build: Env = new Env(_map)
}