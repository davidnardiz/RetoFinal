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
import jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author Jason
 */
public class Utilidades {

    /**
     * Genera un numero aleatorio en un rango
     *
     * @param min Es el numero minimo
     * @param max Es el numero maximo
     * @return Devuelve el numero aleatorio
     */
    public static int numeros_aleatorios(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    /**
     * Permite elegir una foto mediante un explorador de archivos
     *
     * @param ventana Es la ventana desde la que accedes al metodo
     * @return Devuelve el nombre de la imagen
     */
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

    /**
     * Permite elegir una foto mediante un explorador de archivos
     *
     * @param ventana Es la ventana desde la que se accede al metodo
     * @param width Es el ancho que quieres que mida la foto
     * @param height Es lo alto que quietes que mida la foto
     * @return Devulve el nombre de la imagen
     */
    public static String seleccionarImagenTienda(JDialog ventana, int width, int height) {
        File imagen = null;

        String rutaProyecto = System.getProperty("user.dir");
        File rutaExplorador = new File(rutaProyecto + "/src/imagenes/tienda/");

        JnaFileChooser ch = new JnaFileChooser(rutaExplorador);
        boolean seleccionado = ch.showOpenDialog(ventana);

        if (seleccionado) {
            imagen = ch.getSelectedFile();

            if (!imagen.getAbsolutePath().contains("\\RetoFinal\\src\\imagenes\\tienda")) {

                try {
                    File nuevaFoto = new File(imagen.getAbsolutePath());
                    BufferedImage image = ImageIO.read(nuevaFoto);

                    // Si la foto esta en vertical redimensionarla con unos valores
                    if (image.getHeight() > image.getWidth()) {
                        nuevaFoto = Utilidades.redimensionarImagenes(nuevaFoto, 140, 250);

                        // Si no redimensionarla con otrs
                    } else {
                        nuevaFoto = Utilidades.redimensionarImagenes(nuevaFoto, 250, 140);
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

    /**
     * Permite elegir una foto mediante un explorador de archivos
     *
     * @param ventana Es la ventana desde la que accedes al metodo
     * @return Devuelve el nombre de la imagen
     */
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

    /**
     * Redimensiona las imagenes
     *
     * @param imagen Es la imagen que quieres redimensionar
     * @param width Es la nueva anchura
     * @param height Es la nueva altura
     * @return Devuelve la imagen redimensionada
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
