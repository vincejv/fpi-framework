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

package com.abavilla.fpi.fw.codec;

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
    int ord = Integer.MIN_VALUE;
    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      // decode only value type, ignore ord
      String key = reader.readName();
      if (StringUtils.equals(key, VALUE_KEY_NODE_NAME)) {
        reader.readString();
      } else if (StringUtils.equals(key, ORD_KEY_NODE_NAME)) {
        ord = reader.readInt32();
      } else {
        reader.skipValue();
      }
    }
    reader.readEndDocument();

    Method fromValue = getEncoderClass().getDeclaredMethod("fromId", int.class);
    return (E) fromValue.invoke(null, ord);
  }
  
}
