package com.abavilla.fpi.fw.mapper;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

/**
 * Common mapper contains the commonly used data types across all mapstruct mappers.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface IMapper {

  /**
   * Converts hex encoded string to {@link ObjectId} for dto.
   *
   * @param id Hex encoded {@link ObjectId}
   * @return {@link ObjectId} object
   */
  default ObjectId strToMongoId(String id) {
    if (StringUtils.isNotBlank(id) &&
        ObjectId.isValid(id)) {
      return new ObjectId(id);
    } else {
      return null;
    }
  }

  /**
   * Converts mongo db {@link ObjectId} to hex encoded string for entity.
   *
   * @param id {@link ObjectId} object
   * @return Hex encoded id
   */
  default String mongoIdToStr(ObjectId id) {
    if (id != null) {
      return id.toHexString();
    } else {
      return null;
    }
  }
}
