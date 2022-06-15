package com.company;

import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KunUzApplication {

//	@Autowired
//	private ProfileService service;

	public static void main(String[] args) {
		SpringApplication.run(KunUzApplication.class, args);
	}


//
//	@Bean
//	CommandLineRunner ok() {
//		return args -> {
//			ProfileEntity moderator = new ProfileEntity();
//			moderator.setName("bek");
//			moderator.setSurname("bek13");
//			moderator.setPassword("1234");
//			moderator.setStatus(ProfileStatus.ACTIVE);
//			moderator.setRole(ProfileRole.MODERATOR);
//			moderator.setEmail("otabek@mail.ru");
//			service.save(moderator);
//
//			String encode = JwtUtil.encode(moderator.getId());
//			System.err.println("MODERATOR :  "+encode);
//
//			ProfileEntity admin = new ProfileEntity();
//			admin.setName("qobil");
//			admin.setSurname("odilov");
//			admin.setPassword("0512");
//			admin.setEmail("qobil@mail.ru");
//			admin.setStatus(ProfileStatus.ACTIVE);
//			admin.setRole(ProfileRole.ADMIN);
//			service.save(admin);
//			String encode1 = JwtUtil.encode(admin.getId());
//			System.err.println("ADMIN :  "+encode1);
//
//			ProfileEntity publisher=new ProfileEntity();
//			publisher.setName("publisher");
//			publisher.setSurname("publisherov");
//			publisher.setPassword("1111");
//			publisher.setEmail("publisher@gmail.com");
//			publisher.setStatus(ProfileStatus.ACTIVE);
//			publisher.setRole(ProfileRole.PUBLISHER);
//			service.save(publisher);
//			String encode2= JwtUtil.encode(publisher.getId());
//			System.err.println("PUBLISHER:  "+encode2);
//		};
//	}
}
