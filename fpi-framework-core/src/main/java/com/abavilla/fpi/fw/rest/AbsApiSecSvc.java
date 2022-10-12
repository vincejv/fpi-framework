package com.abavilla.fpi.fw.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public abstract class AbsApiSecSvc<Api extends IApi,
  ApiKeys extends IApiKeyConfig> extends AbsApiSvc<Api> {

  @Inject
  protected ApiKeys keys;

}
