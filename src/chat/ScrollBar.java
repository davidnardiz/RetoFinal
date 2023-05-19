package chat;

import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 * Es una clase auxiliar que genera campos personalizados.
 *
 * @author Bayron
 */
public class ScrollBar extends JScrollBar {

    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setOpaque(false);
        setUnitIncrement(20);

    }
}
