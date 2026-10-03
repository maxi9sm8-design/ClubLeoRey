public class Socio {

    private String rut;
    private String nombre;
    private int edad;
    private double cuotaBase;

    /**
     * Constructor por defecto (sobrecarga)
     */
    public Socio() {
        this.rut = "";
        this.nombre = "";
        this.edad = 0;
        this.cuotaBase = 30000;
    }

    /**
     * Constructor con parametros
     *
     * @param rut       RUT del socio
     * @param nombre    nombre del socio
     * @param edad      edad del socio
     * @param cuotaBase valor base de la cuota mensual
     */
    public Socio(String rut, String nombre, int edad, double cuotaBase) {
        this.rut = rut;
        this.nombre = nombre;
        this.edad = edad;
        this.cuotaBase = cuotaBase;
    }

    /**
     * @return el RUT del socio
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut nuevo RUT del socio
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * @return el nombre del socio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre nuevo nombre del socio
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return la edad del socio
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad nueva edad del socio
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return la cuota base del socio
     */
    public double getCuotaBase() {
        return cuotaBase;
    }

    /**
     * @param cuotaBase nueva cuota base
     */
    public void setCuotaBase(double cuotaBase) {
        this.cuotaBase = cuotaBase;
    }

    /**
     * Calcula la cuota final
     * las subclases la sobrescriben
     *
     * @return la cuota final calculada
     */
    public double calcularCuotaFinal() {
        return cuotaBase;
    }

    /**
     * Entrega un resumen con los datos del socio
     *
     * @return cadena con los datos del socio
     */
    public String obtenerResumen() {
        return "RUT: " + rut + "\n"
             + "Nombre: " + nombre + "\n"
             + "Edad: " + edad + "\n"
             + "Cuota base: $" + (int) cuotaBase;
    }
}
