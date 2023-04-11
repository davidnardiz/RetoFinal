package controlador;

import clases.Usuario;
import modelo.DAO;
import modelo.DAOImplementacionBD;
import vista.ParaTi;

public class Main {

	public static void main(String[] args) {
		DAO dao = new DAOImplementacionBD();
		Usuario usu = dao.buscarUsuario("xDoble_Jx");
		
		//Principal prin = new Principal(dao);
		ParaTi prin = new ParaTi(null, false, dao, usu);
		prin.setVisible(true);

	}

}
