# Club Deportivo Leo Rey - Gestión de Socios

Sistema de escritorio hecho en Java con AWT para registrar socios y calcular su cuota mensual.

### Características
- Registro de socios Regular y VIP
- Validación de RUT, nombre y edad (mayor de 18)
- Cálculo de cuota con herencia y polimorfismo
- Proyección de pago a 6 o 12 meses
- Beneficio opcional de casillero/toalla

### Estructura
- `Socio.java` -> Clase padre con encapsulamiento
- `SocioRegular.java` -> Hereda de Socio, aplica descuento
- `SocioVIP.java` -> Hereda de Socio, recargo premium + spa
- `ClubLeoRey.java` -> Interfaz gráfica AWT + main

### Cómo ejecutar
Tener JDK 8 o superior.

```bash
javac *.java
java ClubLeoRey
