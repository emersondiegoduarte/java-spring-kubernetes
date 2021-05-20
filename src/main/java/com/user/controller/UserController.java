package com.user.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserDTO;
import com.user.exception.UserNotFoundException;
import com.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private List<UserDTO> users = new ArrayList<>();
	
	@PostConstruct
	public void initiateList() {
		UserDTO	userDTO	= new UserDTO();
		userDTO.setNome("Eduardo");
		userDTO.setCpf("123");
		userDTO.setEndereco("Rua a");
		userDTO.setEmail("eduardo@email.com");
		userDTO.setTelefone("1234-3454");
		userDTO.setDataCadastro(LocalDateTime.now());
		UserDTO	userDTO2 = new UserDTO();
		userDTO2.setNome("Luiz");
		userDTO2.setCpf("456");
		userDTO2.setEndereco("Rua b");
		userDTO.setEmail("luiz@email.com");
		userDTO.setTelefone("1234-3454");
		userDTO.setDataCadastro(LocalDateTime.now());
		UserDTO	userDTO3 = new	UserDTO();
		userDTO3.setNome("Bruna");
		userDTO3.setCpf("678");
		userDTO3.setEndereco("Rua c");
		userDTO.setEmail("bruna@email.com");
		userDTO.setTelefone("1234-3454");
		userDTO.setDataCadastro(LocalDateTime.now());
		users.add(userDTO);
		users.add(userDTO2);
		users.add(userDTO3);
	}
	
	@GetMapping("/")
	public List<UserDTO> getUsers() {
		return userService.getAllUsers();
	}
	
//	@GetMapping("/{cpf}")
//	public UserDTO getUserByCpf(@PathVariable(name = "cpf", required = true) String cpf) {
//		Optional<UserDTO> optional = users.stream().filter(user -> user.getCpf().equals(cpf)).findFirst();
//		return optional.orElse(null);
//	}
	
	@GetMapping("/{cpf}")
	public UserDTO getUserByCpf(@PathVariable(name = "cpf", required = true) String cpf) {
		UserDTO user = userService.findByCpf(cpf);
		return user;
	}
	
	@GetMapping("/search")
	public List<UserDTO> getUsersByName(@RequestParam(name = "nome", required = true) String nome) {
		return userService.getUsersByName(nome);
	}
	
	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable(name = "id", required = true) Long id) {
		return userService.findById(id);
	}
	
	@PostMapping
	public UserDTO inserir(@RequestBody UserDTO userDTO) {
		userDTO.setDataCadastro(LocalDateTime.now());
		userDTO = userService.store(userDTO);
		return userDTO;
	}

	
//	@DeleteMapping("/{cpf}")
//	public Boolean deleteUserByCpf(@PathVariable(name = "cpf", required = true) String cpf) {
//		Optional<UserDTO> optional = users.stream().filter(user -> user.getCpf().equals(cpf)).findFirst();
//		UserDTO user = optional.orElse(null);
//		if(user != null) {
//			users.remove(user);
//			return true;
//		}
//		
//		return false;
//	}
	
	@DeleteMapping("/{id}")
	public UserDTO deleteUserByCpf(@PathVariable(name = "id", required = true) Long id) throws UserNotFoundException {
		return userService.delete(id);
	}
}
