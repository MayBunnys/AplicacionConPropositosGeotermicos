package com.mycompany.aplicacionconpropositosgeotermicos;

/**
 *
 * @author VyC
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplicacionConPropositosGeotermicos {

    private JFrame frame;
    private JPanel pagina1, pagina2, pagina3;

    // Variables
    private JTextField sumaField;
    private JComboBox<String> restaCombo;
    private JRadioButton division1, division2, division3;
    private JSlider multiplicacionSlider;
    private JSpinner complejaSpinner;
    
    private JLabel resultadoSuma, resultadoResta, resultadoDivision, resultadoMultiplicacion, resultadoCompleja;

    // Para almacenar los resultados seleccionados
    private int suma, resta, division, multiplicacion, compleja;

    public AplicacionConPropositosGeotermicos() {
        frame = new JFrame("Navegación entre Páginas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Cambiar las dimensiones de la ventana a un tamaño más grande
        frame.setSize(600, 400);  // Dimensiones ajustadas para que no se vea saturado
        frame.setLocationRelativeTo(null);  // Esto centra la ventana en la pantalla del usuario
        
        crearPaginas();
        frame.setContentPane(pagina1);
        frame.setVisible(true);
    }

    private void crearPaginas() {
        // Página 1 (solo navegación)
        pagina1 = new JPanel(new BorderLayout());
        JButton botonPagina2 = new JButton("Ir a Página 2");
        JPanel botonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonPanel1.add(botonPagina2);
        pagina1.add(botonPanel1, BorderLayout.SOUTH);

        // Acción para cambiar a la Página 2
        botonPagina2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(pagina2);
                frame.revalidate();
            }
        });

        // Página 2 (selección de datos con margen)
        pagina2 = new JPanel(new BorderLayout());

        JPanel inputsPanel = new JPanel();
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));
        inputsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Márgenes en los lados

        // Suma: TextField con validación
        sumaField = new JTextField(10);
        JLabel sumaLabel = new JLabel("Ingrese un valor para Suma:");
        inputsPanel.add(sumaLabel);
        inputsPanel.add(sumaField);

        // Resta: ComboBox
        JLabel restaLabel = new JLabel("Seleccione un valor para Resta:");
        String[] opcionesResta = {"10", "20", "30", "40", "50"};
        restaCombo = new JComboBox<>(opcionesResta);
        inputsPanel.add(restaLabel);
        inputsPanel.add(restaCombo);

        // División: RadioButtons
        JLabel divisionLabel = new JLabel("Seleccione una opción para División:");
        division1 = new JRadioButton("1");
        division2 = new JRadioButton("2");
        division3 = new JRadioButton("3");
        ButtonGroup groupDivision = new ButtonGroup();
        groupDivision.add(division1);
        groupDivision.add(division2);
        groupDivision.add(division3);
        JPanel divisionPanel = new JPanel();
        divisionPanel.add(division1);
        divisionPanel.add(division2);
        divisionPanel.add(division3);
        inputsPanel.add(divisionLabel);
        inputsPanel.add(divisionPanel);

        // Multiplicación: Slider
        JLabel multiplicacionLabel = new JLabel("Seleccione un valor para Multiplicación:");
        multiplicacionSlider = new JSlider(1, 10);
        multiplicacionSlider.setMajorTickSpacing(1);
        multiplicacionSlider.setPaintTicks(true);
        multiplicacionSlider.setPaintLabels(true);
        inputsPanel.add(multiplicacionLabel);
        inputsPanel.add(multiplicacionSlider);

        // Compleja: Spinner
        JLabel complejaLabel = new JLabel("Seleccione un valor para Compleja:");
        SpinnerModel model = new SpinnerNumberModel(0, 0, 9, 1); // Valores de 0 a 9
        complejaSpinner = new JSpinner(model);
        inputsPanel.add(complejaLabel);
        inputsPanel.add(complejaSpinner);

        // Crear un panel para el botón
        JPanel botonPanel2 = new JPanel(new BorderLayout());
        JButton botonPagina3 = new JButton("Ir a Página 3");
        botonPanel2.add(botonPagina3, BorderLayout.CENTER); // Este botón ocupará todo el ancho

        // Acción para validar y pasar a la Página 3
        botonPagina3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar si el valor de Suma está vacío
                if (sumaField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor introduce un valor de Suma");
                    return;
                }

                // Validar si el valor de Suma es numérico
                try {
                    suma = Integer.parseInt(sumaField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error, solo se permiten números en Suma");
                    return;
                }

                // Validar si se seleccionó una opción de División
                if (!division1.isSelected() && !division2.isSelected() && !division3.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Por favor selecciona una opción de división");
                    return;
                }

                // Si todas las validaciones pasan, obtener los otros valores y cambiar a Página 3
                resta = Integer.parseInt((String) restaCombo.getSelectedItem());
                division = division1.isSelected() ? 1 : division2.isSelected() ? 2 : 3;
                multiplicacion = multiplicacionSlider.getValue();
                compleja = (int) complejaSpinner.getValue();

                // Actualizar los labels en la Página 3
                actualizarResultados();
                frame.setContentPane(pagina3);
                frame.revalidate();
            }
        });
        
        // Añadir el panel de botones y el panel de entradas a la página
        pagina2.add(inputsPanel, BorderLayout.CENTER);
        pagina2.add(botonPanel2, BorderLayout.SOUTH); // Añadir el panel del botón al sur

        // Página 3 (resultados)
        pagina3 = new JPanel(new BorderLayout());

        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new BoxLayout(resultadosPanel, BoxLayout.Y_AXIS));

        resultadoSuma = new JLabel("La suma es: ");
        resultadoResta = new JLabel("La resta es: ");
        resultadoDivision = new JLabel("La división es: ");
        resultadoMultiplicacion = new JLabel("La multiplicación es: ");
        resultadoCompleja = new JLabel("El valor complejo es: ");
        
        resultadosPanel.add(resultadoSuma);
        resultadosPanel.add(resultadoResta);
        resultadosPanel.add(resultadoDivision);
        resultadosPanel.add(resultadoMultiplicacion);
        resultadosPanel.add(resultadoCompleja);

        // Botón para reiniciar y volver a Página 2
        JButton reiniciarBtn = new JButton("Reiniciar y volver a Página 2");
        reiniciarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reiniciar los valores
                sumaField.setText("");
                restaCombo.setSelectedIndex(0);
                groupDivision.clearSelection();
                multiplicacionSlider.setValue(1);
                complejaSpinner.setValue(0);

                // Volver a la página 2
                frame.setContentPane(pagina2);
                frame.revalidate();
            }
        });
        pagina3.add(resultadosPanel, BorderLayout.CENTER);
        pagina3.add(reiniciarBtn, BorderLayout.SOUTH);
    }

    private void actualizarResultados() {
        resultadoSuma.setText("La suma es: " + suma);
        resultadoResta.setText("La resta es: " + resta);
        resultadoDivision.setText("La división es: " + division);
        resultadoMultiplicacion.setText("La multiplicación es: " + multiplicacion);
        resultadoCompleja.setText("El valor complejo es: " + compleja);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplicacionConPropositosGeotermicos();
            }
        });
    }
}


//patata
