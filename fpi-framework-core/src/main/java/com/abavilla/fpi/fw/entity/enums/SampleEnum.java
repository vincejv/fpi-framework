package com.abavilla.fpi.fw.entity.enums;

import static com.abavilla.fpi.fw.util.FWConst.UNKNOWN_PREFIX;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Template base {@link Enum} for creating enums from, since {@link Enum} do not
 * support inheritance.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Getter
@AllArgsConstructor
@RegisterForReflection
public enum SampleEnum implements IBaseEnum {

  /**
   * Default enum with unknown value
   */
  UNKNOWN(-1, UNKNOWN_PREFIX);

  /**
   * Ordinal id to enum mapping
   */
  private static final Map<Integer, SampleEnum> ENUM_MAP = new HashMap<>();

  static {
    for(SampleEnum w : EnumSet.allOf(SampleEnum.class))
      ENUM_MAP.put(w.getId(), w);
  }

  /**
   * The enum ordinal id
   */
  private final int id;

  /**
   * The enum value
   */
  private String value;

  /**
   * Creates an enum based from given string value
   *
   * @param value the string value
   * @return the created enum
   */
  @JsonCreator
  public static SampleEnum fromValue(String value) {
    if (StringUtils.isBlank(value)) {
      return null;
    } else {
      return ENUM_MAP.values().stream().filter(enumItem -> StringUtils.equalsIgnoreCase(value, enumItem.getValue())).findAny()
          .orElseGet(() -> {
            var unknown = UNKNOWN;
            String enumValue = value;
            if (StringUtils.startsWithIgnoreCase(enumValue, UNKNOWN_PREFIX)) {
              enumValue = StringUtils.removeStart(enumValue, UNKNOWN_PREFIX);
            }
            unknown.value = UNKNOWN_PREFIX + enumValue;
            return unknown;
          });
    }
  }

  /**
   * Creates an enum based from given an ordinal id
   *
   * @param id the ordinal id
   * @return the created enum
   */
  public static SampleEnum fromId(int id) {
    return ENUM_MAP.values().stream().filter(enumItem -> id == enumItem.getId()).findAny()
        .orElseGet(() -> {
          var unknown = UNKNOWN;
          unknown.value = UNKNOWN_PREFIX + id;
          return unknown;
        });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @JsonValue
  public String toString() {
    return value;
  }

}
