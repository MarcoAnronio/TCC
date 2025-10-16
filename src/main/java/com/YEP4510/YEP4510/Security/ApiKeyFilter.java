package com.YEP4510.YEP4510.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${app.api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        // ✅ Libera requisições GET e OPTIONS (consultas públicas)
        if (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Libera rotas públicas (login, documentação, favicon)
        if (path.startsWith("/auth/login")
                || path.startsWith("/auth/register")
                || path.startsWith("/favicon")
                || path.contains("swagger")
                || path.contains("api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Busca a chave de aplicação no header personalizado X-Api-Key
        String header = request.getHeader("X-Api-Key");

        if (header == null || !header.equals(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Acesso não autorizado: token de aplicação inválido.");
            return;
        }

        // ✅ Se passou em todas as verificações, continua o fluxo normalmente
        filterChain.doFilter(request, response);
    }
}
