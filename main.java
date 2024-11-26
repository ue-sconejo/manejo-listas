import java.io.Console;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import inventory.*;

public class main {

    //  Definicion de variables
    static Console console = System.console();

    static Product[] productos = new Product[99];

    static ArrayDeque<Product> inventario = new ArrayDeque<>();
    
    static ArrayList<Order> ordenes = new ArrayList<>();
    
    static LinkedList<Order> colaOrdenes = new LinkedList<>();

    static PriorityQueue<Order> colaPrioritaria = new PriorityQueue<>();

    public static void main(String[] args) {
        // 1.1 Inventario Utilizar un arreglo para
        // inicializar los productos del inventario con su nombre y cantidad.
        productos[0] = new Product("Lapiz", 10);
        productos[1] = new Product("Esfero", 5);
        productos[2] = new Product("Cuaderno", 8);
        productos[3] = new Product("Borrador", 3);

        // Almacenamiento en fisico
        inventario.add(productos[0]);
        inventario.add(productos[1]);
        inventario.add(productos[2]);

        colaOrdenes.add(new Order("Jhon", productos[2], 2, 2));
        colaOrdenes.add(new Order("Juan", productos[1], 2, 1));
        colaOrdenes.add(new Order("Maria", productos[4], 10, 5));

        colaPrioritaria.add(new Order("Jhon", productos[2], 2, 2));
        colaPrioritaria.add(new Order("Juan", productos[1], 2, 1));
        colaPrioritaria.add(new Order("Maria", productos[4], 10, 5));
        
        menuPrincipal();
    }

    //  Crear una interfaz amigable en la consola para mostrar las operaciones realizadas.
    //  Menu principal
    //  3. Crear una interfaz amigable en la consola para mostrar las operaciones realizadas.
    public static void menuPrincipal() {
        limpiarConsola();
        System.out.println("---- Menu Principal ----\n");
        System.out.println("p) Lista de Productos\no) Gestion de Ordenes\ni) Inventario\nc) Cola Regular\nu) Cola Prioritaria");
        System.out.println("\nEscriba una de las opciones y presione enter.");

        String option = console.readLine("Enter con el campo vacio para Salir: ");

        //  Opciones del menu
        switch(option) {
            case "p":
                menuProductos();
              break;
            case "o":
                menuOrdenes();
              break;
            case "i":
                menuBodega();
              break;
            case "c":
              menuCola();
            break;
            case "u":
                menuColaPrioritaria();
            break;
            default:
                System.out.println("Saliendo...");
        }
    }

    //  Menu de los productos
    public static void menuProductos(){
        limpiarConsola();

        // 1.2 Permitir consultar y actualizar los niveles de inventario.
        System.out.println("---- Menu Poductos ----\n");
        int cantidadProductos = listarProductos();  // <-- Trae la lista de los productos actuales Array

        System.out.println("\n---- Opciones ----");
        System.out.println("(Escribe el ID de un Producto y presione Enter para ver el detalle.)");
        String option = console.readLine("Enter con el campo vacio para volver el menu Principal: ");

        if (option == null || option.trim().isEmpty()) {
            menuPrincipal();
        }
        else if (Integer.parseInt(option) <= cantidadProductos && Integer.parseInt(option)>0) {
            verProducto(Integer.parseInt(option)-1);// <-- Consultar el detalle de un producto
        } else {
            limpiarConsola();
            console.readLine("Error: La opcion que ingreso no existe!!!");
            menuProductos();
        }
    }

