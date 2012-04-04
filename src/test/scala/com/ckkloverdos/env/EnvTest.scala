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
import com.ckkloverdos.maybe.Just
import com.ckkloverdos.key.{DoubleKey, IntKey, StringKey}

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
class EnvTest {
  val KeyName_1 = "it's.key"
  val KeyName_2 = "just.another.key"
  val KeyName_3 = "day.in.paradise.key"

  val key1_str = StringKey(KeyName_1)
  val val1_str = "Value 1"
  val key2_int = IntKey(KeyName_2)
  val val2_int = 12
  val key3_str = StringKey(KeyName_2)
  val val3_str = "Value 2"
  val key4_int = IntKey(KeyName_1)
  val val4_int = 500
  val key5_double = DoubleKey(KeyName_3)
  val val5_double = 1.0

  val env = Env() +
    (key1_str, val1_str) +
    (key2_int, val2_int) +
    (key3_str, val3_str) +
    (key4_int, val4_int) +
    (key5_double, val5_double)

  @Test
  def testStringKey: Unit = {
    val strValue = env.get(key1_str)
    Assert.assertEquals(Just(val1_str), strValue)
  }

  @Test
  def testIntKey: Unit = {
    val intValue = env.get(key2_int)
    Assert.assertEquals(Just(val2_int), intValue)
  }

  @Test
  def testDoubleKey: Unit = {
    val doubleValue = env.get(key5_double)
    Assert.assertEquals(Just(val5_double), doubleValue)
  }

  @Test
  def testKeysOfType: Unit = {
    val expectedSet = Set(key1_str, key3_str)
    val computedSet = env.keysOfType[String]
    Assert.assertEquals(expectedSet, computedSet)
  }

  @Test
  def testOfType: Unit = {
    val computedEnv = env.selectType[String]

    Assert.assertEquals(2, computedEnv.size)
    Assert.assertFalse(key1_str == key3_str)
    Assert.assertTrue(computedEnv.contains(key1_str))
    Assert.assertTrue(computedEnv.contains(key3_str))
  }

  @Test
  def testKeysOfName: Unit = {
    val expectedSet = Set(key1_str, key4_int)
    val computedSet = env.keysOfName(KeyName_1)
    Assert.assertEquals(expectedSet, computedSet)
  }
}