package ni.edu.uam.servicio;

import ni.edu.uam.modelos.Pedido;
import javax.swing.JOptionPane;

public class Cocina {

    public void prepararPedido(Pedido pedido) {
        JOptionPane.showMessageDialog(null,
                "Cocina recibió el pedido",
                "Dani's Sweet Café · Cocina", JOptionPane.PLAIN_MESSAGE);

        pedido.cambiarEstado("En preparación");

        JOptionPane.showMessageDialog(null,
                "Estado: En preparación\nPreparando tu pedido...",
                "Dani's Sweet Café · Cocina", JOptionPane.PLAIN_MESSAGE);

        pedido.cambiarEstado("Listo");

        JOptionPane.showMessageDialog(null,
                "Estado: Listo\n¡Pedido completado y listo para entregar!",
                "Dani's Sweet Café · Cocina", JOptionPane.PLAIN_MESSAGE);
    }
}
