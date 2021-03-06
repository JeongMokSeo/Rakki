package com.rakki.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data @Alias("member")
public class MemberVo {
	// 회원 넘버
	private int mno;
	// 회원 아이디
	private String id;
	// 회원 비밀번호
	private String password;
	// 회원 이메일
	private String email;
	// 회원 이름
	private String name;
	//회원 전화번호
	private String phone;
	
	// 관리자 구분 0:회원 1:관리자
	private String adminflag;
	
	// 환원 상태 0:가입 1:탈퇴
	private String member_stat;
	
	// 권한
	private List<AuthVo> auths;

}
