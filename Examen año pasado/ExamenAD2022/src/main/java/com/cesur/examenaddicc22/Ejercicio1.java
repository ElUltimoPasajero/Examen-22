package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;

class Ejercicio1 {

    static void solucion() {
        estadisticasDeArchivo("pom.xml");
    }

    private static void estadisticasDeArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int caracteres = 0;
            int palabras = 0;
            int lineas = 0;

            String linea;
            while ((linea = br.readLine()) != null) {
                lineas++;
                caracteres += linea.length();
                palabras += contarPalabras(linea);
            }

            System.out.println("Número de caracteres: " + caracteres);
            System.out.println("Número de palabras: " + palabras);
            System.out.println("Número de líneas: " + lineas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int contarPalabras(String linea) {
        BreakIterator wordIterator = BreakIterator.getWordInstance();
        wordIterator.setText(linea);

        int count = 0;
        int start = wordIterator.first();
        int end = wordIterator.next();
        while (end != BreakIterator.DONE) {
            String word = linea.substring(start, end);
            if (!word.trim().isEmpty()) {
                count++;
            }
            start = end;
            end = wordIterator.next();
        }

        return count;
    }
}






/*
class Ejercicio1 {


    static void solucion() {
        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int caracteres = 0;
            int palabras = 0;
            int lineas = 0;

            String linea;
            while ((linea = br.readLine()) != null) {
                lineas++;
                caracteres += linea.length();
                palabras += contarPalabras(linea);
            }

            System.out.println("Número de caracteres: " + caracteres);
            System.out.println("Número de palabras: " + palabras);
            System.out.println("Número de líneas: " + lineas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int contarPalabras(String linea) {
        String[] palabras = linea.split("\\s+");
        return palabras.length;
    }
}*/