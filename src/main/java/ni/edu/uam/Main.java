package ni.edu.uam;

import ni.edu.uam.modelos.*;
import ni.edu.uam.servicio.Cocina;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final Producto[] MENU = {
            new Producto("Café Americano",       1.50),
            new Producto("Cappuccino",           2.75),
            new Producto("Jugo Natural",         2.00),
            new Producto("Sándwich de Pollo",    3.75),
            new Producto("Brownie de Chocolate", 2.25),
            new Producto("Ensalada Fresca",      4.50),
            new Producto("Agua con Gas",         1.00)
    };

    private static final String[] MENU_DISPLAY = {
            "Café Americano          $1.50",
            "Cappuccino              $2.75",
            "Jugo Natural            $2.00",
            "Sándwich de Pollo       $3.75",
            "Brownie de Chocolate    $2.25",
            "Ensalada Fresca         $4.50",
            "Agua con Gas            $1.00"
    };

    public static void main(String[] args) {

        // 1. Bienvenida
        JOptionPane.showMessageDialog(null,
                "Bienvenido a Dani's Sweet Café\nCalidad y sabor en cada pedido.",
                "Dani's Sweet Café", JOptionPane.PLAIN_MESSAGE);

        // 2. Nombre del cliente
        String nombre = null;
        while (nombre == null || nombre.trim().isEmpty()) {
            nombre = JOptionPane.showInputDialog(null,
                    "¿Cuál es tu nombre?",
                    "Dani's Sweet Café", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null) System.exit(0);
        }
        nombre = nombre.trim();

        // 3. Crear objetos
        Cliente cliente = new Cliente(nombre);
        Cajero  cajero  = new Cajero("Dani");
        Cocina  cocina  = new Cocina();
        Pedido  pedido  = new Pedido();

        // 4. Saludo
        JOptionPane.showMessageDialog(null,
                "¡Hola, " + nombre + "! Estamos listos para tomar tu pedido.",
                "Dani's Sweet Café", JOptionPane.PLAIN_MESSAGE);

        // 5. Loop de pedido
        boolean ordenando = true;

        while (ordenando) {
            Producto seleccionado = mostrarMenu();

            if (seleccionado == null) {
                if (pedido.getProductos().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Selecciona al menos un producto para continuar.",
                            "Dani's Sweet Café", JOptionPane.WARNING_MESSAGE);
                } else {
                    int confirmar = JOptionPane.showConfirmDialog(null,
                            "¿Deseas confirmar tu pedido?",
                            "Dani's Sweet Café", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) ordenando = false;
                }
                continue;
            }

            pedido.agregarProducto(seleccionado);

            String[] acciones = {"Agregar otro", "Ver mi pedido", "Confirmar pedido"};
            int accion = JOptionPane.showOptionDialog(null,
                    seleccionado.getNombre() + " agregado al pedido.\n¿Qué deseas hacer?",
                    "Dani's Sweet Café",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, acciones, acciones[0]);

            if (accion == 1) {
                mostrarEstadoPedido(pedido, nombre);
            } else if (accion == 2 || accion == JOptionPane.CLOSED_OPTION) {
                ordenando = false;
            }
        }

        // 6. Resumen antes de procesar
        mostrarEstadoPedido(pedido, nombre);

        // 7. Paso de mensajes
        cliente.hacerPedido(cajero);
        cajero.enviarACocina(pedido, cocina);
        cajero.notificarCliente(cliente, pedido);

        // 8. Despedida
        JOptionPane.showMessageDialog(null,
                "¡Gracias por tu visita, " + nombre + "!\nEsperamos verte pronto en Dani's Sweet Café.",
                "Hasta pronto", JOptionPane.PLAIN_MESSAGE);
    }

    private static Producto mostrarMenu() {
        JList<String> lista = new JList<>(MENU_DISPLAY);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setSelectedIndex(0);
        lista.setFont(new Font("Monospaced", Font.PLAIN, 13));
        lista.setVisibleRowCount(7);

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(300, 175));
        scroll.setBorder(BorderFactory.createTitledBorder("Menu"));

        int resultado = JOptionPane.showConfirmDialog(null, scroll,
                "Dani's Sweet Café · Menu",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION && lista.getSelectedIndex() >= 0) {
            int i = lista.getSelectedIndex();
            return new Producto(MENU[i].getNombre(), MENU[i].getPrecio());
        }
        return null;
    }

    private static void mostrarEstadoPedido(Pedido pedido, String nombre) {
        if (pedido.getProductos().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Tu pedido está vacío.",
                    "Dani's Sweet Café · Tu pedido", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("Pedido de: ").append(nombre).append("\n");
        resumen.append("--------------------------------\n");

        double total = 0;
        for (Producto p : pedido.getProductos()) {
            resumen.append(String.format("%-24s $%.2f%n", p.getNombre(), p.getPrecio()));
            total += p.getPrecio();
        }

        resumen.append("--------------------------------\n");
        resumen.append(String.format("Total:                   $%.2f", total));
        resumen.append("\n\nEstado: ").append(pedido.getEstado());

        JOptionPane.showMessageDialog(null, resumen.toString(),
                "Dani's Sweet Café · Tu pedido", JOptionPane.PLAIN_MESSAGE);
    }
}
