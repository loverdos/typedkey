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

package com.ckkloverdos.key


/**
 * A key with a specific type attached.
 *
 * Keys are ordered by their names and are stored in [[com.ckkloverdos.env.Env]]ironments.
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>.
 */

trait TypedKey[T] extends Ordered[TypedKey[_]]{
  def name: String
  def keyType: Manifest[T]
  def providesDefaultValue: Boolean
}

sealed abstract class TypedKeySkeleton[T: Manifest](val name: String) extends TypedKey[T] {
  def keyType = manifest[T]

  override def hashCode = (31 * name.## + keyType.##)

  override def equals(obj: Any) = obj match {
    case that: TypedKeySkeleton[_] =>
      (this eq that) ||
      (this.name == that.name &&
       this.keyType == that.keyType &&
       this.getClass == that.getClass)
    case _ => false
  }

  override def toString = {
    val cname = getClass.getName
    val shortName = cname.substring(cname.lastIndexOf('.') + 1)
    "%s[%s](%s)".format(shortName, keyType, name)
  }

  def providesDefaultValue = false

  def compare(that: TypedKey[_]) = this.name compareTo that.name
}

class TypedKeyOnly[T: Manifest](override val name: String) extends TypedKeySkeleton[T](name)

class TypedKeyWithDefault[T: Manifest](override val name: String, val default: T) extends TypedKeySkeleton[T](name) {
  override def providesDefaultValue = true
}
