package com.sharifyy.tokenverifier;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class TokenVerifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenVerifierApplication.class, args);
	}

//	@Bean
//	ApplicationRunner applicationRunner(TokenVerifierService service){
//		return args -> {
//			String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIyS3oweEFqa0xJRzNfVjZLMGRPT2c0TGhFbUw1VU5Qc3gyZTJZQWhBNlBrIn0.eyJleHAiOjE2MzE5MDIyNTksImlhdCI6MTYzMTg2NjI4OCwiYXV0aF90aW1lIjoxNjMxODY2MjU5LCJqdGkiOiI0YmZiZmE1Zi1iNDU0LTRjODctOTA5Ny1mYjA4MDU0N2FlMDQiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvZGVtbyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJmOWI4ZTc3ZC05ZTFlLTQ3YmQtYWYwZi0zZDYzZWNkZTQ2ZTciLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJzcHJpbmctY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6IjZmNTcxNGYyLWU2MDMtNGZhMS1hYmM3LWFiNzJjNGI0YTU4NCIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLWRlbW8iLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjZmNTcxNGYyLWU2MDMtNGZhMS1hYmM3LWFiNzJjNGI0YTU4NCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzaGFyaWZ5eSIsImVtYWlsIjoic2hhcmlmeXlAZ21haWwuY29tIn0.e3kbXKshUVhMSkmkdj3q2DXdnZgECRMRCxBb2YEONFQTzOBi7LO8p8X4b0Evwkfw8wtBddiKVg6NmbJR1Q8Vdhh3j8VWjObW6pIH42Jlj_Pg5dYrusD6utk1eNdFgm0PYwN-PKBqkVGNGpKh682-qQo2CZvLyFqtHcdHjPCRnMmqsCy6zlaUhYykAiq-kbHFdLHMFEnEzabOD22Y-HZ4M7li3-otRMLR261YiHZT8qF44QfCoQAKYVuKK-QkVEAtDwioZhdZhMskUq3lQD0tP0ju_yawLQNDK24zO0SvK4xRwQmK6qYwmrZw5ouv_Jv1zipkALlqgBhtHEuhl9MmZw";
//			ResponseEntity<PermissionEvaluationResponse> responseEntity = service.verifyPermission("spring-client", "http://localhost:9090/pics", token);
//			System.out.println(responseEntity.getStatusCode());
//		};
//	}


}
