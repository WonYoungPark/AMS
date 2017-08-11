package io.github.wonyoungpark.ams.security;

import io.github.wonyoungpark.ams.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


/**
 * JWT(Json Web Token)는 크게 세가지 부분(헤더header, 페이로드payload, 시그니쳐signature)으로 나누어 져 있다.
 * Created by one.0 on 2017. 8. 9..
 */
@Component
public class TokenUtil {

    @Value("${app.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    private String SECRET; // 토큰

    @Value("${jwt.expires_in}")
    private int EXPIRES_IN;

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Value("${jwt.cookie}")
    private String AUTH_COOKIE;

    @Autowired
    private UserService userService;

    final private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * 유저정보에서 토큰 가져오기
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * JWT Token 생성
     * @param username
     * @return
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setIssuer(APP_NAME) // 토큰 발급자
                .setSubject(username) // 토큰 주제(토큰에 담길 내용)
                .setIssuedAt(generateCurrentDate())
                .setExpiration(generateExpirationDate()) // 토큰 만료시간
                .signWith(SIGNATURE_ALGORITHM, SECRET) // 토큰의 서명 알고리
                .compact(); // 토큰 생성
    }

    /**
     * 토큰의 권한 가져오
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * JWT 권한(Claims) 설정
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        try {
            final Date expirationDate = getClaimsFromToken(token).getExpiration();
            String username = getUsernameFromToken(token);
            UserDetails userDetails = userService.findByUsername(username);
            return expirationDate.compareTo(generateCurrentDate()) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 토큰 재생성
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.setIssuedAt(generateCurrentDate());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 현재시간(밀리 세컨드) 생성
     * @return
     */
    private long getCurrentTimeMillis() {
        return DateTime.now().getMillis();
    }

    /**
     * 현재시간 생성
     * @return
     */
    private Date generateCurrentDate() {
        return new Date(getCurrentTimeMillis());
    }

    /**
     * 토큰 만료시간 생성
     * @return
     */
    private Date generateExpirationDate() {

        return new Date(getCurrentTimeMillis() + this.EXPIRES_IN * 1000);
    }

    public String getToken(HttpServletRequest request) {
        /**
         *  Getting the token from Cookie store
         */
        Cookie authCookie = getCookieValueByName(request, AUTH_COOKIE);
        if (authCookie != null) {
            return authCookie.getValue();
        }
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            return authHeader.substring(7);
        }

        return null;
    }

    /**
     * Find a specific HTTP cookie in a request.
     *
     * @param request
     *            The HTTP request object.
     * @param name
     *            The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */
    public Cookie getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals(name)) {
                return request.getCookies()[i];
            }
        }
        return null;
    }
}
