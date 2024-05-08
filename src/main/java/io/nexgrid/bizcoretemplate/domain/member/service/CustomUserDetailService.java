package io.nexgrid.bizcoretemplate.domain.member.service;

import io.nexgrid.bizcoretemplate.domain.member.CustomUserDetails;
import io.nexgrid.bizcoretemplate.domain.member.Member;
import io.nexgrid.bizcoretemplate.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Member userData = memberRepository.findByUsername(username);
            if (userData != null) {
                log.info("### Login Success ▶ UserInfo : {}", userData.toString());
                return new CustomUserDetails(userData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
