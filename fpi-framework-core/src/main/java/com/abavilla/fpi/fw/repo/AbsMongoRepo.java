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

package com.abavilla.fpi.fw.repo;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.abavilla.fpi.fw.entity.AbsItem;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheQuery;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

@ApplicationScoped
public abstract class AbsMongoRepo<I extends AbsItem> implements IMongoRepo<I> {

  /**
   * Retrieves items from db by page
   *
   * @param index Page number (zero based)
   * @param itemPerPage Items per page
   * @return Paged query
   */
  public ReactivePanacheQuery<I> byPage(int index, int itemPerPage) {
    return findAll(Sort.by("dateUpdated")).page(index, itemPerPage);
  }

  public Uni<Optional<I>> byId(String id) {
    return findByIdOptional(new ObjectId(id));
  }
}
