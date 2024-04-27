package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * 时间反序列化
 */
class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    private static final ZoneOffset OFFSET = ZoneId.systemDefault().getRules().getOffset(Instant.EPOCH);

    public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        final long seconds = parser.getLongValue();
        return LocalDateTime.ofEpochSecond(seconds, 0, OFFSET);
    }
}
