package com.abavilla.fpi.fw.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.entity.AbsItem;
import com.abavilla.fpi.fw.repo.IMongoRepo;

/**
 * Base service layer for creating services with access to a specific repository.
 *
 * @param <D> DTO Type
 * @param <E> Entity Type
 * @param <R> Repository type
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@ApplicationScoped
public abstract class AbsRepoSvc<D extends IDto, E extends AbsItem, R extends IMongoRepo<E>>
    extends AbsSvc<D, E> implements ISvc {

  /**
   * The repository to manage {@link E}
   */
  @SuppressWarnings("java:S2387")
  @Inject
  protected R repo;

}
