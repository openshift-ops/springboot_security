package com.springboot.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Bishesh
 */
@Service
public class JwtUtil {

    private static final String SECRET = "secretkey";

    //extract username
    public String extractUserName(String token)
    {
        return extractClaims(token,Claims::getSubject);
    }

    //extract expirationDate
    public Date extractExpirationDate(String token)
    {
        return extractClaims(token,Claims::getExpiration);
    }

    //validate if token expired
    public boolean isTokenExpired(String token)
    {
        return extractExpirationDate(token).before(new Date());
    }


    //extract claims
    public <T> T extractClaims(String token, Function <Claims , T> claimsFunction)
    {
        Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    //extract token
    public Claims extractAllClaims(String token)
    {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();  //parseClaimsJws means the token has been succesfully signed
    }


    //generate token
    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    //create token
    public String createToken(Map<String,Object> claims, String subject)
    {
         return Jwts.builder().addClaims(claims).setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + (1000*1000)))
                .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,SECRET).compact();

    }
    //validate token
    public boolean validateToken(String token , @NotNull UserDetails userDetails)
    {
        final String userName = extractUserName(token);
       return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
