package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {

		List<Integer> listFibonacci = new ArrayList<>();
		int n1 = 0;
		int n2 = 1;
		int n3 = 0;
		listFibonacci.add(n1);
		listFibonacci.add(n2);
        while (n3 <= 350){
            n3=n1+n2;
            listFibonacci.add(n3);
            System.out.println(n3);
            n1=n2;
            n2=n3;
        }
		return listFibonacci;
	}

	public static Boolean isFibonacci(Integer a) {
        int n1 = 0;
        int n2 = 1;
        int n3 = 0;
        Boolean out = null; 
        while (n3<=a){
            n3=n1+n2;
            if (n3 == a)
                out = true;
            else if (n3<a)
                out = false;
            n1=n2;
            n2=n3;
        }
        return out;

	}

}