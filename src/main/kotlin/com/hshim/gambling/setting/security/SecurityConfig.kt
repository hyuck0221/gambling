package com.hshim.gambling.setting.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
                auth.requestMatchers("/", "/login")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .oauth2Login { it.defaultSuccessUrl("/profile", true) }
        return http.build()
    }
}