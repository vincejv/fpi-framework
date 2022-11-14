/*************************************************************************
 * Copyright (C) 2022 Vince Jerald Villamora @ https://vincejv.com       *
 *                                                                       *
 * Licensed under the Apache License, Version 2.0 (the "License");       *
 * you may not use this file except in compliance with the License.      *
 * You may obtain a copy of the License at                               *
 *                                                                       *
 *   http://www.apache.org/licenses/LICENSE-2.0                          *
 *                                                                       *
 * Unless required by applicable law or agreed to in writing, software   *
 * distributed under the License is distributed on an "AS IS" BASIS,     *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or       *
 * implied. See the License for the specific language governing          *
 * permissions and limitations under the License.                        *
 *************************************************************************/

package com.abavilla.fpi.fw.config.codec;

import com.abavilla.fpi.fw.config.codec.impl.SampleEnumCodec;
import com.abavilla.fpi.fw.entity.enums.IBaseEnum;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * Codec provider for mongo db driver
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface IEnumCodecProvider extends CodecProvider {

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  default <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
    if (clazz == IBaseEnum.class) {
      return (Codec<T>) new SampleEnumCodec();
    }
    return getCodecProvider(clazz);
    //return null; // Don't throw here, this tells Mongo this provider doesn't provide a decoder for the requested clazz
  }

  /**
   * Gets the Class to Codec mapping
   *
   * @param <T> Enum type to decode
   * @param tClass Class to decode
   * @return the codec
   */
  <T> Codec<T> getCodecProvider(Class<T> tClass);
}
