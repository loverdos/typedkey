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
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
final class KeyType(val tpe: AnyRef, val tpeName: CharSequence) extends Ordered[KeyType] {
  if(tpe == null) {
    throw new IllegalArgumentException("null type")
  }
  if(tpeName == null) {
    throw new IllegalArgumentException("null type name")
  }

  def this(tpe: AnyRef) = this(tpe, tpe.getClass.getName)

  def compare(that: KeyType) =
    this.tpeName.toString.compareTo(that.tpeName.toString)

  override def equals(o: Any) =
    o match {
      case that: KeyType ⇒
        this.tpe == that.tpe && this.tpeName == that.tpeName
      case _ ⇒
        false
    }

  override def hashCode() = tpe.## * 31 + tpeName.##

  override def toString: String = tpe.toString
}
