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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class for Date and Time operations.
 * <ul>
 * <li>{@code *convert} methods are for changing {@link LocalDateTime} to {@link String}</li>
 * <li>{@code *mod} methods are for modifying the {@link LocalDateTime} or {@link String} retaining its current type</li>
 * </ul>
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

  /**
   * Default timestamp format with timezone specified
   */
  public static final String DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSz";

  /**
   * Default time zone for front end purposes, UTC will always be used when saving to database.
   */
  public static final String DEFAULT_TIMEZONE = "Asia/Shanghai";

  /**
   * UTC Zulu Timezone, timezone output format will output 'z'
   */
  public static final ZoneId UTC_ZULU_TIMEZONE = ZoneId.of("UTC");

  /**
   * Get the current date time in string format in UTC timezone
   *
   * @return Date and time in {@link #DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE} format
   */
  public static String nowAsStr() {
    return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE));
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
   * retains the epoch timezone
   *
   * @param epochMs Unix Epoch in milliseconds
   * @return {@link LocalDateTime} object
   */
  public static LocalDateTime fromEpoch(long epochMs) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMs), ZoneOffset.UTC);
  }

  /**
   * Changes a date time string to another date time format.
   *
   * @param dateStr {@link String} Date Time string to convert
   * @param inFormat {@link DateTimeFormatter} date time format of {@code dateStr}
   * @param outFormat {@link DateTimeFormatter} desired format for the output
   * @return {@link String} Date time string outputted in desired format
   */
  public static String modStrDateToFormat(String dateStr, DateTimeFormatter inFormat, DateTimeFormatter outFormat) {
    var ldt = LocalDateTime.parse(dateStr, inFormat);
    var zdt = ZonedDateTime.of(ldt, ZoneOffset.UTC);
    return zdt.format(outFormat);
  }

  /**
   * Parses a date time string that may or may not contain timezone information and retains the date and time values.
   * Uses {@link #DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE} as the output format.
   *
   * @param dateStr Date time string without timezone specified
   * @return {@link LocalDateTime} object
   */
  public static LocalDateTime parseStrDateToLdt(String dateStr) {
    return parseStrDateToLdt(dateStr, DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE);
  }

  /**
   * Parses a date time string that may or may not contain timezone information and retains the date and time values.
   *
   * @param dateStr Date time string without timezone specified
   * @param inFormat Format of {@code dateStr}
   * @return {@link LocalDateTime} object
   */
  public static LocalDateTime parseStrDateToLdt(String dateStr, DateTimeFormatter inFormat) {
    return LocalDateTime.parse(dateStr, inFormat);
  }

  /**
   * Parses a date time string that may or may not contain timezone information and retains the date and time values.
   *
   * @param dateStr Date time string without timezone specified
   * @param inFormat Format of {@code dateStr}
   * @return {@link LocalDateTime} object
   */
  public static LocalDateTime parseStrDateToLdt(String dateStr, String inFormat) {
    return parseStrDateToLdt(dateStr, DateTimeFormatter.ofPattern(inFormat));
  }

  /**
   * Parses a date time string containing a timezone and changes the date and time values based on given {@code outTz}
   * timezone
   *
   * @param dateStr Input date time string
   * @param inFormat Format of {@code dateStr}
   * @param outTz Output timezone
   * @return {@link LocalDateTime} object
   */
  public static LocalDateTime parseStrDateWTzToLdt(String dateStr, DateTimeFormatter inFormat, ZoneId outTz) {
    return ZonedDateTime.parse(dateStr, inFormat).withZoneSameInstant(outTz).toLocalDateTime();
  }

  /**
   * Parses a date time string containing a timezone and changes the date and time values based on given {@code outTz}
   * timezone
   *
   * @param dateStr Input date time string
   * @param inFormat Format of {@code dateStr}
   * @param outTz Output timezone
   * @return {@link LocalDateTime} object
   */
  public static LocalDateTime parseStrDateWTzToLdt(String dateStr, String inFormat, ZoneId outTz) {
    return parseStrDateWTzToLdt(dateStr, DateTimeFormatter.ofPattern(inFormat), outTz);
  }

  /**
   * Changes a date time string to another date time format.
   *
   * @param dateStr {@link String} Date Time string to convert
   * @param inFormat {@link String} date time format of {@code dateStr}
   * @param outFormat {@link String} desired format for the output
   * @return {@link String} Date time string outputted in desired format
   */
  public static String modStrDateToFormat(String dateStr, String inFormat, String outFormat) {
    return modStrDateToFormat(dateStr, DateTimeFormatter.ofPattern(inFormat),
        DateTimeFormatter.ofPattern(outFormat));
  }

  /**
   * Changes the {@link LocalDateTime} to UTC specifying the timezone used by {@code ldt}
   *
   * @param ldt {@link LocalDateTime} to convert
   * @param zoneId Timezone of {@code ldt}
   * @return {@link LocalDateTime} in UTC
   */
  public static LocalDateTime modtLdtToUtc(LocalDateTime ldt, ZoneId zoneId) {
    var zdt = ZonedDateTime.of(ldt, zoneId);
    return zdt.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
  }

  /**
   * Changes the {@link LocalDateTime} assuming that {@code ldt} uses the {@link #DEFAULT_TIMEZONE}, to UTC.
   *
   * @param ldt {@link LocalDateTime} to convert
   * @return {@link LocalDateTime} in UTC
   */
  public static LocalDateTime modLdtToUtc(LocalDateTime ldt) {
    var zdt = ZonedDateTime.of(ldt, ZoneId.of(DEFAULT_TIMEZONE));
    return zdt.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
  }

  /**
   * Converts {@code ldt} to timestamp string, outputs using the format specified in
   * {@link #DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE}
   * and using UTC Timezone
   *
   * @param ldt {@link LocalDateTime} Date and time to convert
   * @param zoneId {@link ZoneId} Time zone of {@code ldt}
   * @return Formatted date time
   */
  public static String convertLdtToUTCStr(LocalDateTime ldt, ZoneId zoneId) {
    return convertLdtToUTCStr(ldt, zoneId, DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE);
  }

  /**
   * Converts {@code ldt} to timestamp string, outputs using the format specified in {@code outFormat}
   * and using UTC Timezone
   *
   * @param ldt {@link LocalDateTime} Date and time to convert
   * @param zoneId {@link ZoneId} Time zone of {@code ldt}
   * @param outFormat {@link String} Date time format used for the output
   * @return Formatted date time
   */
  public static String convertLdtToUTCStr(LocalDateTime ldt, ZoneId zoneId, String outFormat) {
    ZonedDateTime zdtInUtc = ZonedDateTime.of(ldt, zoneId).withZoneSameInstant(ZoneOffset.UTC);
    var outFormatter = DateTimeFormatter.ofPattern(outFormat);
    return zdtInUtc.format(outFormatter);
  }

  /**
   * Converts {@code ldt} to timestamp string, retaining the time and values of {@code ldt}, outputs using
   * the format specified in {@link #DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE}, assumes time zone of {@code ldt} is UTC
   *
   * @param ldt {@link LocalDateTime} Date and time to convert
   * @return Formatted date time
   */
  public static String convertLdtToStr(LocalDateTime ldt) {
    return convertLdtToStr(ldt, ZoneOffset.UTC, ZoneOffset.UTC, DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE);
  }

  /**
   * Converts {@code ldt} to timestamp string, retaining the time, date and zone values of {@code ldt}, outputs using
   * the format specified in {@link #DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE}
   *
   * @param ldt {@link LocalDateTime} Date and time to convert
   * @param inTz {@link ZoneId} Timezone of {@code ldt}
   * @return Formatted date time
   */
  public static String convertLdtToStr(LocalDateTime ldt, ZoneId inTz) {
    return convertLdtToStr(ldt, inTz, inTz, DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE);
  }

  /**
   * Converts {@code ldt} to timestamp string, and places a specific timezone on the {@code ldt}, outputs using
   * the format specified in {@link #DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE}
   *
   * @param ldt {@link LocalDateTime} Date and time to convert
   * @param inTz {@link ZoneId} Timezone of {@code ldt}
   * @return Formatted date time
   */
  public static String convertLdtToStr(LocalDateTime ldt, ZoneId inTz, ZoneId outTz) {
    return convertLdtToStr(ldt, inTz, outTz, DEFAULT_TIMESTAMP_FORMAT_WITH_TIMEZONE);
  }

  /**
   * Converts {@code ldt} to timestamp string, changing the time and date values of
   * {@code ldt}, to the given timezone by {@code outTz}.
   *
   * @param ldt {@link LocalDateTime} Date and time to convert
   * @param inTz {@link ZoneId} Time zone of {@code ldt}
   * @param outTz {@link ZoneId} Time zone of output
   * @param outFormat {@link String} Date time format used for the output
   * @return Formatted date time
   */
  public static String convertLdtToStr(LocalDateTime ldt, ZoneId inTz, ZoneId outTz, String outFormat) {
    var outFormatter = DateTimeFormatter.ofPattern(outFormat);
    return ZonedDateTime.of(ldt, inTz).withZoneSameInstant(outTz).format(outFormatter);
  }

  /**
   * Converts {@code ldt} to timestamp string, retaining the time and date values of {@code ldt}
   *
   * @param ldt {@link LocalDateTime} Date and time to convert
   * @param inTz {@link ZoneId} Time zone of {@code ldt} and output timestamp
   * @param outFormat {@link String} Date time format used for the output
   * @return Formatted date time
   */
  public static String convertLdtToStr(LocalDateTime ldt, ZoneId inTz, String outFormat) {
    return convertLdtToStr(ldt, inTz, inTz, outFormat);
  }

}
