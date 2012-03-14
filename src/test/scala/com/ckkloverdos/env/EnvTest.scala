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

import org.junit.Assert
import org.junit.Test
import com.ckkloverdos.key.{IntKey, StringKey}
import com.ckkloverdos.maybe.Just

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
class EnvTest {
  val Hello_World = "Hello.World"
  val Just_another_Key = "Just.another.Key"

  val strKey1 = StringKey(Hello_World)
  val strVal1 = "Value 1"
  val intKey1 = IntKey(Just_another_Key)
  val intVal1 = 12
  val strKey2 = StringKey(Just_another_Key)
  val strVal2 = "Value 2"
  val intKey2 = IntKey(Hello_World)
  val intVal2 = 500

  lazy val envb = new EnvBuilder() +
    (strKey1, strVal1) +
    (intKey1, intVal1) +
    (intKey2, intVal2) +
    (strKey2, strVal2)

  lazy val env = envb.build

  @Test
  def testStringKey: Unit = {
    val strValue = env.get(strKey1)
    Assert.assertEquals(Just(strVal1), strValue)
  }

  @Test
  def testIntKey: Unit = {
    val intValue = env.get(intKey1)
    Assert.assertEquals(Just(intVal1), intValue)
  }

  @Test
  def testKeysOfType: Unit = {
    val expectedSet = Set(strKey1, strKey2)
    val computedSet = env.keysOfType[String]
    Assert.assertEquals(expectedSet, computedSet)
  }

  @Test
  def testOfType: Unit = {
    val computedEnv = env.selectType[String]

    Assert.assertEquals(2, computedEnv.size)
    Assert.assertFalse(strKey1 == strKey2)
    Assert.assertTrue(computedEnv.contains(strKey1))
    Assert.assertTrue(computedEnv.contains(strKey2))
  }

  @Test
  def testKeysOfName: Unit = {
    val expectedSet = Set(strKey1, intKey2)
    val computedSet = env.keysOfName(Hello_World)
    Assert.assertEquals(expectedSet, computedSet)
  }
}