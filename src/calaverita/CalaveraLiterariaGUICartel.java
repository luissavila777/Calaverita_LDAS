/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calaverita;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author dcuem
 */
public class CalaveraLiterariaGUICartel
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Calavera Literaria de Daniel y Armeli");
        frame.setSize(750, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel con degradado de fondo
        JPanel panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 228, 225), 0, getHeight(), new Color(255, 182, 193));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 6, true));

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setOpaque(false); // transparente para ver el degradado
        StyledDocument doc = textPane.getStyledDocument();

        // Estilos GUI
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style regular = doc.addStyle("regular", defaultStyle);
        StyleConstants.setFontFamily(regular, "Segoe Script");
        StyleConstants.setFontSize(regular, 18);

        Style danielStyle = doc.addStyle("daniel", regular);
        StyleConstants.setForeground(danielStyle, Color.ORANGE);
        StyleConstants.setBold(danielStyle, true);

        Style armeliStyle = doc.addStyle("armeli", regular);
        StyleConstants.setForeground(armeliStyle, new Color(139, 0, 139));
        StyleConstants.setBold(armeliStyle, true);

        Style keywordStyle = doc.addStyle("keyword", regular);
        StyleConstants.setForeground(keywordStyle, new Color(34, 139, 34)); // verde

        // Insertar texto GUI
        try
        {
            doc.insertString(doc.getLength(), "Estaba sentado esperando su turno, ", regular);
            doc.insertString(doc.getLength(), "Daniel", danielStyle);
            doc.insertString(doc.getLength(), ",\n", regular);
            doc.insertString(doc.getLength(), "y en el hotel se topó con la huesuda cruel.\n", regular);
            doc.insertString(doc.getLength(), "Le habló con dulzura, como la más fina ", regular);
            doc.insertString(doc.getLength(), "miel", keywordStyle);
            doc.insertString(doc.getLength(), ",\n", regular);
            doc.insertString(doc.getLength(), "de su dibujo a pincel plasmado en el ", regular);
            doc.insertString(doc.getLength(), "papel.\n\n", keywordStyle);

            doc.insertString(doc.getLength(), "La huesuda preguntó por su nombre aquel,\n", regular);
            doc.insertString(doc.getLength(), "él respondió orgulloso: “Me llamo ", regular);
            doc.insertString(doc.getLength(), "Armeli", armeliStyle);
            doc.insertString(doc.getLength(), "”.\n", regular);
            doc.insertString(doc.getLength(), "La catrina sonrió y dijo: “¡Qué hombre tan fiel!”,\n", regular);
            doc.insertString(doc.getLength(), "y el catrín celoso murmuró: “Veamos la pelí”.\n\n", regular);

            doc.insertString(doc.getLength(), "Después salió a pasear, con un poco de frío,\n", regular);
            doc.insertString(doc.getLength(), "y la parca le dejó una nota en un ", regular);
            doc.insertString(doc.getLength(), "papel.\n", keywordStyle);
            doc.insertString(doc.getLength(), "La calaca tocó su piel, pero ", regular);
            doc.insertString(doc.getLength(), "Daniel", danielStyle);
            doc.insertString(doc.getLength(), " sonrió,\n", regular);
            doc.insertString(doc.getLength(), "y escapó de la muerte por un misterioso ", regular);
            doc.insertString(doc.getLength(), "túnel.\n", keywordStyle);
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }

        JButton btnPDF = new JButton("Generar PDF");
        btnPDF.setBackground(new Color(255, 20, 147));
        btnPDF.setForeground(Color.WHITE);
        btnPDF.setFont(new java.awt.Font("Segoe Script", java.awt.Font.BOLD, 18));
        btnPDF.setFocusPainted(false);

        btnPDF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    // Usamos nombres completos para evitar conflictos
                    com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document();
                    PdfWriter.getInstance(pdfDocument, new FileOutputStream("CalaveraDanielArmeli.pdf"));
                    pdfDocument.open();
                    pdfDocument.add(new Paragraph("Estaba sentado esperando su turno, Daniel,"));
                    pdfDocument.add(new Paragraph("y en el hotel se topó con la huesuda cruel."));
                    pdfDocument.add(new Paragraph("Le habló con dulzura, como la más fina miel,"));
                    pdfDocument.add(new Paragraph("de su dibujo a pincel plasmado en el papel."));
                    pdfDocument.add(new Paragraph("La huesuda preguntó por su nombre aquel,"));
                    pdfDocument.add(new Paragraph("él respondió orgulloso: 'Me llamo Armeli'."));
                    pdfDocument.add(new Paragraph("La catrina sonrió y dijo: '¡Qué hombre tan fiel!',"));
                    pdfDocument.add(new Paragraph("y el catrín celoso murmuró: 'Mejor veamos la pelí'."));
                    pdfDocument.add(new Paragraph("Después salió a pasear, con un poco de frío,"));
                    pdfDocument.add(new Paragraph("y la parca le dejó una nota en un papel."));
                    pdfDocument.add(new Paragraph("La calaca tocó su piel, pero Daniel sonrió,"));
                    pdfDocument.add(new Paragraph("y escapó de la muerte por un misterioso túnel."));
                    pdfDocument.close();
                    JOptionPane.showMessageDialog(frame, "PDF generado con éxito!");
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error al generar PDF");
                }
            }
        });

        panel.add(new JScrollPane(textPane), BorderLayout.CENTER);
        panel.add(btnPDF, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}
