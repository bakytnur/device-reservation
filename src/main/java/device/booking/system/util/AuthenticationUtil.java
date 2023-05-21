package device.booking.system.util;

import device.booking.system.exception.UserAuthenticationException;
import device.booking.system.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthenticationUtil {
    @Autowired
    private JwtService service;

    public long validateTokenAndGetUserId(String authorization) {
        String userId = parseAndGetToken(authorization);
        if (userId == null) {
            throw new UserAuthenticationException("Authentication required!");
        }

        return Long.parseLong(userId);
    }

    private String parseAndGetToken(String authorization) {
        String token = parseAuthorization(authorization);
        Claims claims = service.decodeToken(token);
        if (!service.isTokenExpired(claims)) {
            return service.getUserIdFromToken(claims);
        }

        return null;
    }

    private String parseAuthorization(String authorization) {
        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer")) {
            throw new UserAuthenticationException("User not authenticated!");
        }

        String[] headerValues = authorization.split(" ");
        if (headerValues.length < 2) {
            throw new UserAuthenticationException("User not authenticated!");
        }

        return headerValues[1];
    }
}
