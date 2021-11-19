/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class GenerarDatosRandom {

    List<String> nombresDoctor;

    public GenerarDatosRandom() {
        iniListaRepartidores();
    }

    private void iniListaRepartidores() {
        nombresDoctor = new ArrayList<>();
        nombresDoctor.add("Armando Arjona");
        nombresDoctor.add("Fabio Melendez");
        nombresDoctor.add("Brenda Trigo");
        nombresDoctor.add("Mariana Ruano");
        nombresDoctor.add("Xavier Salvador");
        nombresDoctor.add("Georgina Belda");
        nombresDoctor.add("Jesica Velasco");
        nombresDoctor.add("Avelino Manzano");
        nombresDoctor.add("Luca Galvan");
        nombresDoctor.add("Arsenio Plaza");
        nombresDoctor.add("Ainara Ramos");
        nombresDoctor.add("Milagros Vegas");
    }

    public String generarNombreRepartidor() {
        int min = 0, max = nombresDoctor.size();
        Random random = new Random();
        int index = random.nextInt(max - min) + min;
        return nombresDoctor.get(index);
    }

    public String generarTiempoEstimado() {
        long minuto = 1000 * 60;
        int min = 5, max = 120;
        Random random = new Random();

        long tiempoEstimadoMs = (random.nextInt((max - min)) + min) * minuto;
        long ahoraMs = System.currentTimeMillis();
        tiempoEstimadoMs += ahoraMs +  TimeUnit.SECONDS.toMillis((TimeUnit.MILLISECONDS.toSeconds(ahoraMs)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ahoraMs)
        )) - 60);

        LocalDateTime fin = LocalDateTime.ofInstant(Instant.ofEpochMilli(tiempoEstimadoMs), ZoneId.systemDefault());
        LocalDateTime ahora = LocalDateTime.ofInstant(Instant.ofEpochMilli(ahoraMs), ZoneId.systemDefault());

        return format(Duration.between(ahora, fin));
    }

    protected String format(Duration duration) {
        long hours = duration.toHours();
        long mins = duration.minusHours(hours).toMinutes();
        long seconds = duration.getSeconds() % 60;

        return String.format("%02dh %02dm %02ds", hours, mins, seconds);
    }
}
