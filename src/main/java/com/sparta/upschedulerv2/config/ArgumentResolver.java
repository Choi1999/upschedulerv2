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
            Long userId = Long.valueOf((String) request.getAttribute("userId"));  // ID는 서브젝트에서 추출
            String username = (String) request.getAttribute("username");
            String email = (String) request.getAttribute("userEmail");  // 이메일 클레임
            String role = (String) request.getAttribute("role");  // 역할 클레임
            return new UserInfoDto(userId, username, email, role);
        }
        return null;
    }

}
