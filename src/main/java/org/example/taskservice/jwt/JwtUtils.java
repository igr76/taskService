package org.example.taskservice.jwt;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {

        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setUserName(claims.getSubject());
        return jwtInfoToken;
    }

}
