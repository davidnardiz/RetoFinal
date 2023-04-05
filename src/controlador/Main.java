package controlador;

import modelo.DAO;
import modelo.DAOImplementacionBD;
import vista.Principal;

public class Main {

	public static void main(String[] args) {
		DAO dao = new DAOImplementacionBD();
		
		Principal prin = new Principal(dao);
		prin.setVisible(true);

	}

}
