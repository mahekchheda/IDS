/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ids;
import java.util.*;
/**
 *
 * @author Mahek Chheda
 */
public class Matching {
    boolean Matchingstring(String sub,String obj,int thresh)
    {
        int r=0,i=0;
        while(r<thresh&&i<sub.length())
        {
            if(sub.charAt(i)==obj.charAt(i))
            {
                r++;
            }
            else
            {
                r=0;
            }
            i++;
        }
        System.out.println(r);
        if(r==thresh)
            return true;
        else
            return false;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Matching m=new Matching();
        System.out.println("Enter two strings");
        String s1=sc.next();
        String s2=sc.next();
        if(m.Matchingstring(s1, s2, 4))
            System.out.println("True");
        else
            System.out.print("false");
    }
}
