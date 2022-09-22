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

import lombok.SneakyThrows;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public abstract class AbsCodec<T extends Enum<T>> implements Codec<T> {
  public AbsCodec() {
  }

  @SneakyThrows
  @Override
  final public void encode(final BsonWriter writer, final T value, final EncoderContext encoderContext) {
    Method getId = getEncoderClass().getDeclaredMethod("getId");
    writer.writeStartDocument();
    writer.writeString("value", value.toString());
    writer.writeInt32("ord", (Integer) getId.invoke(value));
    writer.writeEndDocument();
  }

  @SneakyThrows
  @Override
  final public T decode(final BsonReader reader, final DecoderContext decoderContext) {
    reader.readStartDocument();
    String value = reader.readString("value");
    reader.readEndDocument();
    Method method = getEncoderClass().getDeclaredMethod("fromValue", String.class);
    return (T) method.invoke(null, value);
  }

  public abstract Class<T> getEncoderClass();
}
