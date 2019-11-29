package com.daolion;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-11-29
       Time     :  11:06
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class BindTools {

    private static String INJECTOR_END = "_ViewInjector";

    public static ViewInjector bind(Object object) {
        String proxyClassName = object.getClass().getName() + INJECTOR_END;
        Class<?> proxyClass = null;
        try {
            proxyClass = Class.forName(proxyClassName);
            ViewInjector viewInjector = (ViewInjector) proxyClass.newInstance();
            viewInjector.inject(object);
            return (ViewInjector) proxyClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
