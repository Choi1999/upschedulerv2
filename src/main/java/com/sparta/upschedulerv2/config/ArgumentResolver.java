package com.sparta.upschedulerv2.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.format.number.money.CurrencyUnitFormatter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class ArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;

    public  ArgumentResolver(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter){
        return parameter.getParameterAnnotation(CurrentUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mvContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request != null ){
            String userEmail = (String) request.getAttribute("userEmail");
            return userEmail;
        }
        return null;
    }

}
