package view.componentes;

import javax.swing.*;
import java.awt.*;

public class BotaoEditor extends DefaultCellEditor {
    protected JButton button;
    private boolean isPushed;
    private JTable tabela;
    private int linha;
    private int coluna;
     
    public BotaoEditor(JCheckBox checkBox, JTable tabela) {
        super(checkBox);
        this.tabela = tabela;
        button = new JButton();
        button.setOpaque(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object valor = tabela.getValueAt(linha, coluna);
                if (valor instanceof Boolean) {
                    boolean curtido = (Boolean) valor;
                    if (curtido) {
                        tabela.setValueAt(false, linha, coluna);
                        JOptionPane.showMessageDialog(button, "Descurtido!");
                    } else {
                        tabela.setValueAt(true, linha, coluna);
                        JOptionPane.showMessageDialog(button, "Curtido!");
                    }
                }
                fireEditingStopped(); // Para encerrar a edição
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.linha = row;
        this.coluna = column;

        if (value instanceof Boolean) {
            boolean curtido = (Boolean) value;
            button.setText(curtido ? "Descurtir" : "Curtir");
        } else {
            button.setText("Curtir");
        }

        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return true;
    }
}
