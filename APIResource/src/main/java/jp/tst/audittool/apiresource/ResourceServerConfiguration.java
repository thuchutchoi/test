package jp.tst.audittool.apiresource;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author nghia.nguyen
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "device_rest_api";
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		// resources.resourceId(RESOURCE_ID).stateless(false);
		resources.resourceId(RESOURCE_ID).stateless(false).tokenServices(tokenServices());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		// JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// Resource resource = new ClassPathResource("public.txt");
		// String publicKey = null;
		// try {
		// publicKey = IOUtils.toString(resource.getInputStream());
		// } catch (final IOException e) {
		// throw new RuntimeException(e);
		// }
		// converter.setVerifierKey(publicKey);
		// return converter;
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("123");
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		anonymous().disable()
				.requestMatchers().antMatchers("/user/**")
		.and().authorizeRequests()
				.antMatchers("/user/**").access("hasRole('ADMIN')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

	public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
		OAuth2AccessToken accessToken = tokenStore().readAccessToken(details.getTokenValue());
		return accessToken.getAdditionalInformation();
	}
}