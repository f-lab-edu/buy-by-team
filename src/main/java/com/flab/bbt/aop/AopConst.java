package com.flab.bbt.aop;

public class AopConst {

    public static final String LOGIN_POINTCUT = "@annotation(com.flab.bbt.aop.LoginCheck) "
        + "&& execution(* com.flab.bbt..*Controller.*(..))";
}
