package jp.tst.audittool.apiresource;

import java.util.Arrays;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	@Autowired
	private DataSource dataSource;
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

		endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
				.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		// JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new
		// ClassPathResource("mytest.jks"),
		// "mypass".toCharArray());
		// converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
		// return converter;
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("123");
		return converter;
	}

	// @Bean
	// @Primary
	// public DefaultTokenServices tokenServices() {
	// DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	// defaultTokenServices.setTokenStore(tokenStore());
	// defaultTokenServices.setSupportRefreshToken(true);
	// return defaultTokenServices;
	// }
	public Map<String, Object> getExtraInfo(Authentication auth) {
		OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
		return (Map<String, Object>) oauthDetails.getDecodedDetails();
	}
}