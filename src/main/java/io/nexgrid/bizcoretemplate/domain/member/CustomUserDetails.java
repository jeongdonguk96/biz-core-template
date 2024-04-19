package io.nexgrid.bizcoretemplate.domain.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 권한 확인
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                System.out.println("### Member get Role : "+member.getRole().toString());
                return "ROLE_"+member.getRole(); // security 인터페이스에 ROLE_ 접두사가 붙어있어 맞춰줌
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 만료 체크
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 잠김 체크
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
