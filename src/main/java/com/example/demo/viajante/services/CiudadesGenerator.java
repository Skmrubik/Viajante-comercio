package com.example.demo.viajante.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CiudadesGenerator {

    public CiudadesGenerator() {
    }

    public void generar(int tam){
        Random r= new Random();
        List<List<Integer>> posiciones = new ArrayList<>();
        for (int i=0; i<tam; i++){
            List<Integer> posicion = new ArrayList<>();
            int r1 = r.nextInt(100);
            int r2 = r.nextInt(100);
            posicion.add(r1);
            posicion.add(r2);
            posiciones.add(posicion);
        }

        for (List<Integer> pos: posiciones){
            System.out.println(pos.get(0)+" "+pos.get(1));
        }
    }

    public List<List<Integer>> leerCiudades(){
        List<List<Integer>> ciudades = new ArrayList<>();
        String rutaArchivo = "C:\\Users\\skmru\\Documents\\demo.viajante\\ciudades.txt";

        try (Stream<String> lineas = Files.lines(Paths.get(rutaArchivo))) {
            lineas.forEach(linea -> {
                // Separamos por espacio, tabulación o coma (ajusta según tu archivo)
                String[] columnas = linea.split("\\s+");

                if (columnas.length >= 2) {
                    List<Integer> ciudad = new ArrayList<>();
                    Integer x = Integer.parseInt(columnas[0]);
                    Integer y = Integer.parseInt(columnas[1]);
                    ciudad.add(x);
                    ciudad.add(y);
                    ciudades.add(ciudad);
                }
            });
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return ciudades;
    }
}
