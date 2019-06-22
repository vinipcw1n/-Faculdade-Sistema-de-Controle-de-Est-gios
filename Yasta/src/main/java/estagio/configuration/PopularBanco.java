package estagio.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import estagio.model.Admin;
import estagio.model.Aluno;
import estagio.model.Curriculo;
import estagio.model.Empresa;
import estagio.model.Role;
import estagio.repository.AdminRepository;
import estagio.repository.AlunoRepository;
import estagio.repository.CurriculoRepository;
import estagio.repository.EmpresaRepository;
import estagio.repository.RoleRepository;

@Configuration
public class PopularBanco {
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private CurriculoRepository curriculoRepository;
	
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
	
	@Bean
	public void criarAdmin() {
		List<Admin> admins = (List<Admin>) adminRepository.findAll();
		
		if(admins.isEmpty()) {
			Admin admin = new Admin();
			admin.setNome("Admin");
			admin.setEstado("SP");
			admin.setEndereco("Rua teste");
			admin.setCidade("Teste");
			admin.setCep("123");
			admin.setEmail("admin@admin");
			admin.setSenha("admin");
			admin.setRole(Long.parseLong("3"));
			admin.setAprovado(true);
			adminRepository.save(admin);
		}
	}
	
	@Bean
	public void criarAluno() {
		List<Aluno> alunos = (List<Aluno>) alunoRepository.findAll();
		
		if(alunos.isEmpty()) {
			Aluno aluno = new Aluno();
			aluno.setCep("123");
			aluno.setCidade("Teste");
			aluno.setCurso("ADS");
			aluno.setDataIngresso("05/05/2019");
			aluno.setEstado("SP");
			aluno.setEmail("aluno@aluno");
			aluno.setSenha("aluno");
			aluno.setRole(Long.parseLong("0"));
			aluno.setAprovado(true);
			aluno.setNome("Aluno");
			aluno.setEndereco("Av. Teste, número 123");
			aluno.setTelefone("(16)9999-8888");
			
			aluno.setCurriculo(new Curriculo());
			alunoRepository.save(aluno);
		}
	}
	
	@Bean
	public void criarEmpresa() {
		List<Empresa> empresas = (List<Empresa>) empresaRepository.findAll();
		
		if(empresas.isEmpty()) {
			Empresa empresa = new Empresa();
			empresa.setEmail("empresa@empresa");
			empresa.setSenha("empresa");
			empresa.setRole(Long.parseLong("1"));
			empresa.setAprovado(true);
			empresa.setNome("Empresa");
			empresa.setEndereco("Av. Teste, número 123");
			empresa.setTelefone("(16)9999-8888");
	
			empresaRepository.save(empresa);
		}
	}
}
