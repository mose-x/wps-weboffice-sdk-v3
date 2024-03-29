package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    @NonNull
    @JsonProperty("id")
    private String id;

    @NonNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private int version;

    @JsonProperty("size")
    private long size;

    @NonNull
    @JsonProperty("create_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @NonNull
    @JsonProperty("modify_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyTime;

    @NonNull
    @JsonProperty("creator_id")
    private String creatorId;

    @NonNull
    @JsonProperty("modifier_id")
    private String modifierId;
}
