import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Sistema de Gestion de Socios y Membresias - Club Deportivo Leo Rey.
 * Interfaz grafica con AWT. Asignatura: Fundamentos de Programacion
 * Orientada a Objetos
 * Docente: Javiera Jaldin.
 *
 * @author Ignacio San Martin, Jorge Bazan
 */
public class ClubLeoRey extends Frame implements ActionListener {

    private static final double CUOTA_BASE = 30000;
    private static final double MONTO_CASILLERO = 5000;

    private TextField txtRut;
    private TextField txtNombre;
    private TextField txtEdad;
    private Choice chTipo;
    private Choice chMeses;
    private Checkbox chkCasillero;
    private Button btnRegistrar;
    private Button btnLimpiar;
    private TextArea areaReporte;

    /**
     * Construye la ventana principal y registra los escuchadores.
     */
    public ClubLeoRey() {
        super("Club Deportivo Leo Rey - Gestion de Socios");
        setSize(520, 560);
        setLayout(new BorderLayout());

        txtRut = new TextField();
        txtNombre = new TextField();
        txtEdad = new TextField();

        chTipo = new Choice();
        chTipo.add("Regular");
        chTipo.add("VIP");

        chMeses = new Choice();
        chMeses.add("6");
        chMeses.add("12");

        chkCasillero = new Checkbox("Incluye casillero/toalla");

        Panel panelDatos = new Panel(new GridLayout(6, 2, 5, 8));
        panelDatos.add(new Label("RUT:"));
        panelDatos.add(txtRut);
        panelDatos.add(new Label("Nombre:"));
        panelDatos.add(txtNombre);
        panelDatos.add(new Label("Edad:"));
        panelDatos.add(txtEdad);
        panelDatos.add(new Label("Categoria:"));
        panelDatos.add(chTipo);
        panelDatos.add(new Label("Proyeccion (meses):"));
        panelDatos.add(chMeses);
        panelDatos.add(new Label("Beneficios:"));
        panelDatos.add(chkCasillero);

        btnRegistrar = new Button("Registrar y Calcular");
        btnLimpiar = new Button("Limpiar");
        btnRegistrar.addActionListener(this);
        btnLimpiar.addActionListener(this);

        Panel panelBotones = new Panel();
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnLimpiar);

        areaReporte = new TextArea();
        areaReporte.setEditable(false);

        Panel panelNorte = new Panel(new BorderLayout());
        panelNorte.add(panelDatos, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(areaReporte, BorderLayout.CENTER);

        // Cierra la ventana con la X (WindowAdapter de java.awt.event)
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    /**
     * Atiende los eventos de los botones.
     *
     * @param e evento de accion generado por el boton presionado
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            registrar();
        } else if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }

    /**
     * Valida los datos, crea el socio segun la categoria y muestra
     * el reporte con la proyeccion de pagos.
     */
    private void registrar() {
        String rut = txtRut.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (rut.length() == 0 || nombre.length() == 0
                || txtEdad.getText().trim().length() == 0) {
            areaReporte.setText("Error: Debe completar RUT, nombre y edad.");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(txtEdad.getText().trim());
        } catch (NumberFormatException ex) {
            areaReporte.setText("Error: La edad debe ser un numero.");
            return;
        }

        // Regla de edad minima
        if (edad < 18) {
            areaReporte.setText("Error: El socio debe ser mayor de edad.");
            return;
        }

        // Regla por categoria: switch asigna descuento y acceso al spa
        int tipo = chTipo.getSelectedIndex() + 1; // 1 = Regular, 2 = VIP
        double descuento = 0;
        boolean spa = false;
        switch (tipo) {
            case 1:
                descuento = 10;
                break;
            case 2:
                spa = true;
                break;
            default:
                break;
        }

        Socio socio;
        if (tipo == 1) {
            socio = new SocioRegular(rut, nombre, edad, CUOTA_BASE, descuento);
        } else {
            socio = new SocioVIP(rut, nombre, edad, CUOTA_BASE, spa);
        }

        double cuota = socio.calcularCuotaFinal();

        // Beneficio con checkbox
        if (chkCasillero.getState()) {
            cuota = cuota + MONTO_CASILLERO;
        }

        int meses = Integer.parseInt(chMeses.getSelectedItem());

        areaReporte.setText("=== SOCIO REGISTRADO ===\n");
        areaReporte.append(socio.obtenerResumen() + "\n");
        if (chkCasillero.getState()) {
            areaReporte.append("Casillero/toalla: Si (+$" + (int) MONTO_CASILLERO + ")\n");
        } else {
            areaReporte.append("Casillero/toalla: No\n");
        }
        areaReporte.append("Cuota mensual final: $" + (int) cuota + "\n\n");
        areaReporte.append(proyectarCuotas(cuota, meses));
    }

    /**
     * Calcula la proyeccion de pago acumulando mes a mes con un ciclo for.
     *
     * @param cuota cuota mensual final
     * @param meses cantidad de meses a proyectar (6 o 12)
     * @return texto con el desglose mes a mes y el total
     */
    private String proyectarCuotas(double cuota, int meses) {
        String texto = "=== PROYECCION A " + meses + " MESES ===\n";
        double total = 0;
        for (int mes = 1; mes <= meses; mes++) {
            total = total + cuota;
            texto = texto + "Mes " + mes + ": $" + (int) cuota
                  + " | Acumulado: $" + (int) total + "\n";
        }
        texto = texto + "TOTAL: $" + (int) total + "\n";
        return texto;
    }

    /**
     * Limpia los campos del formulario y el reporte.
     */
    private void limpiar() {
        txtRut.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        chTipo.select(0);
        chMeses.select(0);
        chkCasillero.setState(false);
        areaReporte.setText("");
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args argumentos de linea de comandos (no se usan)
     */
    public static void main(String[] args) {
        new ClubLeoRey();
    }
}
