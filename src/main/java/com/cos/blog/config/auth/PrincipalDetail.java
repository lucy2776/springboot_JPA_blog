package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
// UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails{ // 추상 메서드 오버라이딩
	
	private User user; // 컴포지션 : 객체를 품고있음
	
	
	public PrincipalDetail(User user) {
		this.user=user;
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}
	
	@Override
	public String getPassword() {

		return user.getPassword();
	}

	// 계정이 만료되지 않았는지 리턴 (true : 만료x)
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}
	
	// 비밀번호가 만료되지 않았는지 리턴 (true : 만료x)
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	// 계정이 잠겨있지 않았는지 리턴 (true : 잠김x) 
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	// 계정이 활성화(사용가능)인지 리턴 (true : 활성화)
	@Override
	public boolean isEnabled() {
		
		return true;
	}

	// 계정이 갖고있는 권한 목록 리턴 (권한이 여러 개 있을 수 있어서 루프를 돌아야함(일단 한 개))
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		// ArrayList = Collection 타입
		
//		collectors.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//
//				return "ROLE_"+user.getRole(); //ROLE_USER ; 프리픽스 필수 설정
//			}
//		
//		});
		
		// 람다식 표현
		collectors.add(()->{
			
			return "ROLE_"+user.getRole();
		});
		
		return collectors;
	}

	
}
