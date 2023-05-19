package tienda;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 * Es una clase auxiliar que genera campos personalizados.
 *
 * @author Bayron
 */
public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(Color.WHITE);
    }
}
