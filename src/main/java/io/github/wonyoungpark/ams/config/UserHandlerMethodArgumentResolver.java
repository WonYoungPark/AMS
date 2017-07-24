//package io.github.wonyoungpark.ams.config;
//
//import io.github.wonyoungpark.ams.domain.User;
//import org.springframework.core.MethodParameter;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
///**
// * Created by one0 on 2017. 7. 20..
// */
//public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
//    @Override
//	public boolean supportsParameter(MethodParameter parameter) {
//		return User.class.isAssignableFrom(parameter.getParameterType());
//	}
//
//	@Override
//	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
//			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//		Authentication auth = (Authentication) webRequest.getUserPrincipal();
//        return auth != null && auth.getPrincipal() instanceof User ? auth.getPrincipal() : null;
//	}
//}
