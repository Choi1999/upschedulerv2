package com.sparta.upschedulerv2.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //매서드의 매개변수에 적용될수 있게한다.
@Retention(RetentionPolicy.RUNTIME)//런타임 동안에도 유지되고 값을 참조할 수 있게한다.
public @interface CurrentUser {
}