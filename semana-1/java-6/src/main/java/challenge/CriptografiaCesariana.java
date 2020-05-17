package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        validacao(texto);
        String criptografado = "";
        texto=texto.toLowerCase();
        for (int i=0; i<texto.length(); i++) {
            if(texto.charAt(i)>=97 & texto.charAt(i)<=119)
                criptografado = criptografado + (char) (texto.charAt(i)+3);

            else if(texto.charAt(i)>119 & texto.charAt(i)<=122)
                criptografado = criptografado + (char) (texto.charAt(i)-23);
            else
                criptografado = criptografado + (char) (texto.charAt(i));
        }
        return criptografado;
    }

    @Override
    public String descriptografar(String texto) {
        validacao(texto);
        String descriptografado = "";
        texto=texto.toLowerCase();
        for (int i=0; i<texto.length(); i++) {
            if(texto.charAt(i)>=100 & texto.charAt(i)<=122)
                descriptografado = descriptografado + (char) (texto.charAt(i)-3);

            else if(texto.charAt(i)>=97 & texto.charAt(i)<100)
                descriptografado = descriptografado + (char) (texto.charAt(i)+23);
            else
                descriptografado = descriptografado + (char) (texto.charAt(i));
        }
        return descriptografado;
    }
    public void validacao (String texto){
        if (texto == null)
            throw new NullPointerException();
        else if (texto.isEmpty())
            throw new IllegalArgumentException();
    }
}
