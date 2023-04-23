package controlador;

import clases.Usuario;
import excepciones.ExceptionManager;
import modelo.DAO;
import modelo.DAOImplementacionBD;
import vista.ParaTi;

public class Instagram {

    public static void main(String[] args) {
        ExceptionManager em = new ExceptionManager();

        DAO dao = new DAOImplementacionBD();
        Usuario usu = dao.buscarUsuario("xDoble_Jx");

        //Principal prin = new Principal(dao);
        try {
            ParaTi prin = new ParaTi(null, false, dao, usu);
            prin.setVisible(true);

        } catch (NullPointerException e) {
            System.out.println("controlador.Instagram.main()");

            em.NullPoineterException(e);

        }

    }

}