    //  2.1 Utilizar una lista dinámica para registrar las órdenes, donde cada orden tenga:
    //  Menu de las ordenes
    public static void menuOrdenes() {
        limpiarConsola();
        System.out.println("---- Lista de Ordenes ----\n");

        //  Verifica si existen registros
        if (ordenes.isEmpty()) {
            System.out.println("Actualmente no hay ordenes registradas\n");
        } else {
            for(Order orden : ordenes) {
                orden.mostrarDetalles();
            }
        }

        System.out.println("\n---- Opciones ----");
        System.out.println("a) Agregar orden");
        System.out.println("(Escribe el ID de una Orden y presione Enter para ver el detalle.)");

        String option = console.readLine("Enter con el campo vacio para volver el menu Principal: ");

        if (option.equals("a")) {
            formularioOrden();
        }
        else if (option == null || option.trim().isEmpty()) {
            menuPrincipal();
        }
        else if (Integer.parseInt(option) <= ordenes.size() && Integer.parseInt(option)>0) {
            verOrden(Integer.parseInt(option)-1);
        } else {
            limpiarConsola();
            console.readLine("Error: La opcion que ingreso no existe!!!");
            menuOrdenes();
        }
    }

    //  Menu de la bodega fisica
    public static void menuBodega() {
        limpiarConsola();

        System.out.println("---- Inventario ----\n");
        
        // Muestra en pantalla la lista de productos del inventario sin organizar
        for (Product caja : inventario) {
            caja.mostrarDetalles();
        }

        System.out.println("\nd) Desapilar caja, a) Agregar Caja");
        String option = console.readLine("Enter con el campo vacio para volver el menu Principal: ");

        if (option.contains("a")) {
            // Agrega un nuevo elemento a el inicio de la pila
            System.out.println("Escribe el ID del producto que quieres agregar:");
            listarProductos();  // <-- Trae la lista de los productos actuales Array
            int idProd = Integer.parseInt(console.readLine(":"));
            inventario.push(productos[idProd-1]);
            menuBodega();
        } else if(option.contains("d")) {
            // Borra el ultimo elemento
            inventario.pop();
            menuBodega();
        } else if (option.contains("")) {
            menuPrincipal();
        } else {
            menuPrincipal();
        }

    }

    //  Menu del tipo de estructura Cola para las ordenes registradas
    public static void menuCola () {
        limpiarConsola();

        System.out.println("---- Inventario Cola de Ordendes Regulares ----\n");

        for (Order orden : colaOrdenes) {
            orden.mostrarDetalles();
        }

        System.out.println("t) Terminar la primera orden de la cola");
        String option = console.readLine("Enter con el campo vacio para volver el menu Principal: ");

        if (option.contains("t")) {
            // Borra el ultimo elemento
            colaOrdenes.pop();
            menuCola();
        } else {
            menuPrincipal();
        }
    }
    //  Menu de las ordenes organizadas por prioridad
    public static void menuColaPrioritaria() {
        limpiarConsola();

        System.out.println("---- Inventario Cola Priorizada ----\n");

        //  Muestra las Ordenes de la cola prioritaria
        for (Order orden : colaPrioritaria) {
            orden.mostrarDetalles();
        }
        System.out.println("\nt) Terminar la primera orden de la cola");
        String option = console.readLine("Enter con el campo vacio para volver el menu Principal: ");

        if (option.contains("t")) {
            // Eliminar y obtener la tarea con mayor prioridad
            colaPrioritaria.poll();
            menuColaPrioritaria();
        } else {
            menuPrincipal();
        }
    }

    // 2.2 Agregar una orden
    // Formulario para registrar una orden dentro de las diferentes Listas
    public static void formularioOrden() {
        limpiarConsola();

        String cliente = console.readLine("Ingrese el nombre del cliente: ");
        limpiarConsola();

        listarProductos();  // <-- Trae la lista de los productos actuales Array

        String idProducto = console.readLine("Igrese el id del Producto: ");
        limpiarConsola();

        String cantidad = console.readLine("Ingrese la cantidad de producto: ");
        limpiarConsola();

        String prio = console.readLine("Ingrese la prioridad de la orden: ");

        // 2.2 Agregar nueva Orden
        ordenes.add(new Order(
            cliente,
            productos[Integer.parseInt(idProducto)-1],
            Integer.parseInt(cantidad),
            Integer.parseInt(prio)));

        //  Agregar a la Cola
        colaOrdenes.add(new Order(
            cliente,
            productos[Integer.parseInt(idProducto)-1],
            Integer.parseInt(cantidad),
            Integer.parseInt(prio)));

        colaPrioritaria.add(new Order(
            cliente,
            productos[Integer.parseInt(idProducto)-1],
            Integer.parseInt(cantidad),
            Integer.parseInt(prio)));
        
        menuOrdenes();
    }

