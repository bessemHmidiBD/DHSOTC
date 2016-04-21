package com.adoa.azportal.application.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@InterceptorBinding
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface Transactionnal {

}
