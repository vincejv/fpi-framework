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
package com.abavilla.fpi.fw.spi;

import java.beans.Introspector;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

import org.mapstruct.ap.spi.AccessorNamingStrategy;
import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;
import org.mapstruct.ap.spi.MapStructProcessingEnvironment;

/**
 * A custom {@link AccessorNamingStrategy} recognizing getters in the form of {@code property()} and setters in the
 * form of {@code withProperty(value)}.
 *
 * @author Sjaak Derksen
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
public class FluentAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

  private static final String PACKAGE_APPLIED = "com.pengrad.telegrambot.model";

  @Override
  public void init(MapStructProcessingEnvironment processingEnvironment) {
    super.init( processingEnvironment );
    elementUtils = processingEnvironment.getElementUtils();
  }

  @Override
  public boolean isGetterMethod(ExecutableElement method) {
    if ( isCustomPackage( method ) ) {
      String methodName = method.getSimpleName().toString();
      return !methodName.startsWith( "with" ) && method.getReturnType().getKind() != TypeKind.VOID;
    }
    return super.isGetterMethod( method );
  }

  @Override
  public boolean isSetterMethod(ExecutableElement method) {
    if ( isCustomPackage( method ) ) {
      String methodName = method.getSimpleName().toString();
      return methodName.startsWith( "with" ) && methodName.length() > 4;
    }
    return super.isSetterMethod( method );
  }

  @Override
  public String getPropertyName(ExecutableElement getterOrSetterMethod) {
    if ( isCustomPackage( getterOrSetterMethod ) ) {
      String methodName = getterOrSetterMethod.getSimpleName().toString();
      return Introspector.decapitalize( methodName.startsWith( "with" ) ? methodName.substring(  4 ) : methodName );
    }
    return super.getPropertyName( getterOrSetterMethod );
  }

  private boolean isCustomPackage(ExecutableElement method) {
    return getPackage( method ).contains( "." + PACKAGE_APPLIED + "." ) // subpackage
      || getPackage( method ).endsWith( "." + PACKAGE_APPLIED ); // current package
  }

  private String getPackage(ExecutableElement element) {
    return elementUtils.getPackageOf( element ).getQualifiedName().toString();
  }

}
