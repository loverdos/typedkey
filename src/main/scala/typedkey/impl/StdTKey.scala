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

package typedkey
package impl

import scala.reflect.ClassTag

/** Standard implementation of a [[typedkey.TKey]] */
class StdTKey[T](val keyType: KeyType, val name: String) extends TKey[T] {
  override def toString =
    new java.lang.StringBuilder().
      append("TKey[").
        append(name).append(": ").append(keyType).
      append(']').
      toString
}

/** Standard Scala implementation of a [[typedkey.TKey]], using a [[scala.reflect.ClassTag]]. */
class TagKey[T: ClassTag](override val name: String) extends StdTKey[T](keyTypeOf[T], name)
