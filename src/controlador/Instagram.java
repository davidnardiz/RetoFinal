package controlador;

import clases.Usuario;
import modelo.DAO;
import modelo.DAOImplementacionBD;
import vista.ParaTi;
import vista.Principal;
import vista.pruebaChat;

public class Instagram {

    public static void main(String[] args) {
        DAO dao = new DAOImplementacionBD();
        Usuario usu = dao.buscarUsuario("xDoble_Jx");

        // Principal prin = new Principal(dao);
        ParaTi prin = new ParaTi(null, false, dao, usu);
        prin.setVisible(true);
        // pruebaChat pru = new pruebaChat(null, true, dao, usu);
        //pru.setVisible(true);
    }

}
