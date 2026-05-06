package ni.edu.uam.modelos;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<Producto> productos;
    private String estado;

    public Pedido() {
        this.productos = new ArrayList<>();
        this.estado = "Pendiente";
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        JOptionPane.showMessageDialog(null, "   + Agregado: " + producto);
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        JOptionPane.showMessageDialog(null, "   » Estado actualizado: " + estado);
    }

    public void mostrarResumen() {
        StringBuilder resumen = new StringBuilder("   ---------------------------\n");
        for (Producto p : productos) {
            resumen.append("     · ").append(p).append("\n");
        }
        resumen.append("   Estado: ").append(estado).append("\n");
        resumen.append("   ---------------------------");
        JOptionPane.showMessageDialog(null, resumen.toString());
    }

    public List<Producto> getProductos() { return productos; }
    public String getEstado() { return estado; }
}