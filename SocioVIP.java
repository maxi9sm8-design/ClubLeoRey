public class SocioVIP extends Socio {

    private static final double RECARGO_PREMIUM = 20000;
    private static final double RECARGO_SPA = 10000;

    private boolean accesoTotalSpa;

    /**
     * Constructor que inicializa la clase padre con super(...)
     *
     * @param rut            RUT del socio
     * @param nombre         nombre del socio
     * @param edad           edad del socio
     * @param cuotaBase      cuota base mensual
     * @param accesoTotalSpa true si tiene acceso total al spa
     */
    public SocioVIP(String rut, String nombre, int edad,
                    double cuotaBase, boolean accesoTotalSpa) {
        super(rut, nombre, edad, cuotaBase);
        this.accesoTotalSpa = accesoTotalSpa;
    }

    /**
     * @return true si tiene acceso total al spa
     */
    public boolean isAccesoTotalSpa() {
        return accesoTotalSpa;
    }

    /**
     * @param accesoTotalSpa nuevo valor de acceso al spa
     */
    public void setAccesoTotalSpa(boolean accesoTotalSpa) {
        this.accesoTotalSpa = accesoTotalSpa;
    }

    /**
     * Sobrescribe el calculo de la cuota sumando el recargo premium
     * (y el del spa, si corresponde).
     *
     * @return la cuota base mas los recargos
     */
    @Override
    public double calcularCuotaFinal() {
        double cuota = getCuotaBase() + RECARGO_PREMIUM;
        if (accesoTotalSpa) {
            cuota = cuota + RECARGO_SPA;
        }
        return cuota;
    }

    /**
     * @return resumen del socio con su tipo y acceso al spa
     */
    @Override
    public String obtenerResumen() {
        String spa = accesoTotalSpa ? "Si" : "No";
        return super.obtenerResumen() + "\n"
             + "Categoria: VIP\n"
             + "Acceso total spa: " + spa;
    }
}
