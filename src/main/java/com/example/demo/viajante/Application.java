package com.example.demo.viajante;

import com.example.demo.viajante.services.Camino;
import com.example.demo.viajante.services.CiudadesGenerator;
import com.example.demo.viajante.services.Distancias;
import com.example.demo.viajante.services.Poblacion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
        CiudadesGenerator ciudades = new CiudadesGenerator();
        int tam = 10;
        //ciudades.generar(tam);
        List<List<Integer>> ciudadesArray = ciudades.leerCiudades();
        Distancias distancias = new Distancias(tam);
        distancias.calcularDistancias(ciudadesArray);

        for (List<Integer> pos: ciudadesArray){
            System.out.println(pos.get(0)+" "+pos.get(1));
        }
        for (int i=0; i<tam; i++) {
            for (int j = 0; j < tam; j++) {
                System.out.print(distancias.getDistancias()[i][j]+ " ");
            }
            System.out.println("");
        }

        Camino camino = new Camino(tam, distancias);
        System.out.println("Distancia "+ camino.distancia());
        Poblacion poblacion = new Poblacion(tam, distancias);

        for (int i=0; i<100; i++){
            poblacion.nuevaGeneracion();
        }
        poblacion.getElite();
        poblacion.getEliteCamino();
        /*
        Camino camino1= new Camino(tam, distancias);
        Camino camino2= new Camino(tam, distancias);
        camino1.recorridoToString();
        camino2.recorridoToString();
        Camino result = poblacion.obtenerHijo(camino1, camino2);
        result.recorridoToString();*/

	}


}
