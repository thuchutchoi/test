package jp.tst.audittool.apiresource;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.tst.audittool.apiresource.entity.UserInfo;
import jp.tst.audittool.apiresource.service.UserInfoService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserInfoService userService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userService.findUserInfoByUsername(username);
		logger.info("User : {}", user);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true,
				true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(UserInfo user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().getRoleName()));
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}
