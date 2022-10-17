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
