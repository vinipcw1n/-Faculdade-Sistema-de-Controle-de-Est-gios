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
import estagio.model.Usuario;
import estagio.repository.AdminRepository;
import estagio.repository.AlunoRepository;
import estagio.repository.EmpresaRepository;

@Service
@Transactional
public class ImplementacaoUserDetailService implements UserDetailsService{

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario aluno = alunoRepository.findAlunoByEmail(email);
		Empresa empresa = empresaRepository.findEmpresaByEmail(email);
		Admin admin = adminRepository.findAdminByEmail(email);
		
		if(aluno == null && empresa == null && admin == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		
		if(aluno != null) {
			return new User(aluno.getEmail(), aluno.getPassword(), true, true, true, true, aluno.getAuthorities());
		} else if(empresa != null) {
			return new User(empresa.getEmail(), empresa.getPassword(), true, true, true, true, empresa.getAuthorities());
		} else {
			return new User(admin.getEmail(), admin.getPassword(), true, true, true, true, admin.getAuthorities());
		}
		
	}

}
