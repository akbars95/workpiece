package com.mtsmda.object.request;

import com.mtsmda.pattern.Patterns;
import com.mtsmda.validation.structure.constraint.CheckLocalDateTime;
import com.mtsmda.validation.structure.constraint.DateEnum;
import com.mtsmda.validation.structure.constraint.IsNotNull;
import com.mtsmda.validation.structure.constraint.PasswordEquals;
import com.mtsmda.validation.structure.sequence.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

/**
 * Created by MTSMDA on 30.08.2016.
 */
@PasswordEquals(className = RegistrationReqObj.class, properties = {"accountPassword", "accountPassword2"}, groups = FourthOrder.class, message = "password and confirm password is not equals!")
public class RegistrationReqObj {

    @NotNull(groups = Default.class)
    @Size(min = 7, max = 50, groups = FirstOrder.class)
    @Pattern(regexp = Patterns.USERNAME, groups = SecondOrder.class)
    private String accountUsername;

    @NotNull(groups = Default.class)
    @Size(min = 5, max = 50, groups = FirstOrder.class)
    @Pattern(regexp = Patterns.PASSWORD, groups = SecondOrder.class)
    private String accountPassword;

    @NotNull(groups = Default.class)
    @Size(min = 5, max = 50, groups = FirstOrder.class)
    @Pattern(regexp = Patterns.PASSWORD, groups = SecondOrder.class)
    private String accountPassword2;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 75, groups = FirstOrder.class)
    @Pattern(regexp = Patterns.FIRST_LAST_MIDDLE_NAME, groups = SecondOrder.class)
    private String userFirstName;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 75, groups = FirstOrder.class)
    @Pattern(regexp = Patterns.FIRST_LAST_MIDDLE_NAME, groups = SecondOrder.class)
    private String userLastName;

    @IsNotNull(min = 1, max = 75, groups = Default.class, message = "Size should be Min: 1 and Max: 75 symbols")
    @Pattern(regexp = Patterns.FIRST_LAST_MIDDLE_NAME, groups = FirstOrder.class)
    @IsNotNull(min = 1, max = 75, groups = FifthOrder.class, message = "Size should be Min: 1 and Max: 75 symbols")
    @IsNotNull(min = 1, max = 75, groups = SixthOrder.class, message = "Size should be Min: 1 and Max: 75 symbols")
    private String userMiddleName;

    @NotNull(groups = Default.class)
    @Size(min = 1, max = 1, groups = FirstOrder.class)
    @Pattern(regexp = Patterns.GENDER, groups = FirstOrder.class)
    private String userGender;

    @NotNull(groups = Default.class)
    @CheckLocalDateTime(beginTime = false, dateType = DateEnum.LOCAL_DATE, datePeriod = DateEnum.PAST, groups = FirstOrder.class)
    private String userDateOfBirth;

    @NotNull(groups = Default.class)
    @Size(min = 5, max = 75, groups = FirstOrder.class)
    @Email
    private String userEmail;

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountPassword2() {
        return accountPassword2;
    }

    public void setAccountPassword2(String accountPassword2) {
        this.accountPassword2 = accountPassword2;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "RegistrationReqObj{" +
                "accountUsername='" + accountUsername + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountPassword2='" + accountPassword2 + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userMiddleName='" + userMiddleName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userDateOfBirth='" + userDateOfBirth + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

}