package controlador;

import modelo.DAO;
import modelo.DAOImplementacionBD;
import vista.Conector;

public class Instagram {

	public static void main(String[] args) {
		DAO dao = new DAOImplementacionBD();
		Conector main = new Conector(dao);
                main.setVisible(true);

	}

}
