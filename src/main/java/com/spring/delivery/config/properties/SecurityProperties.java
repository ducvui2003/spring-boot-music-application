package com.spring.delivery.config.properties;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.api.client.json.JsonFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.gson.GsonFactory;

import lombok.Data;

@Data
@Component("securityProperties")
@ConfigurationProperties(prefix = "spring.security.allowed")
public class SecurityProperties {
	private List<String> origins;
	private List<String> methods;
	private List<String> headers;

	@Bean
	public GoogleClientSecrets googleClientSecrets() throws IOException {
		JsonFactory jsonFactory = new GsonFactory();

		// Use getResourceAsStream to load the resource from the classpath (works for both JAR and filesystem)
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("client_secret.json")) {
			if (inputStream == null) {
				throw new IOException("client_secret.json not found in classpath");
			}

			// Return the GoogleClientSecrets loaded from the input stream
			return GoogleClientSecrets.load(jsonFactory, new InputStreamReader(inputStream));
		}
	}
}
