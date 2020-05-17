package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioFibonacci {

    public ArrayList<Integer> exibirFibonacci(){

        ArrayList<Integer> listFibonacci = new ArrayList<>();
        int n1 = 1;
        int n2 = 1;
        listFibonacci.set(0, n1);
        listFibonacci.set(1, n2);
        int positon = 2;
        while (n2 <= 350){
            n2=n1+n2;
            n1=n2;
            listFibonacci.set(positon, n2);
            System.out.println(n2);
        }
        return listFibonacci;
    }
}
