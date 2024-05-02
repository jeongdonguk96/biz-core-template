package io.nexgrid.bizcoretemplate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResultDto<T> {
    private String resultCode;
    private String resultMsg;
    private T resultData;

    public ResultDto(final String resultCode, final String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = null;
    }

    public static <T> ResultDto<T> resultSet(final String resultCode, final String resultMsg) {
        return resultSet(resultCode, resultMsg, null);
    }

    public static <T> ResultDto<T> resultSet(final String resultCode, final String resultMsg, final T resultData) {
        return ResultDto.<T>builder()
                .resultCode(resultCode)
                .resultMsg(resultMsg)
                .resultData(resultData)
                .build();
    }
}
