public class Polynomial {
	double [] polynomial;


public Polynomial() {
	double [] zeroArray = {0};
	polynomial = zeroArray;
}

public Polynomial(double [] arrayPoly) {
	polynomial = arrayPoly;
}

public Polynomial add(Polynomial poly) {
	if (polynomial.length >= poly.polynomial.length) {
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
	}
	
}

public double evaluate(double num) {
	double value = polynomial[0];
	for(int i = 1; i< polynomial.length; i++) {
		value += polynomial[i] * (Math.pow(num, i));
	}
	return value;
}

public boolean hasRoot(double root) {
	if (evaluate(root) == 0) {
		return true;
	}
	return false;
	}
}