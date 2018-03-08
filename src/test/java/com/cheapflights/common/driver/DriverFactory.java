package com.cheapflights.common.driver;

public class DriverFactory {
    public static AbstractWebDriver getDriverFromFactory(String type){
        DriverType driverType = DriverType.valueOf(type.toUpperCase());

        switch(driverType){
            case CHROME: return new NewChromeDriver();
            case FIREFOX: return new NewFirefoxDriver();
            default:throw new EnumConstantNotPresentException(DriverType.class, driverType.name());
        }
    }
}
