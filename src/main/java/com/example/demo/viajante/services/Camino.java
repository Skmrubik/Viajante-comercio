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

    public Camino(List<Integer> recorrido, Distancias distancias){
        this.recorrido = recorrido;
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

    public List<Integer> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(List<Integer> recorrido) {
        this.recorrido = recorrido;
    }

    public void recorridoToString(){
        for (int i=0; i< recorrido.size(); i++){
            System.out.print(recorrido.get(i)+" ");
        }
        System.out.println("");
    }
}
