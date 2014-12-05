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

package typedkey.impl;

import typedkey.KeyType;
import typedkey.TypeProvider;

/**
 * @author Christos KK Loverdos <loverdos@gmail.com>
 */
public final class JavaSimpleTypeProvider implements TypeProvider {
    private static final JavaSimpleTypeProvider instance = new JavaSimpleTypeProvider();

    private JavaSimpleTypeProvider() {}

    public static JavaSimpleTypeProvider getInstance() {
        return instance;
    }

    public <T> KeyType keyTypeOf(Class<T> clazz) {
        final String canonicalName = clazz.getCanonicalName();
        final String typeName = canonicalName != null ? canonicalName : clazz.getName();

        return new KeyType(clazz, typeName);
    }
}
