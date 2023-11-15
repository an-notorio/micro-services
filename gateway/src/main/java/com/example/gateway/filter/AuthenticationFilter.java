package com.example.gateway.filter;

import com.example.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;
//    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
//            System.out.println(config.getRole().get(0));
//            System.out.println(config.getRole().get(1));
//            System.out.println(config.getRole().get(0));
//            System.out.println(config.getRole());
            if (validator.isSecured.test(exchange.getRequest())) {

                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                if(config.getRole()!=null){
                    List<String> userRoles = jwtUtil.extractRoles(authHeader);
                    System.out.println(userRoles);
                    if(!(userRoles.stream().anyMatch(config.role::contains))){
                        System.out.println("invalid access!");
                        throw new RuntimeException("unauthorized access to application");
                    }
                }

                try {
                    //REST call to AUTH service
//                    template.getForObject("http://authenticator//api/authenticate?token" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    System.out.println("invalid access!");
                    throw new RuntimeException("unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
        private List<String> role;

        public List<String> getRole() {
            return role;
        }

        public void setRole(List<String> role) {
            this.role = role;
        }
    }
}
