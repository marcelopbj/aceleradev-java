package br.com.codenation;

import static br.com.codenation.StatisticUtil.mode;

public class TesteMain {

    public static void main(String[] args) {
        int media = mode(new int[]{1,2,2,2,2,3,3,4,4,4,5,5,6,7,8});
        System.out.println("Moda: " + media);
    }
}
