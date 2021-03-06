package com.rakki.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rakki.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberTests {
	@Autowired @Setter @Qualifier("BCryptPasswordEncoder")
	private PasswordEncoder encoder;
	@Autowired @Setter
	private MemberMapper memberMapper;
	
	
	private List<Map<String, Object>> members = new ArrayList<>();
	
	@Before
	public void init() {
		for(int i = 0; i <= 10 ; i++) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("password", encoder.encode("1234"));
			
			
			if(i < 7) {
				map.put("id", "UPDATETEST");
				map.put("email", "TEST@gmail");
				map.put("name", "일반사용자");
				map.put("phone", "010-1234-5678");
				map.put("auth", "ROLE_USER");
				
			} else if(i < 9) {
				map.put("id", "member" + i);
				map.put("email", "member@gmail" + i);
				map.put("name", "회원");
				map.put("phone", "010-1234-5678");
				map.put("auth", "ROLE_MEMBER");
				
			} else {
				map.put("id", "admin" + i);
				map.put("email", "admin@gmail" + i);
				map.put("name", "관리자");
				map.put("phone", "010-1234-5678");
				map.put("auth", "ROLE_ADMIN");
			}
			members.add(map);
		}
		
	}
	
	// member
	@Test
	public void testInsert() {
		members.forEach(m -> memberMapper.insertMember(m));
	}
	// auth
	@Test
	public void testAuth() {
		members.forEach(m -> memberMapper.insertAuth(m));
	}
	
	@Test
	public void testAuth2() {
		List<Map<String, Object>> list = new ArrayList<>();
		for(int i = 90; i < 100 ; i++) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("userid", "admin" + i);
			map.put("auth", "ROLE_MEMBER");
			list.add(map);
		}
		list.forEach(m -> memberMapper.insertAuth(m));
	}
	
	// 통합
	@Test
	public void testInte() {
		
	}
}