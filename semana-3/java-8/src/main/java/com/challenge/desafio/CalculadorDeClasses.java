package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object object) {
        return calcula(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        return calcula(object, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return somar(object).subtract(subtrair(object));
    }

    private BigDecimal calcula (Object object, Class annotation){
        BigDecimal resultado = BigDecimal.ZERO;
        for (Field field : object.getClass().getDeclaredFields()){
            if (field.isAnnotationPresent(annotation) && field.getType().equals(BigDecimal.class)){
                field.setAccessible(true);
                try {
                    resultado = resultado.add((BigDecimal)field.get(object));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado;
    }
}
