package io.nexgrid.bizcoretemplate.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

	// 성공 응답 목록
	SUCCESS("0000", "성공적으로 처리되었습니다."),


	// 실패 응답 목록
	MEMBER_NOT_FOUND("1000", "존재하지 않는 사용자입니다."),
	UNAUTHORIZED("1001", "인증되지 않은 사용자입니다."),
	ACCESS_DENIED("1002", "접근 권한이 없습니다."),
	AUTHENTICATION_FAILED("1003", "ID 또는 PW가 옳지 않습니다."),
	DUPLICATE_LOGIN_ID("1004", "이미 존재하는 유저 ID 입니다."),
	NOT_VALID_PARAMETER("1005", "파라미터 값이 부적절합니다."),
	PARAMETER_MISSING("1006", "필수 파라미터가 누락되었습니다."),
	FAIL("9999", "정의되지 않은 오류입니다.");

	private String code;
	private String message;

}