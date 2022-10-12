package com.abavilla.fpi.fw.rest;

import javax.enterprise.context.ApplicationScoped;

import com.abavilla.fpi.fw.service.ISvc;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public abstract class AbsApiSvc<Api extends IApi> implements ISvc {

  @RestClient
  Api client;

}
