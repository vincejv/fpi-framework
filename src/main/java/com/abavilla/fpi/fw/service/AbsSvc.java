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

package com.abavilla.fpi.fw.service;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.entity.AbsItem;
import com.abavilla.fpi.fw.repo.IMongoRepo;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

public abstract class AbsSvc<D extends IDto, I extends AbsItem> implements ISvc<D, I> {

  /**
   * The repository to manage {@link I}
   */
  @Inject
  protected IMongoRepo<I> repo;

  /**
   * Retrieves an item by id.
   *
   * @param id {@link String} Id
   * @return {@link I} Item retrieved
   */
  public Uni<D> get(String id) {
    Uni<Optional<I>> byId = repo.byId(id);
    return byId.chain(opt -> {
      if (opt.isPresent()) {
        return Uni.createFrom()
            .item(this.mapToDto(opt.get()));
      }
      return Uni.createFrom()
          .failure(new NotFoundException("Cannot find " + id));
    });
  }

  /**
   * Retrieves items by page
   *
   * @param ndx Page number (zero based)
   * @param size Items per page
   * @return Paged query
   */
  public Multi<D> getByPage(int ndx, int size) {
    return repo.byPage(ndx, size).stream().map(this::mapToDto);
  }

  /**
   * Updates an item to database.
   *
   * @param id {@link String} Id
   * @param item {@link D} Item to save
   * @return {@link I} Item after saving to db
   */
  public Uni<D> update(String id, D item) {
    Uni<Optional<I>> byId = repo.byId(id);
    return byId.chain(opt -> {
      if (opt.isPresent()) {
        var updatedItem = mapToEntity(item);
        updatedItem.setId(new ObjectId(id));
        return repo.persistOrUpdate(updatedItem).map(this::mapToDto);
      }
      return Uni.createFrom().failure(new NotFoundException("Cannot find " + id));
    });
  }

  /**
   * Saves a new item to database.
   *
   * @param item {@link D} Item to save
   * @return {@link I} Item after saving to db
   */
  public Uni<D> save(D item) {
    Uni<I> persistedItem = repo.persist(mapToEntity(item));
    return persistedItem.map(this::mapToDto);
  }

  /**
   * Deletes an item from the database given by id.
   *
   * @param id Database id
   * @return {@link D}
   */
  public Uni<D> delete(String id) {
    Uni<Optional<I>> byId = repo.byId(id);
    return byId.chain(opt -> {
      if (opt.isPresent()) {
        opt.get().setIsArchived(true);
        return repo.persistOrUpdate(opt.get()).map(this::mapToDto);
      }
      return Uni.createFrom().failure(new NotFoundException("Cannot find " + id));
    });
  }

  public Multi<D> list() {
    return repo.streamAll().map(this::mapToDto);
  }

  public abstract D mapToDto(I entity);

  public abstract I mapToEntity(D dto);
}
