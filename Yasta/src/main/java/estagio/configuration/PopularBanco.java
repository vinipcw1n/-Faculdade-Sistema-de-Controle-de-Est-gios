package estagio.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import estagio.model.Role;
import estagio.repository.RoleRepository;

@Configuration
public class PopularBanco {
	@Autowired
	private RoleRepository roleRepository;
	
	@Bean
	public void popularRole() {
		List<Role> roles = (List<Role>) roleRepository.findAll();
		
		if(roles.isEmpty()) {
			Role role = new Role();
			role.setId(Long.parseLong("0"));
			role.setNomeRole("ROLE_ALUNO");
			roleRepository.save(role);
			
			role.setId(Long.parseLong("1"));
			role.setNomeRole("ROLE_EMPRESA");
			roleRepository.save(role);
			
			role.setId(Long.parseLong("2"));
			role.setNomeRole("ROLE_SUPERVISOR");
			roleRepository.save(role);
			
			role.setId(Long.parseLong("3"));
			role.setNomeRole("ROLE_ADMIN");
			roleRepository.save(role);
		}
	}
}
