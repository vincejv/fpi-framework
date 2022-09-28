package com.abavilla.fpi.fw.mapper;

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
    return new ObjectId(id);
  }

  /**
   * Converts mongo db {@link ObjectId} to hex encoded string for entity.
   *
   * @param id {@link ObjectId} object
   * @return Hex encoded id
   */
  default String mongoIdToStr(ObjectId id) {
    return id.toHexString();
  }
}
