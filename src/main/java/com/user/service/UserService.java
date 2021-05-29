package com.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.DTOConverter;
import com.user.entity.User;
import com.user.repository.UserRepository;

import dto.UserDTO;
import exceptions.UserNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO store(UserDTO userDTO) {
		userDTO.setKey(UUID.randomUUID().toString());
		User user = DTOConverter.convertDTO(userDTO);
		user.setDataCadastro(LocalDateTime.now());
		user = userRepository.save(user);
		return DTOConverter.convert(user);
	}
	
	public List<UserDTO> getAllUsers(){
		List<User> usuarios = userRepository.findAll();
		return usuarios.stream()
		.map(DTOConverter::convert)
		.collect(Collectors.toList());
	}
	
	public List<UserDTO> getUsersByName(String nome){
		List<User> usuarios = userRepository.findByNomeContaining(nome);
		return usuarios.stream()
		.map(DTOConverter::convert)
		.collect(Collectors.toList());
	}
	
	public UserDTO findByCpf(String cpf) {
		Optional<User> user = userRepository.findByCpf(cpf);
		if(user.isPresent()) {
			return DTOConverter.convert(user.get());
		}
		throw new UserNotFoundException();
	}
	
	public UserDTO findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return DTOConverter.convert(user.get());
		}
		throw new UserNotFoundException();
	}
	
	public UserDTO delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return DTOConverter.convert(user.get());
		}
		
		throw new UserNotFoundException();
	}

	public UserDTO findByCpfAndKey(String cpf, String key) {
		Optional<User> user = userRepository.findByCpfAndKey(cpf, key);
		if(user.isPresent()) {
			return DTOConverter.convert(user.get());
		}
		
		throw new UserNotFoundException();
	}
	
	

}
