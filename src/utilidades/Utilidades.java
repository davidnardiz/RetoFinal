package utilidades;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import jnafilechooser.api.JnaFileChooser;

public class Utilidades {

    public static int numeros_aleatorios(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static String seleccionarImagen(JDialog ventana) {
        File imagen = null;

        String rutaProyecto = System.getProperty("user.dir");
        File rutaExplorador = new File(rutaProyecto + "/src/imagenes/publicaciones/");

        JnaFileChooser ch = new JnaFileChooser(rutaExplorador);
        boolean seleccionado = ch.showOpenDialog(ventana);

        if (seleccionado) {
            imagen = ch.getSelectedFile();

            if (!imagen.getAbsolutePath().contains("\\RetoFinal\\src\\imagenes\\publicaciones")) {

                try {
                    File nuevaFoto = new File(imagen.getAbsolutePath());
                    BufferedImage image = ImageIO.read(nuevaFoto);

                    // Si la foto esta en vertical redimensionarla con unos valores
                    if (image.getHeight() > image.getWidth()) {
                        nuevaFoto = Utilidades.redimensionarImagenes(nuevaFoto, 356, 475);

                        // Si no redimensionarla con otrs
                    } else {
                        nuevaFoto = Utilidades.redimensionarImagenes(nuevaFoto, 475, 356);
                    }

                    // Ruta origina del archivo
                    Path rutaAntigua = Path.of(nuevaFoto.getAbsolutePath());

                    // Ruta donde vamos a pegar
                    Path rutaNueva = Path.of(rutaExplorador.getAbsolutePath());

                    // Copia la imagen al nuevo destino
                    Files.copy(rutaAntigua, rutaNueva.resolve(rutaAntigua.getFileName()),
                            StandardCopyOption.REPLACE_EXISTING);

                    nuevaFoto.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return imagen.getName();
        }
        return null;

    }
    
    public static String seleccionarIcono(JDialog ventana) {
        File imagen = null;

        String rutaProyecto = System.getProperty("user.dir");
        File rutaExplorador = new File(rutaProyecto + "/src/imagenes/iconos/");

        JnaFileChooser ch = new JnaFileChooser(rutaExplorador);
        boolean seleccionado = ch.showOpenDialog(ventana);

        if (seleccionado) {
            imagen = ch.getSelectedFile();

            if (!imagen.getAbsolutePath().contains("\\RetoFinal\\src\\imagenes\\iconos")) {

                try {
                    File nuevaFoto = new File(imagen.getAbsolutePath());
                    BufferedImage image = ImageIO.read(nuevaFoto);

                    // Si la foto esta en vertical redimensionarla con unos valores
                   
                        nuevaFoto = Utilidades.redimensionarImagenes(nuevaFoto, 64, 64);
                    

                    // Ruta origina del archivo
                    Path rutaAntigua = Path.of(nuevaFoto.getAbsolutePath());

                    // Ruta donde vamos a pegar
                    Path rutaNueva = Path.of(rutaExplorador.getAbsolutePath());

                    // Copia la imagen al nuevo destino
                    Files.copy(rutaAntigua, rutaNueva.resolve(rutaAntigua.getFileName()),
                            StandardCopyOption.REPLACE_EXISTING);

                    nuevaFoto.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return imagen.getName();
        }
        return null;

    }

    /*
    public static String exploradorArchivos(JDialog ventana) {
        String fotoSeleccionada = "";
        Path rutaAntigua;
        Path rutaNueva = null;
        // Crear el Explorador
        JFileChooser fileChooser = new JFileChooser();
        // Decir donde abrir
        String rutaProyecto = System.getProperty("user.dir");
        File rutaExplorador = new File(rutaProyecto + "/src/imagenes/publicaciones/");
        fileChooser.setCurrentDirectory(rutaExplorador);
        // Decir que solo puede abrir fotos
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        // Mostrar el cuadro de diálogo del selector de archivos
        int result = fileChooser.showOpenDialog(ventana);
        // Si se a elegido una foto
        if (result == JFileChooser.APPROVE_OPTION) {
            // Coger la ruta absoluta
            fotoSeleccionada = fileChooser.getSelectedFile().getPath();
            // Si no esta dentro de la carpeta imagenes la copiamos a esa carpeta
            if (!fotoSeleccionada.contains("\\RetoFinal\\src\\imagenes\\publicaciones")) {
                try {
                    File nuevaFoto = new File(fotoSeleccionada);
                    BufferedImage image = ImageIO.read(nuevaFoto);
                    // Si la foto esta en vertical redimensionarla con unos valores
                    if (image.getHeight() > image.getWidth()) {
                        nuevaFoto = Utilidades.redimensionarImagenes(nuevaFoto, 356, 475);
                        // Si no redimensionarla con otrs
                    } else {
                        nuevaFoto = Utilidades.redimensionarImagenes(nuevaFoto, 475, 356);
                    }
                    // Ruta origina del archivo
                    rutaAntigua = Path.of(nuevaFoto.getAbsolutePath());
                    // Ruta donde vamos a pegar
                    rutaNueva = Path.of(rutaExplorador.getAbsolutePath());
                    // Copia la imagen al nuevo destino
                    Files.copy(rutaAntigua, rutaNueva.resolve(rutaAntigua.getFileName()),
                            StandardCopyOption.REPLACE_EXISTING);
                    nuevaFoto.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // En la bda unicamente guardamos el nombre del archivo
            fotoSeleccionada = fileChooser.getSelectedFile().getName();
        }
        return fotoSeleccionada;
    }
     */
    public static File redimensionarImagenes(File imagen, int width, int height) {
        BufferedImage inputImage;
        File outputFile = null;

        try {
            inputImage = ImageIO.read(imagen);
            Image scaledImage = inputImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();
            outputFile = new File(imagen.getName());
            ImageIO.write(outputImage, "jpg", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputFile;
    }

}
