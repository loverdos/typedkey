package com.ckkloverdos.env

import com.ckkloverdos.key.{TypedKeyWithDefault, TypedKeyOnly}
import java.util.Date

/**
 *
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
sealed class EnvHelpers {
  def newTypedKeyOnly[A](name: String, typ: Class[A]): TypedKeyOnly[A] = {
    new TypedKeyOnly[A](name)(Manifest.classType[A](typ))
  }

  def newTypedKeyWithDefault[A](name: String, value: A, typ: Class[A]): TypedKeyWithDefault[A] = {
    new TypedKeyWithDefault[A](name, value)((Manifest.classType[A](typ)))
  }

  def ByteKey(name: String): TypedKeyOnly[Byte] =
    new TypedKeyOnly(name)

  def ByteKey(name: String, value: Byte): TypedKeyWithDefault[Byte] =
    new TypedKeyWithDefault(name, value)

  def BooleanKey(name: String): TypedKeyOnly[Boolean] =
    new TypedKeyOnly(name)

  def BooleanKey(name: String, value: Boolean): TypedKeyWithDefault[Boolean] =
    new TypedKeyWithDefault(name, value)

  def ShortKey(name: String): TypedKeyOnly[Short] =
    new TypedKeyOnly(name)

  def ShortKey(name: String, value: Short): TypedKeyWithDefault[Short] =
    new TypedKeyWithDefault(name, value)

  def CharKey(name: String): TypedKeyOnly[Char] =
    new TypedKeyOnly(name)

  def CharKey(name: String, value: Char): TypedKeyWithDefault[Char] =
    new TypedKeyWithDefault(name, value)

  def IntKey(name: String): TypedKeyOnly[Int] =
    new TypedKeyOnly(name)

  def IntKey(name: String, value: Int): TypedKeyWithDefault[Int] =
    new TypedKeyWithDefault(name, value)

  def LongKey(name: String): TypedKeyOnly[Long] =
    new TypedKeyOnly(name)

  def LongKey(name: String, value: Long): TypedKeyWithDefault[Long] =
    new TypedKeyWithDefault(name, value)

  def FloatKey(name: String): TypedKeyOnly[Float] =
    new TypedKeyOnly(name)

  def FloatKey(name: String, value: Float): TypedKeyWithDefault[Float] =
    new TypedKeyWithDefault(name, value)

  def DoubleKey(name: String): TypedKeyOnly[Double] =
    new TypedKeyOnly(name)

  def DoubleKey(name: String, value: Double): TypedKeyWithDefault[Double] =
    new TypedKeyWithDefault(name, value)

  def StringKey(name: String): TypedKeyOnly[String] =
    new TypedKeyOnly(name)

  def StringKey(name: String, value: String): TypedKeyWithDefault[String] =
    new TypedKeyWithDefault(name, value)

  def DateKey(name: String): TypedKeyOnly[Date] =
    new TypedKeyOnly(name)

  def DateKey(name: String, value: Date): TypedKeyWithDefault[Date] =
    new TypedKeyWithDefault(name, value)

}

object EnvHelpers extends EnvHelpers
