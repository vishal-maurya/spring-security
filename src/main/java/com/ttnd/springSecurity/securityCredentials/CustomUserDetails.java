package com.ttnd.springSecurity.securityCredentials;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.ttnd.springSecurity.entities.User;

public class CustomUserDetails extends User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<String> userRoles;
	
	public CustomUserDetails(User user, List<String> userRoles){
		super(user);
		this.userRoles = userRoles;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles=StringUtils.collectionToCommaDelimitedString(userRoles);	
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	public String getUsername() {
		return super.getUserName();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
