package com.app.repo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entity.user;

public class myUser implements UserDetails{


	private final user user;
	private final String email;
	private final String lastName;
	private final String firstName;
	private final LocalDate regDate;
	private final Integer rate;



	public String getEmail() {
		return email;
	}

	public myUser(com.app.entity.user user) {
		super();
		this.user = user;
		this.email = user.getEmail();
	    this.lastName = user.getLastName();
	    this.firstName = user.getFirstName();
	    this.regDate = user.getRegDate();
	    this.rate = user.getRate();

	}
	
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public Integer getRate() {
		return rate;
	}

	public user getUser() {
		return user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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
		

}
