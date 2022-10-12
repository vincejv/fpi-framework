package com.abavilla.fpi.fw.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public abstract class AbsApiSecSvc<Api extends IApi,
  ApiKeys extends IApiKeyConfig> extends AbsApiSvc<Api> {

  @RestClient
  Api client;

  @Inject
  ApiKeys keys;

}
