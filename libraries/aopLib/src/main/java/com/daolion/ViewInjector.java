package com.daolion;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-11-29
       Time     :  14:26
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public interface ViewInjector<T> {

    void inject(T object);
}
