package com.example.HappyNewHere.config;

import com.example.HappyNewHere.service.AccountService;
import com.example.HappyNewHere.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilterConfig extends OncePerRequestFilter {
    private final AccountService accountService;
    private final String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //헤더에서 authorization부분 가져오기
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        //authorizatio에 아무것도 없을때 -> 로그인 안한 사용자(바로 return)
        if(authorization==null){
            filterChain.doFilter(request,response);
            return;
        }

        //authorization에서 token가져오기
        String token=authorization.replace("Bearer ","");
        //만료된 토큰인지 아닌지 체크 -->만료(바로 return)
        if(JwtUtils.isExpired(token,secretKey)){
            System.out.println("만료된 토큰입니다.");
            filterChain.doFilter(request,response);
            return;
        }
        //토큰에서 사용자Id 꺼내오기
        final Long accountId = JwtUtils.extractMember(token,secretKey);
        System.out.println("accountId = " + accountId);


        //가입한 사용자에 대해 막혀있는 권한에 대해 허용 설정
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(accountId,null, List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}