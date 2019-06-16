package estagio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import estagio.model.Admin;
import estagio.model.Aluno;
import estagio.model.Empresa;
import estagio.model.SupervisorEstagio;
import estagio.model.Usuario;
import estagio.repository.AdminRepository;
import estagio.repository.AlunoRepository;
import estagio.repository.EmpresaRepository;
import estagio.repository.SupervisorEstagioRepository;

@Service
@Transactional
public class ImplementacaoUserDetailService implements UserDetailsService{

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private SupervisorEstagioRepository supervisorEstagioRepository;
	
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario aluno = alunoRepository.findAlunoByEmail(email);
		Empresa empresa = empresaRepository.findEmpresaByEmail(email);
		Admin admin = adminRepository.findAdminByEmail(email);
		SupervisorEstagio supervisor = supervisorEstagioRepository.findSupervisorEstagioByEmail(email);
		
		if(aluno == null && empresa == null && admin == null && supervisor == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		
		if(aluno != null) {
			return new User(aluno.getEmail(), aluno.getPassword(), aluno.isEnabled(), true, true, true, aluno.getAuthorities());
		} else if(empresa != null) {
			return new User(empresa.getEmail(), empresa.getPassword(), empresa.isEnabled(), true, true, true, empresa.getAuthorities());
		} else if (admin != null){
			return new User(admin.getEmail(), admin.getPassword(), admin.isEnabled(), true, true, true, admin.getAuthorities());
		} else {
			return new User(supervisor.getEmail(), supervisor.getPassword(), supervisor.isEnabled(), true, true, true, supervisor.getAuthorities());
		}
		
	}

}
