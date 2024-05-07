package io.nexgrid.bizcoretemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseDto<T> {
    private String resultCode;
    private String resultMsg;
    private T resultData;

    public ResponseDto(final String resultCode, final String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = null;
    }

    public static <T> ResponseDto<T> resultSet(final String resultCode, final String resultMsg) {
        return resultSet(resultCode, resultMsg, null);
    }

    public static <T> ResponseDto<T> resultSet(final String resultCode, final String resultMsg, final T resultData) {
        return ResponseDto.<T>builder()
                .resultCode(resultCode)
                .resultMsg(resultMsg)
                .resultData(resultData)
                .build();
    }
}
