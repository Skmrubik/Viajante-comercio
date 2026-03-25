package com.example.demo.viajante.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Camino {

    List<Integer> recorrido;
    Distancias distancias;

    public Camino(int tam, Distancias distancias) {
        this.recorrido = new ArrayList<>();
        for (int i = 0; i < tam; i++) {
            this.recorrido.add(i);
        }
        Collections.shuffle(this.recorrido);
        this.distancias = distancias;
    }

    public double distancia(){
        double distanciaRecorrida = 0.0;
        int tam = recorrido.size();
        for (int i=0;i<tam; i++){
            if (i==tam-1){
                distanciaRecorrida+=distancias.getDistancias()[recorrido.get(i)][recorrido.get(0)];
            } else {
                int origen = recorrido.get(i);
                int destino = recorrido.get(i+1);
                distanciaRecorrida+=distancias.getDistancias()[origen][destino];
            }
        }
        return distanciaRecorrida;
    }
}
