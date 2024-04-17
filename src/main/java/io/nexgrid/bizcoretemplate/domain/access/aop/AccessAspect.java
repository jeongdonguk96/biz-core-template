package io.nexgrid.bizcoretemplate.domain.access.aop;

import io.nexgrid.bizcoretemplate.domain.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AccessAspect {

    private final HttpServletRequest request;

//    @Before("@annotation(io.nexgrid.bizcoretemplate.domain.access.aop.AccessTrace)")
    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void beforeAccess(JoinPoint joinPoint) throws UnknownHostException {
        String requestUri = request.getRequestURI();
        String accessor = getAccessorUsername();
        String accessorIp = request.getRemoteAddr();

        // Ipv6인 경우 Ipv4로 변환한다.
        if (InetAddress.getByName(accessorIp) instanceof Inet6Address) {
            InetAddress inetAddress = InetAddress.getByName(accessorIp);
            byte[] ipv4Bytes = inetAddress.getAddress();

            accessorIp = ((ipv4Bytes[0] & 0xFF) + "." +
                    (ipv4Bytes[1] & 0xFF) + "." +
                    (ipv4Bytes[2] & 0xFF) + "." +
                    (ipv4Bytes[3] & 0xFF));
        }

        log.info("Accessing URI: {}", requestUri);
        log.info("Accessing accessor: {}", accessor);
        log.info("Accessing accessorIp: {}", accessorIp);


    }


    // 요청자의 아이디를 가져온다.
    private String getAccessorUsername() {
        HttpSession session = request.getSession();

        if (session.getAttribute("member") == null) {
            return null;

        } else {
            Member member = (Member) session.getAttribute("member");
            return member.getUsername();
        }
    }

}

public class Access {
    private Long id;
    private String accessor = null;
    private String accessorIp;
    private String accessResource;
    private String accessYear;
    private String accessMonth;
    private String accessDay;
    private String accessHour;
    private LocalDateTime accessTime;
}