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

/**
 * A key with a specific type attached.
 *
 * Keys are ordered by their names and are stored in [[typedkey.env.Env]]ironments.
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>.
 */
trait TKey[T] extends Ordered[TKey[_]] {
  def name: String
  def keyType: KeyType

  override def hashCode = 31 * name.## + keyType.##

  override def equals(obj: Any) = obj match {
    case that: TKey[_] ⇒
      this.name == that.name && this.keyType == that.keyType
    case _ ⇒ false
  }

  def compare(that: TKey[_]) = this.name compareTo that.name
}
