package com.cwjcsu.learning.basic.generics;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author atlas
 * @date 2013-10-31
 */
public class Copy  {        
    public <A,B extends List<A>,C extends Map<A,B>> A method(A a, B b) {
        A something = null;
        return something;
    }

    public <A,B extends List<A>> A method2(A a, B b) {
        return this.<A,B,Map<A,B>>method(a,b);
    }
    
    public static <A,B extends List<A>,C extends Map<A,B>> A methodA(A a, B b) {
        A something = null;
        return something;
    }

    public static <A,B extends List<A>> A methodB(A a, B b) {
        return Copy.<A,B,Map<A,B>>methodA(a,b);
    }    
}
