package com.example.demo.viajante;

import com.example.demo.viajante.services.CiudadesGenerator;
import com.example.demo.viajante.services.Distancias;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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


	}


}
