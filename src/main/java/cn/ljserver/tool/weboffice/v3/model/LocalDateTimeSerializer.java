package cn.ljserver.tool.weboffice.v3.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * 时间序列化
 */
class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {
    private static final ZoneOffset OFFSET = ZoneId.systemDefault().getRules().getOffset(Instant.EPOCH);

    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeNumber(dateTime.toEpochSecond(OFFSET));
    }
}
