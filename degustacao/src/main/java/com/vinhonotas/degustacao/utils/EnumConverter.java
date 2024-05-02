package com.vinhonotas.degustacao.utils;

import com.vinhonotas.degustacao.domain.enums.EnumCode;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnumConverter {

    public static <T extends Enum<T> & EnumCode> T fromString(String value, Class<T> enumType) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        for (T enumValue : enumType.getEnumConstants()) {
            if (enumValue.getCode().equals(value)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("No enum constant " + enumType.getSimpleName() + " with code " + value);
    }

    public static <T extends Enum<T>> String toString(T enumValue) {
        return ((EnumCode) enumValue).getCode();
    }

}
