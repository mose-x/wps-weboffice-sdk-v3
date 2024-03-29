package cn.ljserver.tool.weboffice.v3.exception;

public abstract class ProviderException extends RuntimeException {
    public ProviderException(String message) {
        super(message);
    }
    public abstract int getCode();
}
