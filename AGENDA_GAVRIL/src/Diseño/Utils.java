package Diseño;

import java.awt.*;
import java.util.HashMap;

public class Utils {
    public static final Font FONT_GENERAL_UI = new Font("Segoe UI", Font.PLAIN, 20);
    
    public static final Dimension DIMENSION_= new Dimension (1500, 1000);
    public static final Dimension DIMENSION_TAB= new Dimension (1500, 950);


    public static final Color COLOR_OUTLINE = new Color(103, 112, 120);
    public static final Color COLOR_BACKGROUND = new Color(37, 51, 61);
    public static final Color COLOR_INTERACTIVE = new Color(108, 216, 158);
    public static final Color COLOR_INTERACTIVE_DARKER = new Color(87, 171, 127);
    public static final Color OFFWHITE = new Color(229, 229, 229);

    public static final String TEXTO_APODO = "Apodo:";
    public static final String TEXTO_NOMBRE = "Nombre:";
    public static final String TEXTO_APELLIDO = "Apellido:";
    public static final String TEXTO_EMPRESA = "Empresa:";
    public static final String TEXTO_SEXO = "Sexo:";
    public static final String TEXTO_TELEFONO = "Telefono:";
    public static final String TEXTO_CORREO = "Correo:";
    public static final String TEXTO_NOTAS = "Notas:";
    

    public static final String TEXTO_CALLE= "Calle:";
    public static final String TEXTO_NUMERO = "Numero:";
    public static final String TEXTO_PISO = "Piso:";
    public static final String TEXTO_PUERTA = "Puerta:";
    public static final String TEXTO_CODIGO = "CP:";
    public static final String TEXTO_CIUDAD = "Ciudad:";
    public static final String TEXTO_PROVINCIA = "Provincia:";
    public static final String TEXTO_AFICION = "Aficiones:";
    
    
    public static final String TEXTO_ACCEPTAR = "Aceptar";
    public static final String TEXTO_MODIFICAR = "Modificar";
    public static final String TEXTO_ELIMINAR = "Eliminar";
    public static final String TEXTO_BUSCAR = "Buscar";
    public static final String TEXTO_SALIR = "Salir";
    public static final String TEXTO_SELECT_APODO = "Apodo";
    public static final String TEXTO_SELECT_NOMBRE = "Nombre";
    public static final String TEXTO_SELECT_EMPRESA = "Empresa";
    public static final String TEXTO_SELECT_TELEFONO = "Telefono";

    public static final int ROUNDNESS = 8;

    public static Graphics2D get2dGraphics(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>() {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        return g2;
    }
}