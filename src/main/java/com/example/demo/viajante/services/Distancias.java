package com.example.demo.viajante.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Distancias {
    double[][] distancias;
    int tamanio;

    public Distancias(int tam) {
        this.tamanio = tam;
        this.distancias = new double[tam][tam];
    }

    public void calcularDistancias(List<List<Integer>> ciudades){
        for (int i=0; i<this.tamanio; i++){
            for (int j=0; j<this.tamanio; j++){
                if (i==j){
                    distancias[i][j]= 0.0;
                } else {
                    double absX = Math.abs(ciudades.get(i).get(0)-ciudades.get(j).get(0));
                    double absY = Math.abs(ciudades.get(i).get(1)-ciudades.get(j).get(1));
                    double distancia = Math.sqrt(Math.pow(absX,2)+Math.pow(absY,2));
                    double distanciaRedondeada = new BigDecimal(distancia)
                            .setScale(2, RoundingMode.HALF_UP)
                            .doubleValue();
                    distancias[i][j] = distanciaRedondeada;
                }
            }
        }
    }

    public double[][] getDistancias() {
        return distancias;
    }

    public void setDistancias(double[][] distancias) {
        this.distancias = distancias;
    }
}
