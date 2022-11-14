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

package com.abavilla.fpi.fw.service;

import com.abavilla.fpi.fw.engine.IEngine;
import io.quarkus.arc.All;
import io.quarkus.arc.Priority;

/**
 * Interface for upstream provider services, to be used together with {@link IEngine}, implementing classes should
 * define may define a {@code callSvc} method for calling the provider and exeucute actions.
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public interface IProviderSvc extends ISvc {

  /**
   * Initializes the service
   */
  void init();

  /**
   * Service priority, the lower the number, the more it is prioritized when multiple upstream services are available.
   * @deprecated Use {@link Priority} and {@link All} to define the list of providers
   * available for an engine
   *
   * @return the priority number
   */
  @Deprecated(forRemoval = true)
  long getPriority();

  /**
   * Service provider name.
   * @return the provider name
   */
  String getProviderName();

  /**
   * Flag to check if service is currently available.
   *
   * @return the service availability
   */
  default boolean isEnabled() {
    return true;
  }
}
