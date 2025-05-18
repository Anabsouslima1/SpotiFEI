package view.componentes;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class BotaoRenderizar extends JButton implements TableCellRenderer {
    public BotaoRenderizar(){
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        if(value instanceof Boolean) {
            boolean curtido = (Boolean) value;
            setText(curtido ? "Descurtir" : "Curtir");
        } else {
            setText("Curtir");   
        }
        return this;
    }
}

