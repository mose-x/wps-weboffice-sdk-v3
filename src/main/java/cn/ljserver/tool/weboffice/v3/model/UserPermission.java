package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserPermission {
    @NonNull
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("read")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean read;

    @JsonProperty("update")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean update;

    @JsonProperty("download")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean download;

    @JsonProperty("rename")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean rename;

    @JsonProperty("history")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean history;

    @JsonProperty("copy")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean copy;

    @JsonProperty("print")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean print;

    @JsonProperty("saveas")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean saveAs;

    @JsonProperty("comment")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private boolean comment;
}
