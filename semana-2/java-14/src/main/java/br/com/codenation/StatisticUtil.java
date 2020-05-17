package br.com.codenation;

import java.util.ArrayList;
import java.util.List;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum=0;
		for (int i=0; i<elements.length; i++){
			sum+=elements[i];
		}
		return sum/elements.length;
	}

	public static int mode(int[] elements) {

		int len[] = new int[elements.length];
		int cont=0;
		int aux;
		for(int i=0; i<elements.length;i++){
			cont=0;
			for (int x=0; x<elements.length; x++){
				if(elements[i] == elements[x]){
					cont++;
				}
			}
			len[i]=cont;
		}
		for(int i=1; i<len.length;i++){
			for (int x=i; x<len.length; x++){
				if(len[i-1] > len[x]){
					aux = elements[x];
					elements[x] = elements[i-1];
					elements[i-1] = aux;
					aux = len[x];
					len[x] = len[i-1];
					len[i-1]=aux;
				}
			}
		}

		return elements[len.length-1];
	}

	public static int median(int[] elements) {
		int aux;
		for(int i=1; i<elements.length;i++){
			for (int x=i; x<elements.length; x++){
				if(elements[i-1] > elements[x]){
					aux = elements[x];
					elements[x] = elements[i-1];
					elements[i-1] = aux;
				}
			}
		}
		if (elements.length%2 == 0){
			return (elements[elements.length/2] + elements[(elements.length/2)-1])/2;
		}else{
			return elements[elements.length/2];
		}
	}
}