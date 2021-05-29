package com.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByCpf(String cpf);
	
	List<User> findByNomeContaining(String nome);
	
	Optional<User> findByCpfAndKey(String cpf, String key);

}
