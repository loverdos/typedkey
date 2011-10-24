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

final case class IntKey    (name: String) extends TypedKeySkeleton[Int]    (name)
final case class LongKey   (name: String) extends TypedKeySkeleton[Long]   (name)
final case class DoubleKey (name: String) extends TypedKeySkeleton[Double] (name)
final case class BooleanKey(name: String) extends TypedKeySkeleton[Boolean](name)
final case class StringKey (name: String) extends TypedKeySkeleton[String] (name)
