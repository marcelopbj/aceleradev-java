package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		if (salarioBase < 1039)
			return Math.round(0.0);
		else
			if (calcularInss(salarioBase) <= 3000)
				return Math.round(calcularInss(salarioBase));
			else
				return Math.round(calcularIrrf(calcularInss(salarioBase)));
	}

	private double calcularInss(double salarioBase) {
		if (salarioBase <= 1500)
			return  salarioBase*0.92;
		else if (salarioBase > 1500 & salarioBase <= 4000)
			return  salarioBase*0.91;
		else
			return salarioBase*0.89;
	}

	private double calcularIrrf(double salarioMenosInss){
		if (salarioMenosInss > 3000 && salarioMenosInss <= 6000)
			return salarioMenosInss*0.925;
		else
			return salarioMenosInss*0.85;
	}

}