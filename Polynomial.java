import java.util.*;
import java.io.*;

public class Polynomial {
	double [] coeff;
	int [] expo;


public Polynomial() {
	coeff = null;
	expo = null;
}

public Polynomial(double [] coeff, int [] expo) {
	this.coeff = coeff;
	this.expo = expo;
}

public Polynomial(File file) throws FileNotFoundException{
	Scanner sc = new Scanner(file);
	String line = sc.nextLine();
	line = line.replace("-", "+-");
	String [] splitline= line.split("\\+");
	int maxL = splitline.length;
	
	double [] retCoeff = new double[maxL];
	int [] retExpo = new int[maxL];
	int count = 0;
	for(String i: splitline) {
		String [] temp = i.split("x");
		if(temp.length==1) {
			retCoeff[count] = Double.parseDouble(temp[0]);
			retExpo[count] = 0;
			count++;
		}
		else{
			retCoeff[count] = Double.parseDouble(temp[0]);
			retExpo[count] = Integer.parseInt(temp[1]);
			count++;
		}
	}
	sc.close();

	this.coeff=retCoeff;
	this.expo=retExpo;
	
}
public Polynomial add(Polynomial poly) {
/*	if (polynomial.length >= poly.polynomial.length) {
		for(int i = 0; i<poly.polynomial.length; i++) {
			polynomial[i] += poly.polynomial[i];
		}
		return this;
	}
	else{
		for(int i = 0; i<polynomial.length; i++) {
			poly.polynomial[i] += polynomial[i];
		}
		return poly;
	}*/
	
	int maxL = expo.length + poly.expo.length;
	double [] newCoeff = new double[maxL];
	int [] newExpo = new int[maxL];
	int count=0;
			
	for(int i: expo) {
		newExpo[count] = i;
		count++;
	}
	for(int i: poly.expo) {
		newExpo[count] = i;
		count++;
	}
	count = 0;
	// You have added all exponents into an array, now go add the coefficients :)
	
	for(int i: newExpo) {
		if(existNum(expo,i) && existNum(poly.expo, i)) {
			int i1=indexRet(expo,i);
			int i2=indexRet(poly.expo,i);
			newCoeff[count] = coeff[i1] + poly.coeff[i2];
			count++;
		}
		else if(existNum(expo,i)) {
			int i1=indexRet(expo,i);
			newCoeff[count] = coeff[i1];
			count++;
		}
		else {
			int i1=indexRet(poly.expo,i);
			newCoeff[count]=poly.coeff[i1];
			count++;
		}
	}
	count = 0;
	//Remove 0 coefficients.
	for(double i: newCoeff) {
		if (i!=0) {
			count++;
		}
	}
	double [] retCoeff = new double[count];
	int [] retExpo = new int[count];
	count = 0;
	for(int i =0; i<newExpo.length; i++) {
		if(newCoeff[i]!=0) {
			retExpo[count] = newExpo[i];
			retCoeff[count] = newCoeff[i];
			count++;
		}
	}//Remove redundant:)
	for(int i =0; i<retExpo.length; i++) {
		if(retExpo[i] != -1) {
			for(int j = i+1; j<retExpo.length; j++) {
				if(retExpo[i] == retExpo[j]) {
					retExpo[j] = -1;
				}
			}
		}
	}
	/*for(int i =0; i<retExpo.length; i++) {
		System.out.print(retCoeff[i] + " ");
	}
	System.out.println();
	for(int i =0; i<retExpo.length; i++) {
		System.out.print(retExpo[i] + " ");
	}
	System.out.println();*/
	
	int newMax = 0;
	for(int i: retExpo) {
		if(i!=-1) {
			newMax++;
		}
	}
	double [] newRetCoeff = new double[newMax];
	int [] newRetExpo = new int[newMax];
	count = 0;
	
	for(int i = 0; i<retExpo.length; i++) {
		if(retExpo[i]!= -1) {
			newRetCoeff[count] = retCoeff[i];
			newRetExpo[count] = retExpo[i];
			count++;
	
		}

	}
	
	return new Polynomial(newRetCoeff, newRetExpo);
	//return new Polynomial(retCoeff, retExpo);
	

}
public void display() {
	for(int i =0; i<this.coeff.length; i++) {
		System.out.print(coeff[i] + " ");
	}
	System.out.println();
	for(int i =0; i<this.coeff.length; i++) {
		System.out.print(expo[i] + " ");
	}
	System.out.println();
}
public Polynomial multiply(Polynomial poly) {
	int maxL = poly.expo.length * this.expo.length;
	double [] tempCoeff = new double[maxL];
	int [] tempExpo = new int[maxL];
	int count = 0;
	
	//First add all exponents (even redundent ones), then iterate through all of them while multiplying the new coefficients, 
	// Then every duplicate expo u see, change it's value to -1 or smth, to symbolize it is a duplicate, then recreate a new one.
	// Then reiterate the new one to find all coefficients with 0 to create a newer one, return newer one.
	for(int i =0; i<this.expo.length; i++) {
		for(int j =0; j<poly.expo.length; j++) {
			tempCoeff[count]= coeff[i]*poly.coeff[j];
			tempExpo[count] = expo[i] + poly.expo[j];
			count++;
		} 
	}
	
	for(int i =0; i<tempExpo.length; i++) {
		if(tempExpo[i]!= -1) {
			for(int j=i+1; j<tempExpo.length; j++) {
				if(tempExpo[i] == tempExpo[j]) {
					tempCoeff[i] += tempCoeff[j];
					tempExpo[j] = -1;
				}
			}
		}
	}
	count = 0;
	for(int i =0; i<tempExpo.length; i++) {
		if(tempCoeff[i]==0) {
			tempExpo[i] = -1;
		}
	}
	
	for(int i = 0; i<tempExpo.length; i++) {
		if(tempExpo[i]!= -1) {
			count++;
		}
	}
	double [] retCoeff = new double[count];
	int [] retExpo = new int[count];
	count = 0;
	
	for(int i =0; i<tempExpo.length; i++) {
		if(tempExpo[i] != -1) {
			retCoeff[count] = tempCoeff[i];
			retExpo[count] = tempExpo[i];
			count++;
		}
	}
	return new Polynomial(retCoeff, retExpo);


}

public void saveToFile(String filename) throws Exception {
	String retStr="";
	for(int i =0; i<this.coeff.length; i++) {
		retStr+= coeff[i];
		if(expo[i]!=0) {
			retStr+="x" + expo[i];
		}
		retStr+="+";

	}
	if(retStr.endsWith("+"))
		retStr = retStr.substring(0,retStr.length()-1);
	FileWriter writer = new FileWriter(new File(filename));
	writer.write(retStr);
	writer.close();
}


public double evaluate(double num) {
	double value = 0;
	for(int i = 0; i< expo.length; i++) {
		value += coeff[i] * (Math.pow(num, expo[i]));
	}
	return value;
}

public boolean hasRoot(double root) {
	return evaluate(root) == 0;
}

//Helper Functions
public boolean existNum(int [] arr, int value) {
	for(int i: arr) {
		if (i==value)
			return true;
	}
	return false;
	}
public boolean existNum(double [] arr, double value) {
	for(double i: arr) {
		if (i==value)
			return true;
	}
	return false;
	}

public int indexRet(double [] arr, double value) {
		for(int i =0; i<arr.length; i++) {
			if (arr[i] == value) {
				return i;
			}
		}
		return -1;
	}
public int indexRet(int [] arr, int value) {
	for(int i =0; i<arr.length; i++) {
		if (arr[i] == value) {
			return i;
		}
	}
	return -1;
}
}