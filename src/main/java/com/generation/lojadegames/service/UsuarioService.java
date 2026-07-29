package com.generation.lojadegames.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojadegames.model.Usuario;
import com.generation.lojadegames.model.UsuarioLogin;
import com.generation.lojadegames.repository.UsuarioRepository;
import com.generation.lojadegames.security.JwtService;

public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> getById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
		 if (usuario.getData_nascimento()
		            .isAfter(LocalDate.now().minusYears(18))) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cadastro permitido apenas para maiores de 18 anos");
		    }
		    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		    usuario.setId(null);
		    return Optional.of(usuarioRepository.save(usuario));
		}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		if(usuarioRepository.findById(usuario.getId()).isEmpty()) {
			return Optional.empty();
		}
		
		Optional <Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getNome());
		
		if(usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(usuario.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse e-mail já está em uso!" , null);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
		return Optional.of(usuarioRepository.save(usuario));
	}
	
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin){
		if(usuarioLogin.isEmpty()) {
			return Optional.empty();
		}
		
		UsuarioLogin login = usuarioLogin.get();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getSenha()));
			
			return usuarioRepository.findByUsuario(login.getUsuario())
					.map(usuario -> construirRespostaLogin(login, usuario));
		}catch(Exception e) {
			return Optional.empty();
		}
	}
	private UsuarioLogin construirRespostaLogin(UsuarioLogin usuarioLogin, Usuario usuario) {
		usuarioLogin.setId(usuario.getId());
		usuarioLogin.setNome(usuario.getNome());
		usuarioLogin.setSenha("");
		usuarioLogin.setToken(gerarToken(usuario.getNome()));
		return usuarioLogin;
	}

	private String gerarToken(String usuario) {
		return "Bearer " + jwtService.generateToken(usuario);
	}
	
	public void delete(Long id){
	    usuarioRepository.deleteById(id);
	}
}

