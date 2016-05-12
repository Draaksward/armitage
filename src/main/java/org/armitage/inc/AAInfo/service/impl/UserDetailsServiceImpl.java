package org.armitage.inc.AAInfo.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.armitage.inc.AAInfo.dao.UserRepository;
import org.armitage.inc.AAInfo.entity.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Logger logger;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    final User user = userRepository.getByLogin(username);
	    if(user == null){
	        logger.warn("user with login \""+username+"\" not found");
	        throw new UsernameNotFoundException(username);
	    }
		UserDetails details = new UserDetails(){
			private static final long serialVersionUID = 1L;

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
				return roles;
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getUserName();
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isEnabled() {
				return true;
			}
			
		};
		logger.debug("User found. Returning userDetails obj");
		return details;
	}

}
