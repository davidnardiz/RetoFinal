package controlador;

import modelo.DAO;
import modelo.DAOImplementacionBD;
import vista.VMain;

public class Instagram {

    public static void main(String[] args) {
        DAO dao = new DAOImplementacionBD();
        VMain main = new VMain(dao);
        main.setVisible(true);

    }

}
