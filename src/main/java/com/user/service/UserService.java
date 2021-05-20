package com.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.UserDTO;
import com.user.entity.User;
import com.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO store(UserDTO userDTO) {
		User user = User.convert(userDTO);
		user = userRepository.save(user);
		return UserDTO.convert(user);
	}
	
	public List<UserDTO> getAllUsers(){
		List<User> usuarios = userRepository.findAll();
		return usuarios.stream()
		.map(UserDTO::convert)
		.collect(Collectors.toList());
	}
	
	public List<UserDTO> getUsersByName(String nome){
		List<User> usuarios = userRepository.findByNomeContaining(nome);
		return usuarios.stream()
		.map(UserDTO::convert)
		.collect(Collectors.toList());
	}
	
	public UserDTO findByCpf(String cpf) {
		Optional<User> user = userRepository.findByCpf(cpf);
		if(user.isPresent()) {
			return UserDTO.convert(user.get());
		}
		return null;
	}
	
	public UserDTO findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return UserDTO.convert(user.get());
		}
		return null;
	}
	
	public UserDTO delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return UserDTO.convert(user.get());
		}
		
		return null;
	}
	
	

}
