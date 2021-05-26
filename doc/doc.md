# Configuration Spring

Il faut ajouter la dépendance spring boot OAuth 2 Client
```xml
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
      </dependency>
```

Il faut ajouter la classe de configuration :
```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/owners/**").authenticated()
			.antMatchers("/").permitAll()
			.and()
			.oauth2Login();
	}
}
```
Il faut ajouter les parametres dans la conf du fichier application.yml :
```yml
# url de keycloak
baseUrl: http://localhost:8080/auth

spring:
  security:
    oauth2:
      client:
        registration:
          client1:
            client-id: client1
            #client-secret: example-secret
            authorization-grant-type: authorization_code
            redirect-uri: '{baseUrl}/login/oauth2/code/{registrationId}'
            client-authentication-method: basic
            client-name: Client1
            scope:
              - openid
              - profile
              - email
              - address
              - phone
        provider:
          client1:
            authorization-uri: '${baseUrl}/realms/test1/protocol/openid-connect/auth'
            token-uri: '${baseUrl}/realms/test1/protocol/openid-connect/token'
            jwk-set-uri: '${baseUrl}/realms/test1/protocol/openid-connect/certs'
```
Cet exemple considère que KeyCloak est à l'adresse : http://localhost:8080
et que le realm est test1, et le client est client1

# Logout
Pour se déconnecter, il faut ajouter :
```java
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// ...
			.and()
			// logout ne délogue pas dans KeyCloak
			.logout().logoutSuccessUrl("/").permitAll();
	}
```
Cela ajoute l'url /logout a appeler en post pour supprimer la session http.
Cela ne délogue pas dans KeyCloak.
Pour déloguer dans KeyCloak, il faut créer une url différente et appeler l'url de logout de KeyCloak

# urls

https://www.jtips.info/Spring/security-oauth2
https://medium.com/@bcarunmail/securing-rest-api-using-keycloak-and-spring-oauth2-6ddf3a1efcc2
https://www.baeldung.com/spring-boot-keycloak (avec keycloak adapter)



