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

package com.abavilla.fpi.fw.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for Date and Time operations.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public abstract class DateUtil {

  /**
   * Default timestamp format
   */
  public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSz";

  /**
   * Default time zone for front end purposes, UTC will always be used when saving to database.
   */
  public static final String DEFAULT_TIMEZONE = "Asia/Shanghai";

  /**
   * Get the current date time in string format in UTC timezone
   *
   * @return Date and time in {@link #DEFAULT_TIMESTAMP_FORMAT} format
   */
  public static String nowAsStr() {
    return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(DEFAULT_TIMESTAMP_FORMAT));
  }

  /**
   * Get the current date time in UTC timezone
   *
   * @return {@link LocalDateTime} Object.
   */
  public static LocalDateTime now() {
    return LocalDateTime.now(ZoneOffset.UTC);
  }

  /**
   * Converts UNIX Epoch millisecond time to {@link LocalDateTime} object
   * @param epoch Unix Epoch millisecond
   *
   * @return {@link LocalDateTime} object
   */
  public static LocalDateTime fromEpoch(long epoch) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch),
        ZoneOffset.UTC);
  }

  /**
   * Converts a date string to a specific date time format.
   *
   * @param dateStr {@link String} Date Time string to convert
   * @param inFormat {@link DateTimeFormatter} date time format of {@code dateStr}
   * @param outFormat {@link DateTimeFormatter} desired format for the output
   * @return {@link String} Date time string outputted in desired format
   */
  public static String convertStrDateToFormat(String dateStr, DateTimeFormatter inFormat, DateTimeFormatter outFormat) {
    var ldt = LocalDateTime.parse(dateStr, inFormat);
    var zdt = ZonedDateTime.of(ldt, ZoneOffset.UTC);
    return zdt.format(outFormat);
  }

  /**
   * Converts the {@link LocalDateTime} to UTC specifying the timezone used by the date time object.
   * @param ldt {@link LocalDateTime} to convert
   * @param zoneId Timezone of {@link LocalDateTime}
   *
   * @return {@link LocalDateTime} in UTC
   */
  public static LocalDateTime convertLdtToUtc(LocalDateTime ldt, ZoneId zoneId) {
    var zdt = ZonedDateTime.of(ldt, zoneId);
    return zdt.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
  }

  /**
   * Converts the {@link LocalDateTime} assuming that it uses the {@link #DEFAULT_TIMEZONE}, to UTC.
   * @param ldt {@link LocalDateTime} to convert
   *
   * @return {@link LocalDateTime} in UTC
   */
  public static LocalDateTime convertLdtUTC8ToUtc(LocalDateTime ldt) {
    var zdt = ZonedDateTime.of(ldt, ZoneId.of(DEFAULT_TIMEZONE));
    return zdt.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
  }

}
