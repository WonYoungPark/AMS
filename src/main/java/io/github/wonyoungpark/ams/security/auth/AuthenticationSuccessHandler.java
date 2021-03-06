package io.github.wonyoungpark.ams.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wonyoungpark.ams.domain.User;
import io.github.wonyoungpark.ams.domain.UserTokenState;
import io.github.wonyoungpark.ams.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 로그인 인증 성공 시
 * Created by one.0 on 2017. 8. 8..
 */
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Value("${jwt.expires_in}")
	private int EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${app.user_cookie}")
	private String USER_COOKIE;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException {
		clearAuthenticationAttributes(request);
		User user = (User)authentication.getPrincipal();

		// jws( JSON Web Signature)
		String jws = tokenUtil.generateToken(user.getUsername());

		// Create token auth Cookie
		Cookie authCookie = new Cookie(TOKEN_COOKIE, jws);
		authCookie.setPath("/");
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(EXPIRES_IN);
		// Create flag Cookie
		Cookie userCookie = new Cookie(USER_COOKIE, user.getFirstName());
		userCookie.setPath("/");
		userCookie.setMaxAge(EXPIRES_IN);
		// Add cookie to response
		response.addCookie(authCookie);
		response.addCookie(userCookie);
		// JWT is also in the response
		UserTokenState userTokenState = new UserTokenState(jws, EXPIRES_IN);
		String jwtResponse = objectMapper.writeValueAsString(userTokenState);
		response.setContentType("application/json");
	    response.getWriter().write(jwtResponse);
	}
}
