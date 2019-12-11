package la.niub.abcapi.servicecompre.config.enums;

import java.util.Calendar;

public enum WechatPeriodEnum {

    W1(Calendar.WEEK_OF_YEAR,-1),
    M1(Calendar.MONTH,-1),
    M3(Calendar.MONTH,-3),
    ALL(Calendar.YEAR,-100),
    ;

    private int field;

    private int amount;

    WechatPeriodEnum(int field, int amount) {
        this.field = field;
        this.amount = amount;
    }

    public int getField() {
        return field;
    }

    public int getAmount() {
        return amount;
    }
}
