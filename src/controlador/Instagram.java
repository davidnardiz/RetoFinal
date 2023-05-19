package controlador;

import modelo.DAO;
import modelo.DAOImplementacionBD;
import vista.VMain;

/**
 *
 * @author Jason
 */
public class Instagram {

    /**
     * Este metodo ejecuta todo el programa
     *
     * @param args
     */
    public static void main(String[] args) {
        DAO dao = new DAOImplementacionBD();
        VMain main = new VMain(dao);
        main.setVisible(true);

    }

}
