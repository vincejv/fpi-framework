package com.abavilla.fpi.fw.rest;

import com.abavilla.fpi.fw.exceptions.handler.ApiRepoExHandler;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

@RegisterProvider(value = ApiRepoExHandler.class)
public interface IApi {
}
