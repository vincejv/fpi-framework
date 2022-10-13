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

import java.lang.reflect.Method;

import com.abavilla.fpi.fw.entity.enums.IBaseEnum;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * Generic codec for encoding Enum to MongoDB Document
 * @param <E> Enum Type
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@NoArgsConstructor
public abstract class AbsEnumCodec<E extends IBaseEnum> implements Codec<E> {

  /**
   * Document key name for the value node
   */
  public static final String VALUE_KEY_NODE_NAME = "value";

  /**
   * Document key name for the enum id
   */
  public static final String ORD_KEY_NODE_NAME = "ord";

  /**
   * Encodes the given {@link E} Enum
   *
   * @param writer the BSON writer to encode into
   * @param value the value to encode
   * @param encoderContext the encoder context
   */
  @SneakyThrows
  @Override
  public final void encode(final BsonWriter writer, final E value, final EncoderContext encoderContext) {
    if (value != null) {
      Method getId = getEncoderClass().getDeclaredMethod("getId");
      writer.writeStartDocument();
      writer.writeString(VALUE_KEY_NODE_NAME, value.toString());
      writer.writeInt32(ORD_KEY_NODE_NAME, (Integer) getId.invoke(value));
      writer.writeEndDocument();
    }
  }

  /**
   * Decodes the enum from the mongo db document
   *
   * @param reader         the BSON reader
   * @param decoderContext the decoder context
   * @return an instance of the type parameter {@code T}.
   */
  @SneakyThrows
  @Override
  @SuppressWarnings("unchecked")
  public final E decode(final BsonReader reader, final DecoderContext decoderContext) {
    reader.readStartDocument();
    String value = StringUtils.EMPTY;
    int ord = -1;
    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      // decode only value type, ignore ord
      String key = reader.readName();
      if (StringUtils.equals(key, VALUE_KEY_NODE_NAME)) {
        value = reader.readString();
      } else if (StringUtils.equals(key, ORD_KEY_NODE_NAME)) {
        ord = reader.readInt32();
      } else {
        reader.skipValue();
      }
    }
    reader.readEndDocument();

    Method fromValue = getEncoderClass().getDeclaredMethod("fromValue", String.class);
    E decodedEnum = (E) fromValue.invoke(null, value);
    if (ord == -1) { // if enum.ord is -1, it is 'UNKNOWN'
      // if unknown, retain the enum.value content when encoding
      Method setValue = getEncoderClass().getDeclaredMethod("setValue", String.class);
      setValue.invoke(decodedEnum, value);
    }
    return decodedEnum;
  }
  
}
