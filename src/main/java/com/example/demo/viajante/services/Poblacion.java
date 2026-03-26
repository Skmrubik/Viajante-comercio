package com.example.demo.viajante.services;

import java.util.*;

public class Poblacion {
    Map<Double, Camino> poblacion;
    Distancias distancias;

    public Poblacion(int tam, Distancias distancias) {
        this.poblacion = new TreeMap<>((d1, d2) -> {
            int res = d1.compareTo(d2);
            return res == 0 ? 1 : res; // Si son iguales, retorna 1 (lo mueve a la derecha)
        });
        this.distancias = distancias;
        for (int i=0; i<100; i++){
            Camino camino1 = new Camino(tam, distancias);
            poblacion.put(camino1.distancia(), camino1);
        }
    }

    public void getElite(){
        Map.Entry<Double, Camino> primeraEntrada = ((TreeMap<Double, Camino>) poblacion).firstEntry();
        System.out.println("Distancia primero "+ primeraEntrada.getKey());
    }

    public void getEliteCamino(){
        Map.Entry<Double, Camino> primeraEntrada = ((TreeMap<Double, Camino>) poblacion).firstEntry();
        Camino camino = primeraEntrada.getValue();
        for (int i=0; i<camino.getRecorrido().size(); i++){
            System.out.print(camino.getRecorrido().get(i)+ " ");
        }
    }

    public void nuevaGeneracion(){
        TreeMap<Double, Camino> copiaPoblacion = new TreeMap<>((d1, d2) -> {
            int res = d1.compareTo(d2);
            return res == 0 ? 1 : res; // Si son iguales, retorna 1 (lo mueve a la derecha)
        });
        //copiaPoblacion.putAll(poblacion);
        for (Map.Entry<Double, Camino> entrada : poblacion.entrySet()) {
            Double distancia = entrada.getKey();
            Camino caminoAux = entrada.getValue();
            copiaPoblacion.put(distancia, caminoAux);
        }
        poblacion.clear();
        int contador = 0;
        Iterator<Map.Entry<Double, Camino>> it = copiaPoblacion.entrySet().iterator();

        while (it.hasNext()) {
            // Sacamos el primer elemento del par
            Map.Entry<Double, Camino> primero = it.next();
            Map.Entry<Double, Camino> segundo = it.next();
            if (primero.getValue().getRecorrido().size()<10){
                System.out.println("Aquí ha fallado el 1");
            }
            if (segundo.getValue().getRecorrido().size()<10){
                System.out.println("Aquí ha fallado el 2");
            }
            if (contador<47){
                Camino hijo1 = obtenerHijo(primero.getValue(), segundo.getValue());
                poblacion.put(hijo1.distancia(), hijo1);
                Camino hijo2 = obtenerHijo(segundo.getValue(), primero.getValue());
                poblacion.put(hijo2.distancia(), hijo2);
            }
            if (contador<3){
                poblacion.put(primero.getKey(), primero.getValue());
                poblacion.put(segundo.getKey(), segundo.getValue());
            }
            contador++;
        }
    }

    public Camino obtenerHijo(Camino c1, Camino c2){
        List<Integer> recorrido1 = new ArrayList<>(c1.getRecorrido());
        List<Integer> recorrido2 = new ArrayList<>(c2.getRecorrido());
        int tam = recorrido1.size()/3;
        List<Integer> resultado = new ArrayList<>();
        for(int i=0; i<tam; i++){
            resultado.add(recorrido1.get(i));
            recorrido2.remove(recorrido1.get(i));
        }
        for (int i=0; i<recorrido2.size();i++){
            resultado.add(recorrido2.get(i));
        }
        Camino camino = new Camino(resultado, distancias);
        return camino;
    }
}
