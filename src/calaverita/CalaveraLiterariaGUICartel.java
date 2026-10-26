package calaverita;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CalaveraLiterariaGUICartel
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Calavera Literaria de Luis Alvares");
        frame.setSize(750, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel con degradado de fondo (Morado profundo a Negro)
        JPanel panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(48, 25, 52), 0, getHeight(), Color.BLACK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 6, true)); // Borde Cempasúchil

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setOpaque(false); // Transparente para ver el degradado
        StyledDocument doc = textPane.getStyledDocument();

        // Estilos GUI - Nueva fuente
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        
        Style regular = doc.addStyle("regular", defaultStyle);
        StyleConstants.setFontFamily(regular, "Times New Roman"); // Fuente cambiada
        StyleConstants.setFontSize(regular, 22); // Tamaño más grande
        StyleConstants.setForeground(regular, Color.WHITE); // Texto blanco
        StyleConstants.setItalic(regular, true);

        Style luisStyle = doc.addStyle("luis", regular);
        StyleConstants.setForeground(luisStyle, new Color(255, 140, 0)); // Naranja Cempasúchil
        StyleConstants.setBold(luisStyle, true);

        Style keywordStyle = doc.addStyle("keyword", regular);
        StyleConstants.setForeground(keywordStyle, new Color(228, 0, 124)); // Rosa Mexicano
        StyleConstants.setBold(keywordStyle, true);

        // Insertar texto GUI de la Calaverita
        try
        {
            doc.insertString(doc.getLength(), "\nEstaba en clase aburrido, ", regular);
            doc.insertString(doc.getLength(), "Luis Alvares", luisStyle);
            doc.insertString(doc.getLength(), " muy atento,\ncuando llegó la calaca, arruinando su momento.\nLe dijo con voz de frío: “Deja ya ese ", regular);
            doc.insertString(doc.getLength(), "cuaderno", keywordStyle);
            doc.insertString(doc.getLength(), ",\nte vienes conmigo ahora, directo para el infierno”.\n\n", regular);

            doc.insertString(doc.getLength(), "Luis", luisStyle);
            doc.insertString(doc.getLength(), " la miró de reojo, sin perder la compostura,\nle entregó su lapicero y le dijo con premura:\n“Si me llevas, te encargas de pasar esta ", regular);
            doc.insertString(doc.getLength(), "asignatura", keywordStyle);
            doc.insertString(doc.getLength(), "”.\nLa huesuda dio un pasito, temblando de la locura.\n\n", regular);

            doc.insertString(doc.getLength(), "“Mejor me voy solitita”, gritó la muerte asustada,\n“yo no hago tus exámenes, ni me gusta desvelada”.\nY así fue como ", regular);
            doc.insertString(doc.getLength(), "Luis", luisStyle);
            doc.insertString(doc.getLength(), ", con sonrisa dibujada,\nse libró del más allá por una ", regular);
            doc.insertString(doc.getLength(), "tarea pesada", keywordStyle);
            doc.insertString(doc.getLength(), ".\n", regular);

        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }

        // Hacer el ScrollPane completamente transparente
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nuevo color de botón
        JButton btnPDF = new JButton("Generar PDF para Luis");
        btnPDF.setBackground(new Color(75, 0, 130)); // Morado Oscuro (Índigo)
        btnPDF.setForeground(Color.WHITE);
        btnPDF.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 20));
        btnPDF.setFocusPainted(false);

        btnPDF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document();
                    PdfWriter.getInstance(pdfDocument, new FileOutputStream("CalaveraLuisAlvares.pdf"));
                    pdfDocument.open();
                    
                    pdfDocument.add(new Paragraph("Estaba en clase aburrido, Luis Alvares muy atento,"));
                    pdfDocument.add(new Paragraph("cuando llegó la calaca, arruinando su momento."));
                    pdfDocument.add(new Paragraph("Le dijo con voz de frío: 'Deja ya ese cuaderno,"));
                    pdfDocument.add(new Paragraph("te vienes conmigo ahora, directo para el infierno'.\n\n"));
                    
                    pdfDocument.add(new Paragraph("Luis la miró de reojo, sin perder la compostura,"));
                    pdfDocument.add(new Paragraph("le entregó su lapicero y le dijo con premura:"));
                    pdfDocument.add(new Paragraph("'Si me llevas, te encargas de pasar esta asignatura'."));
                    pdfDocument.add(new Paragraph("La huesuda dio un pasito, temblando de la locura.\n\n"));
                    
                    pdfDocument.add(new Paragraph("'Mejor me voy solitita', gritó la muerte asustada,"));
                    pdfDocument.add(new Paragraph("'yo no hago tus exámenes, ni me gusta desvelada'."));
                    pdfDocument.add(new Paragraph("Y así fue como Luis, con sonrisa dibujada,"));
                    pdfDocument.add(new Paragraph("se libró del más allá por una tarea pesada."));
                    
                    pdfDocument.close();
                    JOptionPane.showMessageDialog(frame, "¡PDF generado con éxito!");
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error al generar PDF");
                }
            }
        });

        // 1. Cargamos tu imagen de la calaverita a la derecha
        ImageIcon iconoDerecho = new ImageIcon("calaverita.jpg");
        Image imgDer = iconoDerecho.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel etiquetaDerecha = new JLabel(new ImageIcon(imgDer));
        etiquetaDerecha.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        // 2. Creamos "flores" usando texto HTML y Emojis para la izquierda
        // Usamos <br> para dar saltos de línea y que se vean hacia abajo
        JLabel etiquetaIzquierda = new JLabel("<html><div style='text-align: center;'>🏵️<br><br>🌺<br><br>🏵️</div></html>");
        etiquetaIzquierda.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 45)); // Tamaño gigante para que parezca decoración
        etiquetaIzquierda.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        // 3. Ensamblamos todo en el panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(etiquetaDerecha, BorderLayout.EAST); 
        panel.add(etiquetaIzquierda, BorderLayout.WEST); // Emojis de flores a la izquierda
        panel.add(btnPDF, BorderLayout.SOUTH);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}