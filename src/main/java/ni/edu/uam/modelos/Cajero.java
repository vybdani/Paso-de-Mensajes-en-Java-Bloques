package ni.edu.uam.modelos;

import ni.edu.uam.servicio.Cocina;
import javax.swing.JOptionPane;

public class Cajero {

    private String nombre;

    public Cajero() {}

    public Cajero(String nombre) {
        this.nombre = nombre;
    }

    public void recibirPedido(Cliente cliente) {
        JOptionPane.showMessageDialog(null,
                nombre + " (Cajero) registró el pedido de " + cliente.getNombre() + ".",
                "Dani's Sweet Café", JOptionPane.PLAIN_MESSAGE);
    }

    public void enviarACocina(Pedido pedido, Cocina cocina) {
        JOptionPane.showMessageDialog(null,
                nombre + " envía el pedido a la cocina...",
                "Dani's Sweet Café", JOptionPane.PLAIN_MESSAGE);
        cocina.prepararPedido(pedido);
    }

    public void notificarCliente(Cliente cliente, Pedido pedido) {
        JOptionPane.showMessageDialog(null,
                nombre + " notifica al cliente que su pedido está " + pedido.getEstado() + ".",
                "Dani's Sweet Café", JOptionPane.PLAIN_MESSAGE);
        cliente.recibirNotificacion("tu pedido está " + pedido.getEstado() + ". ¡Buen provecho!");
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}