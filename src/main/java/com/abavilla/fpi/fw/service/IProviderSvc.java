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
