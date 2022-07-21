package br.com.alura.comex.shared.aplicacao;

import br.com.alura.comex.aplicacao.usuario.AutenticacaoService;
import br.com.alura.comex.aplicacao.usuario.AutenticacaoViaTokenService;
import br.com.alura.comex.aplicacao.usuario.TokenService;
import br.com.alura.comex.dominio.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

  @Autowired
  private AutenticacaoService autenticacaoService;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository usuarioRepository;

  //Configuracoes de autenticacao
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration,
                                                     ApplicationContext context,
                                                     ObjectPostProcessor<Object> objectObjectPostProcessor
                                                     ) throws Exception {
    authenticationConfiguration.authenticationManagerBuilder(objectObjectPostProcessor, context).userDetailsService(autenticacaoService)
            .passwordEncoder(new BCryptPasswordEncoder());
    return authenticationConfiguration.getAuthenticationManager();
  }



  //Configuracoes de autorizacao
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/categorias/**").permitAll()
            .antMatchers(HttpMethod.GET, "/produtos/**").permitAll()
            .antMatchers(HttpMethod.GET, "/usuario/**").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
            .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v3/api-docs/**").permitAll()
            .antMatchers(HttpMethod.GET, "/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM/**").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(new AutenticacaoViaTokenService(tokenService, usuarioRepository),
                    UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }


  //Configuracoes de recursos estaticos(js, css, imagens, etc.)
//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//    return web ->  web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
//  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
