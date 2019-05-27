package estagio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import estagio.configuration.PopularBanco;
import estagio.security.ImplementacaoUserDetailService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementacaoUserDetailService implementacaoUserDetailService;
	
	@Override //Configura as solicitações de acesso por Http
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable() //Desativa as configurações padrões de memória.
		.authorizeRequests() //Permitir/restringir acessos.
		.antMatchers(HttpMethod.GET, "/cadastrar/**").permitAll()
		.antMatchers(HttpMethod.POST, "/cadastrar/**").permitAll()
		.antMatchers(HttpMethod.GET, "/salvar/**").permitAll()
		.antMatchers(HttpMethod.POST, "/salvar/**").permitAll()
		//.antMatchers(HttpMethod.GET, "/menu").hasAnyRole("ADMIN", "GERENTE", "ALMOXARIFE", "VENDEDOR")
		
		.anyRequest().authenticated()
		.and().formLogin().permitAll() //Permite qualquer usuário
		.loginPage("/login")
		.defaultSuccessUrl("/curriculo")
		.failureUrl("/login?error=true")
		.and().logout().logoutSuccessUrl("/login") //Mapeia URL de Logout e invalída usuário autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementacaoUserDetailService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**")
		.antMatchers("/icons/**")
		.antMatchers("/img/**");
	}
}
