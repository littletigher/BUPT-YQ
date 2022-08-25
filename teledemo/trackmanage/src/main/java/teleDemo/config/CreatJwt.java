package teleDemo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import teleDemo.entities.userlogin;

import java.util.Date;

public class CreatJwt {
    public static String getoken(userlogin user) {
        Jwts.builder();//生成
        Jwts.parser();//解密

        JwtBuilder jwtBuilder = Jwts.builder()
                //设置需要加密的内容
                .setId(user.getUserID() + "")
                .setSubject(user.getUserName())
                //token保留时间
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "my-123").setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                );
        return jwtBuilder.compact();
    }
    public static userlogin getUser(String token){

        Claims claims= Jwts.parser()
                .setSigningKey("my-123")
                .parseClaimsJws(token)
                .getBody();
        //解密ID
        Integer id=Integer.valueOf(claims.getId());
        //解密用户名
        String username=claims.getSubject();
        userlogin user = new userlogin(id,username,"",0);
        return user;
    }
}

