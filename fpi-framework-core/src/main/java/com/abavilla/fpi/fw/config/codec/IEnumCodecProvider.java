/******************************************************************************
 * FPI Application - Abavilla                                                 *
 * Copyright (C) 2022  Vince Jerald Villamora                                 *
 *                                                                            *
 * This program is free software: you can redistribute it and/or modify       *
 * it under the terms of the GNU General Public License as published by       *
 * the Free Software Foundation, either version 3 of the License, or          *
 * (at your option) any later version.                                        *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU General Public License          *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.     *
 ******************************************************************************/

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
