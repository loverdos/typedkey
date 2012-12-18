package com.ckkloverdos.env

import com.ckkloverdos.key.{TypedKeyWithDefault, TypedKeyOnly}

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
}

object EnvHelpers extends EnvHelpers
