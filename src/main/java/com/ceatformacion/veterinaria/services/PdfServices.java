package com.ceatformacion.veterinaria.services;

import com.ceatformacion.veterinaria.model.Mascota;
import com.lowagie.text.*;


import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfServices {
    //generar un pdf con los datos de la mascota
    //generar un pdf con los datos de la mascota y sus historias clinicas
    //crear un metodo para generar un pdf con los datos de la mascota y sus historias clinicas
    public ByteArrayInputStream exportarMascotas(List<Mascota> mascota) {
        Document documento = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(documento, salida);

            documento.open();
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Listado de mascotas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new int[]{3, 3, 3, 3, 3, 3, 3});
            Font encabezadoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15);
            tabla.addCell(new PdfPCell(new Phrase("Id", encabezadoFont)));
            tabla.addCell(new PdfPCell(new Phrase("Nombre", encabezadoFont)));
            tabla.addCell(new PdfPCell(new Phrase("Especie", encabezadoFont)));
            tabla.addCell(new PdfPCell(new Phrase("Raza", encabezadoFont)));
            tabla.addCell(new PdfPCell(new Phrase("Edad", encabezadoFont)));
            tabla.addCell(new PdfPCell(new Phrase("Peso", encabezadoFont)));
            tabla.addCell(new PdfPCell(new Phrase("DNI Propietario", encabezadoFont)));

            for (Mascota m : mascota) {
                tabla.addCell(String.valueOf(m.getIdMascota()));
                tabla.addCell(m.getNombre());
                tabla.addCell(m.getEspecie());
                tabla.addCell(m.getRaza());
                tabla.addCell(String.valueOf(m.getEdad()));
                tabla.addCell(String.valueOf(m.getPeso()));
                tabla.addCell(m.getDniPropietario());
            }
            documento.add(tabla);
            documento.close();




        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(salida.toByteArray());
    }
}
