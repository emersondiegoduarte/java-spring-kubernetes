package com.user.dto;

import com.user.entity.User;

import dto.UserDTO;

public class DTOConverter {
	
	public static UserDTO convert(User user) {
		UserDTO	userDTO	=	new	UserDTO();
		userDTO.setNome(user.getNome());
		userDTO.setEndereco(user.getEndereco());
		userDTO.setCpf(user.getCpf());
		return	userDTO;

	}
	
	public static User convertDTO(UserDTO user) {
		User	userDTO	=	new	User();
		userDTO.setNome(user.getNome());
		userDTO.setEndereco(user.getEndereco());
		userDTO.setCpf(user.getCpf());
		return	userDTO;

	}

}
