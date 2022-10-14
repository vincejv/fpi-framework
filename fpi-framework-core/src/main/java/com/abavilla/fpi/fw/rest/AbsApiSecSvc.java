package com.abavilla.fpi.fw.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public abstract class AbsApiSecSvc<A extends IApi,
  K extends IApiKeyConfig> extends AbsApiSvc<A> {

  @Inject
  protected K keys;

}
