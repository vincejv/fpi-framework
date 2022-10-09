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

package com.abavilla.fpi.fw.dto.impl;

import com.abavilla.fpi.fw.controller.AbsBaseResource;
import com.abavilla.fpi.fw.dto.AbsDto;
import com.abavilla.fpi.fw.service.AbsSvc;

/**
 * Data transfer object for transporting {@code null} or empty items/response, can be used as placeholders for
 * {@link AbsBaseResource} or {@link AbsSvc}
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public class NullDto extends AbsDto {
}
