package com.abavilla.fpi.fw.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.abavilla.fpi.fw.dto.IDto;
import com.abavilla.fpi.fw.entity.AbsItem;
import com.abavilla.fpi.fw.repo.IMongoRepo;

/**
 * Base service layer for creating services with access to a specific repository.
 *
 * @param <Dto> DTO Type
 * @param <Item> Entity Type
 * @param <Repo> Repository type
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@ApplicationScoped
public abstract class AbsRepoSvc<Dto extends IDto, Item extends AbsItem, Repo extends IMongoRepo<Item>>
    extends AbsSvc<Dto, Item> implements ISvc {

  /**
   * The repository to manage {@link Item}
   */
  @Inject
  protected Repo repo;

}
