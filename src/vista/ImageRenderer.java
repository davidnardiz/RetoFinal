package vista;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class ImageRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        JLabel label = new JLabel();
        if (value != null) {
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setIcon((ImageIcon) value);
        }
        return label;
    }
}
