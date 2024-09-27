package com.k8s.ci;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class CiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiApplication.class, args);
	}

	@RestController
	public static class Con {

		@GetMapping("/")
		public String getHostname() {
			String hostname = System.getenv().getOrDefault("HOSTNAME", "unknown");
			return "Hello Docker World from " + hostname;
		}
		

		@GetMapping("/v2")
		public String getMethodName() {
			//호스트명구하기
			String hostname = System.getenv().getOrDefault("HOSTNAME", "unknown");
			// 경로 /mnt/nfs/javalove93/message.txt 읽어서 문자열에 저장
			String message = "";
			try {
				message = new String(Files.readAllBytes(Paths.get("/mnt/nfs/javalove93/message.txt")));
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "v2" + hostname + ", NFS message is " +
			message;
		}
	}
}
