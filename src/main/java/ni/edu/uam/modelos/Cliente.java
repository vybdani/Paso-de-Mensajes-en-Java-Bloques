package ni.edu.uam.modelos;

import javax.swing.JOptionPane;

public class Cliente {

    private String nombre;

    public Cliente() {}

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public void hacerPedido(Cajero cajero) {
        JOptionPane.showMessageDialog(null, "[CLIENTE] " + nombre + " está realizando un pedido...");
        cajero.recibirPedido(this);
    }

    public void recibirNotificacion(String mensaje) {
        JOptionPane.showMessageDialog(null, "[CLIENTE] Notificación para " + nombre + ": " + mensaje);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}