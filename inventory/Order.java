package inventory;

public class Order implements Comparable<Order> {
    private String cliente;
    private Product producto;
    private double cantidad;
    private int prioridad;

    // Constructor
    public Order(String cliente, Product producto, double cantidad, int prioridad) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.prioridad = prioridad;
    }

    // getter y setter
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String n) {
        this.cliente = n;
    }

    public Product getProducto() {
        return producto;
    }

    public void setProducto(Product p) {
        this.producto = p;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double c) {
        this.cantidad = c;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prio) {
        this.prioridad = prio;
    }

    // Ver detalles
    public void mostrarDetalles() {

        System.out.println("Cliente: " + cliente + "  " +
        "Producto: " + producto.getNombre()+ "  " +
        "Cantidad: " + cantidad + "  " +
        "Prioridad: " + prioridad);
    }

    @Override
    public int compareTo(Order order) {
        // Ordenar por prioridad (mayor prioridad primero)
        return Integer.compare(order.prioridad, prioridad);
    }

}
