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

package com.ckkloverdos.key

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
sealed abstract class TKeySkeleton[T: Manifest](val name: String) extends TKey[T] {
  def keyType = manifest[T]

  override def hashCode = (31 * name.## + keyType.##)

  override def equals(obj: Any) = obj match {
    case that: TKeySkeleton[_] ⇒
        (this.name == that.name && this.keyType == that.keyType)
    case _ ⇒ false
  }

  override def toString = {
    val cname = getClass.getName
    val shortName = cname.substring(cname.lastIndexOf('.') + 1)
    "%s[%s](%s)".format(shortName, keyType, name)
  }

  def providesDefaultValue = false

  def compare(that: TKey[_]) = this.name compareTo that.name
}

class TKeyOnly[T: Manifest](override val name: String) extends TKeySkeleton[T](name)

class TKeyWithDefault[T: Manifest](override val name: String, val default: T) extends TKeySkeleton[T](name) {
  override def providesDefaultValue = true
}
