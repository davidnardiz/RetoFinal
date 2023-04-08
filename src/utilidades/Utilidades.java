package utilidades;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Utilidades {

	public static int numeros_aleatorios(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static String exploradorArchivos(JDialog ventana) {
		String fotoSeleccionada = "";

		// Crear un objeto JFileChooser
		JFileChooser fileChooser = new JFileChooser();

		// Establecer una carpeta predeterminada
		String rutaProyecto = System.getProperty("user.dir");
		File defaultDir = new File(rutaProyecto + "/src/img");
		fileChooser.setCurrentDirectory(defaultDir);

		// Establecer un filtro para mostrar solo archivos de imagen
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);

		// Mostrar el cuadro de diálogo del selector de archivos
		int result = fileChooser.showOpenDialog(ventana);

		// Si el usuario selecciona un archivo, mostrar su ruta
		if (result == JFileChooser.APPROVE_OPTION) {
			fotoSeleccionada = fileChooser.getSelectedFile().getPath();
			
			if(fotoSeleccionada.contains("RetoFinal\\src\\img")) {
				fotoSeleccionada = fileChooser.getSelectedFile().getName();
			}
			
			System.out.println(fotoSeleccionada);
		}

		return fotoSeleccionada;
	}
}
