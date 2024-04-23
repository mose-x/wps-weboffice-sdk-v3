package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件水印
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Watermark {
    @JsonProperty("type")
    private Type type;

    @JsonProperty("value")
    private String value;

    @JsonProperty("fill_style")
    private FillStyle fillStyle;

    @JsonProperty("font")
    private String font;

    @JsonProperty("rotate")
    private double rotate;

    @JsonProperty("horizontal")
    private int horizontal;

    @JsonProperty("vertical")
    private int vertical;

    public enum Type {
        NONE, TEXT;

        @JsonValue
        public int jsonValue() {
            return this.ordinal();
        }
    }

    @Builder
    public static class FillStyle {
        private int red;
        private int green;
        private int blue;
        private double alpha;

        @JsonValue
        public String jsonValue() {
            return String.format("rgba( %d, %d, %d, %f)", this.red, this.green, this.blue, this.alpha);
        }
    }
}
