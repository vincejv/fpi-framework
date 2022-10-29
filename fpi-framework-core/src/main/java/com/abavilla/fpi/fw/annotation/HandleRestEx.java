package com.abavilla.fpi.fw.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abavilla.fpi.fw.exceptions.handler.ApiRepoExHandler;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

/**
 * Inherited annotation to handle rest client exceptions with {@link ApiRepoExHandler}
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@RegisterProvider(value = ApiRepoExHandler.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandleRestEx {
}