    // 1.2 Permitir consultar y actualizar los niveles de inventario.
    public static void verProducto(int index) {
        limpiarConsola();
        // Consulta del nombre y cantidad de un producto
        System.out.println("---- Detalle del producto ----");
        Product producto = productos[index];
        producto.mostrarDetalles(); //<-- Muestra en pantalla la informacion

        System.out.println("\n---- Opciones ----");
        System.out.println("n) Edit. Nombre, c) Edit. Cantidad");
        String option = console.readLine("Enter con el campo vacio para Volver: ");

        if (option.contains("n")) {
            // Actualiza el nombre del producto seleccionado
            String nombre = console.readLine("Escribe el nuevo nombre: ");
            producto.setNombre(nombre);
            menuProductos();
        } else if(option.contains("c")) {
            // Actualiza la cantidad del producto seleccionado
            String cantidad = console.readLine("Escribe la nueva cantidad: ");
            producto.setCantidad(Double.parseDouble(cantidad));
            menuProductos();
        } else if (option.contains("")) {
            menuProductos();
        } else {
            limpiarConsola();
            console.readLine("Error: La opcion que ingreso no existe!!!");
            menuProductos();
        }
    }

    //  Ver el detalle de una orden
    public static void verOrden(int index) {
        limpiarConsola();

        System.out.println("---- Detalle de una orden ----\n");

        Order orden = ordenes.get(index);
        orden.mostrarDetalles();
        //  Opciones de edicion o eliminacion de la orden actual
        System.out.println("\n---- Opciones ----");
        System.out.println("c) Edit. Nombre, q) Edit. Cantidad, u) Edit. Urgencia,\np) Edit. Producto, d) ELIMINAR Orden");
        String option = console.readLine("Enter con el campo vacio para Volver: ");

        switch(option) {
            case "c":
                // Actualiza el nombre del cliente en la orden seleccionada
                String nombre = console.readLine("Escribe el nuevo cliente: ");
                orden.setCliente(nombre);
                verOrden(index);
              break;
            case "q":
                // Actualiza la cantidad de producto en la orden seleccionada
                double cantidad = Double.parseDouble(console.readLine("Escribe la nueva cantidad: "));
                orden.setCantidad(cantidad);
                verOrden(index);
              break;
            case "u":
                // Actualiza la prioridad en la orden seleccionada
                int prioridad = Integer.parseInt(console.readLine("Ingrese la nueva prioridad: "));
                orden.setPrioridad(prioridad);
                verOrden(index);
                break;
            case "p":
                listarProductos();  // <-- Trae la lista de los productos actuales Array
                // Actualiza el producto en la orden seleccionada
                int prodIndex = Integer.parseInt(console.readLine("Ingrese el id del nuevo Producto: "));
                orden.setProducto(productos[prodIndex-1]);
                verOrden(index);
                break;
            case "d":
                // Elimina la orden seleccionada
                String eliminar = console.readLine("Desea ELIMINAR esta Orden?:\n (s) Eliminar / (n) Cancelar");
                if (eliminar.contains("s")) {
                    ordenes.remove(index);
                } 
                verOrden(index);
                break;
            default:
            menuOrdenes();
        }
    }

   
    // --- Metodos genericos ---

    //  Metodo para traer los elementos dentro de productos y mostrarlos en la consola
    public static int listarProductos() {
        int index = 0;

        for(int i=0;i<productos.length;i++){
            if (productos[i] != null) {
                index++;
                System.out.print("Id: "+ index + " ");
                productos[i].mostrarDetalles();
            }
        }
        return index;
    }

    // Metodo para limpiar la consola
    public static void limpiarConsola() {
        try {
            String sistemaOperativo = System.getProperty("os.name");

            if (sistemaOperativo.contains("Windows")) {
                // Para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para sistemas basados en Unix (Linux/Mac)
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la consola");
        }
    }
}