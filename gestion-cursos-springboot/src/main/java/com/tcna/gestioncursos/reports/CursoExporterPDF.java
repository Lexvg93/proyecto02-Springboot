package com.tcna.gestioncursos.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tcna.gestioncursos.entity.Curso;

import jakarta.servlet.http.HttpServletResponse;

public class CursoExporterPDF {
    private List<Curso>listaCursos;

    public CursoExporterPDF(List<Curso>listaCursos){
        this.listaCursos=listaCursos;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        String[] valores = {"ID","Titulo","Descripcion","Nivel","Publicado"};

        for(String valor:valores ){
            cell.setPhrase(new Phrase(valor,font));
            table.addCell(cell);
        }
    }

    private void writeTableData(PdfPTable table){
        for(Curso curso:listaCursos){
            table.addCell(String.valueOf(curso.getId()));
            table.addCell(curso.getTitulo());
            table.addCell(curso.getDescripcion());
            table.addCell(String.valueOf(curso.getNivel()));
            table.addCell(String.valueOf(curso.isPublicado()));
        }
    }

    public void export(HttpServletResponse response) throws IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Lista de Cursos",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.3f,3.5f,3.5f,2.0f,1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
