import java.util.*;
import java.io.*;
public class Driver {
public static void main(String [] args) {
/*Polynomial p = new Polynomial();
System.out.println(p.evaluate(3));
double [] c1 = {6,0,0,5};
Polynomial p1 = new Polynomial(c1);
double [] c2 = {0,-2,0,0,-9};
Polynomial p2 = new Polynomial(c2);
Polynomial s = p1.add(p2);
System.out.println("s(0.1) = " + s.evaluate(0.1));
if(s.hasRoot(1))
System.out.println("1 is a root of s");
else
System.out.println("1 is not a root of s");
}*/
double [] co1= {2,4,1};
int [] expo1 = {1,3,0};
Polynomial p1=new Polynomial(co1, expo1);

double [] co2= {2,3};
int [] expo2 = {2,1};
Polynomial p2 = new Polynomial(co2,expo2);
File file = new File("test.txt");
Polynomial p5=null;
try {
	p5= new Polynomial(file);
}
catch(FileNotFoundException e) {
	System.out.println("File DNE");
}
Polynomial p3= p1.add(p2);
p3.display();

Polynomial p4=p1.multiply(p2);
p4.display();
if(p5!=null)
	p5.display();
	}
}