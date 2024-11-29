import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThunderboltsGUI {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox Habilidad;
    private JComboBox Redencion;
    private JComboBox Mision;
    private JTable tablaThunderbolts;
    private JPanel pGeneral;
    private JButton agregarButton;
    private JButton buscarButton;
    private JButton filtrarButton;
    private JButton ordenarButton;
    private JButton contarButton;
    private JTextArea txtAreaResultados;
    private JTable tablaResultados;

    private ListaThunderbolts lista;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloResultados;

    public ThunderboltsGUI() {

        lista = new ListaThunderbolts();

        // Configurar combo boxes
        Habilidad.setModel(new DefaultComboBoxModel<>(new String[]{"Combate Cuerpo a Cuerpo", "Tiro Preciso", "Tecnología Avanzada", "Sigilo", "Supervelocidad"}));
        Redencion.setModel(new DefaultComboBoxModel<>(new Integer[]{1, 2, 3, 4, 5}));
        Mision.setModel(new DefaultComboBoxModel<>(new String[]{"Rescate", "Defensa", "Infiltración", "Neutralización", "Recuperación"}));

        // Configurar la tabla principal
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Habilidad", "Redención", "Misión"}, 0);
        tablaThunderbolts.setModel(modeloTabla);

        // Configurar la tabla de resultados
        modeloResultados = new DefaultTableModel(new String[]{"Código", "Nombre", "Habilidad", "Redención", "Misión"}, 0);
        tablaResultados.setModel(modeloResultados);


        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    String nombre = txtNombre.getText();
                    String habilidad = (String) Habilidad.getSelectedItem();
                    int redencion = (int) Redencion.getSelectedItem();
                    String mision = (String) Mision.getSelectedItem();

                    Thunderbolt nuevo = new Thunderbolt(codigo, nombre, habilidad, redencion, mision);
                    if (lista.agregarThunderbolt(nuevo)) {
                        JOptionPane.showMessageDialog(null, "Thunderbolt agregado.");
                        lista.mostrarEnJTable(modeloTabla); // Actualiza la tabla principal
                    } else {
                        JOptionPane.showMessageDialog(null, "El código del Thunderbolt ya está registrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un código de Thunderbolt válido.");
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    Thunderbolt t = lista.buscarPorCodigo(codigo);

                    if (t != null) {
                        txtNombre.setText(t.getNombre());
                        Habilidad.setSelectedItem(t.getHabilidadPrincipal());
                        Redencion.setSelectedItem(t.getNivelRedencion());
                        Mision.setSelectedItem(t.getMisionAsignada());
                    } else {
                        JOptionPane.showMessageDialog(null, "Thunderbolt no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un códigode Thunderbolt válido.");
                }
            }
        });

        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String habilidad = (String) Habilidad.getSelectedItem();
                ListaThunderbolts filtrada = lista.filtrarPorHabilidad(habilidad);
                filtrada.ordenarPorBurbuja();
                filtrada.mostrarEnJTable(modeloResultados); // Actualiza la tabla de resultados
            }
        });


        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.ordenarPorBurbuja();
                lista.mostrarEnJTable(modeloResultados); // Actualiza la tabla de resultados
            }
        });


        contarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String habilidad = (String) Habilidad.getSelectedItem();
                int total = lista.contarMisiones(habilidad, lista.getInicio());
                txtAreaResultados.setText("Total de misiones para " + habilidad + ": " + total);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ThunderboltsGUI");
        frame.setContentPane(new ThunderboltsGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
