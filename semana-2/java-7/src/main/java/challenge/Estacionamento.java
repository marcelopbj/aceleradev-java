package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    List<Carro> carros = new ArrayList<>();

    public void estacionar(Carro carro) {
        validarMotorista(carro);

        if (carrosEstacionados() < 10) {
            carros.add(carro);
        }
        else {
            for(Carro car : carros){
                if(car.getMotorista().getIdade()<55){
                    carros.remove(car);
                    carros.add(carro);
                }
                else{
                    throw new EstacionamentoException("O estacionamento está lotado");
                }
            }
        }
    }

    private void validarMotorista(Carro carro){
        if(carro.getMotorista() == null){
            throw new EstacionamentoException("É necessário ter um motorista");
        }
        if(carro.getMotorista().getIdade()<18){
            throw new EstacionamentoException("Idade não permitida");
        }
        if(carro.getMotorista().getPontos()>20){
            throw new EstacionamentoException("Carteira invalida");
        }
    }
    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
       return carros.contains(carro);
    }
}
