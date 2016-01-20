package hw8.taxi.util;

/**
 * Created by Andrew on 15.10.2015.
 */
public class Properties {
    private int loginAttemptsLimit;
    private int passwordTermDays;
    private int portionSize;

    public Properties() {
    }

    public int getLoginAttemptsLimit() {
        return loginAttemptsLimit;
    }

    public void setLoginAttemptsLimit(int loginAttemptsLimit) {
        this.loginAttemptsLimit = loginAttemptsLimit;
    }

    public int getPasswordTermDays() {
        return passwordTermDays;
    }

    public void setPasswordTermDays(int passwordTermDays) {
        this.passwordTermDays = passwordTermDays;
    }

    public int getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(int portionSize) {
        this.portionSize = portionSize;
    }
}
