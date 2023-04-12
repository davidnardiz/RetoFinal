package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;

public class IniciarSesion extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;
	private JButton btnParati;
	private Usuario usu;

	/**
	 * Create the dialog.
	 * 
	 * @param dao
	 * @param b
	 * @param principal
	 */
	public IniciarSesion(Principal principal, boolean b, DAO dao, Usuario usu) {
		super(principal);
		this.setModal(b);
		this.dao = dao;
		this.usu = usu;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnParati = new JButton("Confirmar");
			btnParati.setBounds(321, 227, 89, 23);
			contentPanel.add(btnParati);
			btnParati.addActionListener(this);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnParati)) {
			mostrarParaTi();
		}
	}

	private void mostrarParaTi() {
		ParaTi parati = new ParaTi(this, true, dao, usu);
		parati.setVisible(true);

	}

}
