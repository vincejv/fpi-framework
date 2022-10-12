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

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

import com.abavilla.fpi.fw.entity.AbsItem;
import com.abavilla.fpi.fw.exceptions.OptimisticLockEx;
import com.mongodb.MongoException;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public abstract class AbsMongoRepo<I extends AbsItem> implements IMongoRepo<I> {

  @Override
  public Uni<I> update(I toSave) {
    if (toSave.getId() != null) {
      return findByIdOptional(toSave.getId()).chain(inDb -> {
        if (inDb.isPresent()) {
          // update with optimistic locking
          if (Objects.equals(toSave.getVersion(), inDb.get().getVersion())) {
            toSave.setVersion(toSave.getVersion() == null ?
              BigInteger.ONE : toSave.getVersion().add(BigInteger.ONE));
            return IMongoRepo.super.update(toSave);
          }
          throw new OptimisticLockEx(String.format("Found version %s, while item in memory is %s while saving %s",
            inDb.get().getVersion().toString(), toSave.getVersion(), toSave.getId()));
        } else {
          throw new MongoException("Cannot perform update on non-existing entity " + toSave);
        }
      });
    }
    throw new MongoException("Cannot perform update on null id, entity is " + toSave);
  }

  @Override
  public Uni<I> persistOrUpdate(I toSave) {
    if (toSave.getId() != null) {
      return findByIdOptional(toSave.getId()).chain(inDb -> {
          if (inDb.isPresent()) {
            // update with optimistic locking
            if (Objects.equals(toSave.getVersion(), inDb.get().getVersion())) {
              toSave.setVersion(toSave.getVersion() == null ?
                BigInteger.ONE : toSave.getVersion().add(BigInteger.ONE));
              return IMongoRepo.super.update(toSave);
            }
            throw new OptimisticLockEx(String.format("Found version %s, while item in memory is %s while saving %s",
              inDb.get().getVersion().toString(), toSave.getVersion(), toSave.getId()));
          } else {
            return IMongoRepo.super.persist(toSave);
          }
        }
      );
    } else {
      return IMongoRepo.super.persist(toSave);
    }
  }

  @Override
  public Uni<Void> update(Iterable<I> is) {
    for (I i : is) {
      this.update(i).replaceWithVoid().subscribe().with(ignored->{});
    }
    return Uni.createFrom().voidItem();
  }

  @Override
  public Uni<Void> update(Stream<I> entities) {
    entities.forEach(i -> this.update(i).replaceWithVoid().subscribe().with(ignored->{}));
    return Uni.createFrom().voidItem();
  }

  @SafeVarargs
  @Override
  public final Uni<Void> update(I firstEntity, I... is) {
    this.update(firstEntity).replaceWithVoid().subscribe().with(ignored->{});
    this.update(List.of(is));
    return Uni.createFrom().voidItem();
  }

  @Override
  public Uni<Void> persistOrUpdate(Iterable<I> is) {
    for (I i : is) {
      this.persistOrUpdate(i).replaceWithVoid().subscribe().with(ignored->{});
    }
    return Uni.createFrom().voidItem();
  }

  @Override
  public Uni<Void> persistOrUpdate(Stream<I> entities) {
    entities.forEach(i -> this.persistOrUpdate(i).replaceWithVoid().subscribe().with(ignored->{}));
    return Uni.createFrom().voidItem();
  }

  @SafeVarargs
  @Override
  public final Uni<Void> persistOrUpdate(I firstEntity, I... is) {
    this.persistOrUpdate(firstEntity).replaceWithVoid().subscribe().with(ignored->{});
    this.persistOrUpdate(List.of(is));
    return Uni.createFrom().voidItem();
  }
}
