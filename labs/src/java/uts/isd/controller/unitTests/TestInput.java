/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.unitTests;

import java.util.Scanner;

/**
 * 
 * @author super
 * 
 * some utility methods
 */
public class TestInput {
    
    private static Scanner in = new Scanner(System.in);
    
    public static String nextLine(){
        return in.nextLine();
    }
    
    public static String askString(String s){
        System.out.print(s);
        return in.nextLine();
    }
    
    public static int askInt(String s){
        System.out.print(s);
        int i = in.nextInt();
        in.nextLine();
        return i;
        
    }
    
    public static long askLong(String s) {
        System.out.print(s);
        long l = in.nextLong();
        in.nextLine();
        return l;
    }
    
}
