package com.cwjcsu.learning.笔试._1;
/**
 * 
 * @author atlas
 * @date 2013-4-23
 */
class Father{
    private String name="FATHER";
    public Father(){
        whoAmI();
        tellName(name);
    }
    public void whoAmI(){
        System.out.println("Father says, I am " + name);
    }
    public void tellName(String name){
        System.out.println("Father's name is " + name);
    }
}

class Son extends Father{
    private String name="SON";
    public Son(){
        whoAmI();
        tellName(name);
    }
    public void whoAmI(){
        System.out.println("Son says, I am " + name);
    }
    public void tellName(String name){
        System.out.println("Son's name is " + name);
    }
    public static void main(String[] args) {
    	Father who = new Son();
	}
}

