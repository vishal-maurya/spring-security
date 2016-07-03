package com.ttnd.springSecurity.securityCredentials;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttnd.springSecurity.Daos.UserDao;
import com.ttnd.springSecurity.entities.User;
import com.ttnd.springSecurity.entities.UserRoles;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		System.out.println(user.getPassword()+":"+user.getUserRoles().size());
		//List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());

		//return buildUserForAuthentication(user, authorities);
		List<String> userRoles = buildUserRoles(user.getUserRoles());
		return new CustomUserDetails(user, userRoles);

	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, 
			List<GrantedAuthority> authorities) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), 
					user.isEnabled(), true, true, true, authorities);
		}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRoles> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRoles userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getType()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	private List<String> buildUserRoles(Set<UserRoles> userRoles) {

		Set<String> setAuths = new HashSet<String>();

		// Build user's authorities
		for (UserRoles userRole : userRoles) {
			setAuths.add(userRole.getType());
		}

		List<String> Result = new ArrayList<String>(setAuths);

		return Result;
	}
	


}
