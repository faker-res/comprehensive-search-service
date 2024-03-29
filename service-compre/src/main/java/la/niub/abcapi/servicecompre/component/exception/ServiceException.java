package la.niub.abcapi.servicecompre.component.exception;

import la.niub.abcapi.servicecompre.config.code.ICodeConfig;

public class ServiceException extends Exception {
    private int code = 50000;

    public ServiceException() {
        super();
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ICodeConfig object) {
        super(object.getMessage());
        this.code = object.getCode();
    }

    public int getCode() {
        return code;
    }
}
