public class SocioRegular extends Socio {

    private double descuentoPase;

    /**
     * Constructor que inicializa la clase padre con super(...)
     *
     * @param rut           RUT del socio
     * @param nombre        nombre del socio
     * @param edad          edad del socio
     * @param cuotaBase     cuota base mensual
     * @param descuentoPase porcentaje de descuento (ej. 10 = 10%)
     */
    public SocioRegular(String rut, String nombre, int edad,
                        double cuotaBase, double descuentoPase) {
        super(rut, nombre, edad, cuotaBase);
        this.descuentoPase = descuentoPase;
    }

    /**
     * @return el porcentaje de descuento
     */
    public double getDescuentoPase() {
        return descuentoPase;
    }

    /**
     * @param descuento pase nuevo porcentaje de descuento
     */
    public void setDescuentoPase(double descuentoPase) {
        this.descuentoPase = descuentoPase;
    }

    /**
     * Sobrescribe el calculo de la cuota aplicando el descuento
     *
     * @return la cuota base menos el descuento
     */
    @Override
    public double calcularCuotaFinal() {
        return getCuotaBase() - (getCuotaBase() * descuentoPase / 100);
    }

    /**
     * @return resumen del socio con su tipo y descuento
     */
    @Override
    public String obtenerResumen() {
        return super.obtenerResumen() + "\n"
             + "Categoria: Regular\n"
             + "Descuento pase: " + (int) descuentoPase + "%";
    }
}
