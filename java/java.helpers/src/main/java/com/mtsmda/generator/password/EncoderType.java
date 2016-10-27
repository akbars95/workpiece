package com.mtsmda.generator.password;

/**
 * Created by dminzat on 10/22/2016.
 */
public enum EncoderType {

    REVERSE("R159e", 19, 8),
    TO_UPPER_CASE("U753c", 36, 2),
    TO_LOWER_CASE("L014c", 63, 46),
    PHONE_TYPE_CUSTOM("P097t", 35, 61);

    private String code;
    private Integer beginCountLetters;
    private Integer endCountLetters;

    EncoderType(String code, Integer beginCountLetters, Integer endCountLetters) {
        this.code = code;
        this.beginCountLetters = beginCountLetters;
        this.endCountLetters = endCountLetters;
    }

    public String getCode() {
        return code;
    }

    public Integer getBeginCountLetters() {
        return beginCountLetters;
    }

    public Integer getEndCountLetters() {
        return endCountLetters;
    }
}