package com.abavilla.fpi.fw.dto.impl;

import java.util.List;

import com.abavilla.fpi.fw.dto.AbsDto;
import com.abavilla.fpi.fw.dto.IDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Data transfer object containing a page of {@link AbsDto}.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageDto<DTO extends IDto> extends AbsDto {

  /**
   * Content of the page containing list of {@link DTO}
   */
  private final List<DTO> content;

  /**
   * Flag to indicate if page has a next page, {@code true} if has a next page,
   * otherwise {@code false}
   */
  private final Boolean hasNextPage;

  /**
   * Current page number
   */
  private final Integer pageNumber;

  /**
   * Number of items in the page
   */
  private final Integer count;

  /**
   * Maximum number of items in a page
   */
  private final Integer requestedCount;

  /**
   * Creates a new page containing list of {@link DTO}
   *
   * @param content Page content
   * @param hasNextPage Flag to indicate if it has a next page
   * @param pageNumber Current page number
   * @param count Number of items in page
   * @return {@link PageDto}
   */
  public static <DTO extends IDto> PageDto<DTO> of(List<DTO> content, Boolean hasNextPage, Integer pageNumber, Integer count, Integer maxCount) {
    return new PageDto<>(content, hasNextPage, pageNumber, count, maxCount);
  }

}
