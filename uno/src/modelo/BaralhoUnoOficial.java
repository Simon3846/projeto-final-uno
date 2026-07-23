package modelo;

public class BaralhoUnoOficial extends Baralho{

    // o inicializar é um metodo abstrato então subclasse é que dicidi como ela funcionará

    @Override
    protected void inicializarBaralho(){
        String[] cores = {"Vermelho", "Amarelo", "Verde", "Azul"};
    
         for(String cor : cores){
         // No UNO, há uma carta "0" de cada cor
         cartas.add(new CartaNormal(cor, "0"));
        
         // Cartas de 1 a 9 (duas de cada cor)
            for(int i = 1;i <= 9;i++){

                cartas.add(new CartaNormal(cor, String.valueOf(i)));
                cartas.add(new CartaNormal(cor, String.valueOf(i)));
            }

        // Cartas de ação: Pular, Inverter e +2 (duas de cada por cor)
            for(int i = 0; i < 2; i++){
                cartas.add(new CartaAcao(cor, "Pular"));
                cartas.add(new CartaAcao(cor, "Inverter"));
                cartas.add(new CartaAcao(cor,"+2"));
            }
        // Em um passo futuro, adicionar os Coringas (Wild) aqui!
        
        }
    }

}
