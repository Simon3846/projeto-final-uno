package modelo;

public class BaralhoConvencional extends Baralho{

    @Override
    protected void inicializarBaralho(){
        String[] naipes = {"Copas", "Ouros", "Paus", "Espadas"}; // Vermelho, Amarelo, Verde, Azul
    
        // Adicionando cartas de 1 a 10 para cada naipe
        for(String naipe: naipes){
                cartas.add(new CartaNormal(naipe, "1"));
            
                // Adicionando cartas de 2 a 10 para cada naipe
                for(int i = 2; i <= 10; i++){
                    cartas.add(new CartaNormal(naipe,String.valueOf(i)));
                }

            // Adicionando cartas de ação para cada naipe
            cartas.add(new CartaNormal(naipe, "Pular")); // valete 
            cartas.add(new CartaNormal(naipe, "Inverter")); // dama
            cartas.add(new CartaNormal(naipe, "+2")); // rei

        }
    }
}