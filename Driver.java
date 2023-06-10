import java.util.*;
import java.io.*;
public class Driver {
public static void main(String [] args) {


double [] co1= {-2,-4,-1};
int [] expo1 = {1,3,0};
Polynomial p1=new Polynomial(co1, expo1);

double [] co2= {2,3};
int [] expo2 = {2,1};
Polynomial p2 = new Polynomial(co2,expo2);
/*File file = new File("test.txt");
Polynomial p5=null;
try {
	p5= new Polynomial(file);
}
catch(FileNotFoundException e) {
	System.out.println("File DNE");
}*/
Polynomial p3= p1.add(p2);
p3.display();
double [] a = {-1,1,1,1};
int [] a1 = {3,2,0,1};

double [] b = {-1,1};
int [] b1= {1,0};

Polynomial p5=new Polynomial(a,a1);
Polynomial p6= new Polynomial(b,b1);

Polynomial p4=p5.multiply(p6);
//p4.display();
try {
	p4.saveToFile("test.txt");
}
catch(IOException e) {
	System.out.print("hi");
}
/*if(p5!=null)
	p5.display();
	}*/
}
}