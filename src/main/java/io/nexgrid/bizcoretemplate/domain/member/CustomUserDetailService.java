package io.nexgrid.bizcoretemplate.domain.member;

import io.nexgrid.bizcoretemplate.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Autowired
    public CustomUserDetailService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            Member userData = memberRepository.findByEmail(email);
            System.out.println("######################### Login UserInfo : "+userData.toString());

            if (userData != null) {
                return new CustomUserDetails(userData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
