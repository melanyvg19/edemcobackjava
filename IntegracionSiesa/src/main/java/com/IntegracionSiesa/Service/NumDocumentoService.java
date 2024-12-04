package com.IntegracionSiesa.Service;

import java.io.*;

public class NumDocumentoService {

    private static final String COUNTER_FILE = "execution_counter.txt";
    private int counter;

    public NumDocumentoService() {
        this.counter = readCounterFromFile();
    }

    // Método que incrementa el contador y guarda el valor en el archivo
    public void incrementCounter() {
        counter++;
        writeCounterToFile();
    }

    // Método que lee el contador desde el archivo
    private int readCounterFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNTER_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            // Si hay algún error, asumimos que es la primera ejecución
            return 0;
        }
    }

    // Método que escribe el contador en el archivo
    private void writeCounterToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COUNTER_FILE))) {
            writer.write(String.valueOf(counter));
        } catch (IOException e) {
            System.err.println("Error al escribir el contador en el archivo: " + e.getMessage());
        }
    }

    public int getCounter() {
        return counter;
    }

}
