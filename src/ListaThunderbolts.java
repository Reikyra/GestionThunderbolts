import javax.swing.table.DefaultTableModel;

public class ListaThunderbolts {
    private Nodo inicio;

    public ListaThunderbolts() {
        this.inicio = null;
    }

    public boolean agregarThunderbolt(Thunderbolt nuevo) {
        if (buscarPorCodigo(nuevo.getCodigo()) != null) {
            return false; // Código duplicado
        }

        Nodo nuevoNodo = new Nodo(nuevo);
        nuevoNodo.siguiente = inicio;
        inicio = nuevoNodo;
        return true;
    }

    public Thunderbolt buscarPorCodigo(int codigo) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.thunderbolt.getCodigo() == codigo) {
                return actual.thunderbolt;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public ListaThunderbolts filtrarPorHabilidad(String habilidad) {
        ListaThunderbolts nuevaLista = new ListaThunderbolts();
        Nodo actual = inicio;

        while (actual != null) {
            if (actual.thunderbolt.getHabilidadPrincipal().equalsIgnoreCase(habilidad)) {
                nuevaLista.agregarThunderbolt(actual.thunderbolt);
            }
            actual = actual.siguiente;
        }
        return nuevaLista;
    }

    public void ordenarPorBurbuja() {
        int n = getTamanio();
        for (int i = 0; i < n - 1; i++) {
            for (Nodo actual = inicio; actual != null && actual.siguiente != null; actual = actual.siguiente) {
                if (actual.thunderbolt.getNivelRedencion() > actual.siguiente.thunderbolt.getNivelRedencion()) {
                    Thunderbolt temp = actual.thunderbolt;
                    actual.thunderbolt = actual.siguiente.thunderbolt;
                    actual.siguiente.thunderbolt = temp;
                }
            }
        }
    }

    public int contarMisiones(String habilidad, Nodo nodo) {
        if (nodo == null) return 0;

        int count = 0;
        if (nodo.thunderbolt.getHabilidadPrincipal().equalsIgnoreCase(habilidad)) {
            count++;
        }
        return count + contarMisiones(habilidad, nodo.siguiente);
    }

    public void mostrarEnJTable(DefaultTableModel modelo) {
        modelo.setRowCount(0); // Limpiar la tabla
        Nodo actual = inicio;

        while (actual != null) {
            Thunderbolt t = actual.thunderbolt;
            modelo.addRow(new Object[]{t.getCodigo(), t.getNombre(), t.getHabilidadPrincipal(), t.getNivelRedencion(), t.getMisionAsignada()});
            actual = actual.siguiente;
        }
    }

    public Nodo getInicio() {
        return inicio;
    }

    public int getTamanio() {
        int count = 0;
        Nodo actual = inicio;
        while (actual != null) {
            count++;
            actual = actual.siguiente;
        }
        return count;
    }
}
