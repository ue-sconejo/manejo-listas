package inventory;

public class Product {

    private String nombre;
    private double cantidad;

    // Constructor
    public Product(String nombre, double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    // getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    // Ver detalles
    public void mostrarDetalles() {
        System.out.println("Nombre: " + nombre + "  " + 
        "Cantidad: " + cantidad);
    }
}
