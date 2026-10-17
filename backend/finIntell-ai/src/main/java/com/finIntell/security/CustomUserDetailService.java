package com.finIntell.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finIntell.entity.Users;
import com.finIntell.repo.UsersRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService  implements UserDetailsService{

	private final UsersRepo userRepo;
	
	public CustomUserDetailService(UsersRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
		return user;
	}
}
