package com.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.settings.*;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.Table.TableBuilder;
import org.vandeseer.easytable.structure.cell.ImageCell;
import org.vandeseer.easytable.structure.cell.TextCell;

import static java.awt.Color.*;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.*;
import static org.vandeseer.easytable.settings.HorizontalAlignment.*;
import static org.vandeseer.easytable.settings.VerticalAlignment.MIDDLE;
import static org.vandeseer.easytable.settings.VerticalAlignment.TOP;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.ArrayList;

public class ReportPdf {

	public static void main(String[] args) throws IOException{
		
		//Crea un alumno u obtiene los datos de uno, guarda las calif en una lista
		//Este modelo y lo datos a usar se puede modificar para que coincida con la BD
		Alumno alumno = new Alumno();
		
		//Crea el documento vacio
		PDDocument document = new PDDocument();
		
		//Crea una pagina y la agrega al documento vacio
		final PDPage pag = new PDPage(PDRectangle.A4);
        document.addPage(pag);
		
        //Obtiene la primera pagina del documento
        final PDPage page = document.getPage(0);
        //Carga una imagen para mostrar en el logo de la institución o sistema, el archivo esta en la carpeta raiz
		PDImageXObject logo = PDImageXObject.createFromFile("logo.png", document);
		
		
		//Codigo para el llenado de la tabla a traves de un contentStream o el contenido de la pagina
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            //Crear una tabla para las calificciones
            //Inicializa una tabla con los valores de cabecera
            final TableBuilder table = Table.builder()
            		// addRow inserta un fila y Row.builder() el contenido
            		.addRow(Row.builder()
            				//En .add se agrega el contenido de la celda (texto o imagen), colSpan determina cuantas columnas ocupara
            				//De acuerdo con el contenido el documento consta de cuatro columnas
            				.add(TextCell.builder().text("Sistema Integral de Administración Escolar SIAE")
                            		.colSpan(3)
                                    .font(HELVETICA_BOLD_OBLIQUE)
                                    .verticalAlignment(MIDDLE)
                                    .horizontalAlignment(HorizontalAlignment.CENTER)
                                    .build())
		                    .add(ImageCell.builder()
		                            .verticalAlignment(MIDDLE)
		                            .horizontalAlignment(LEFT)
		                            //En esta parte se inserta la imagen o logo 
		                            .image(logo)
		                            .scale(0.07f)
		                            .build())
		                    		.build())
            		//Agrega espacio en blanco
            		.addRow(Row.builder()
                            .add(TextCell.builder().text("")
                            		.colSpan(4)
                                    .build())
		                            .build())
            		.addRow(Row.builder()
                            .add(TextCell.builder().text("Reporte de calificaciones")
                            		.borderWidthTop(1)
                                    .colSpan(2)
                                    .lineSpacing(1f)
                                    .horizontalAlignment(LEFT)
                                    .font(HELVETICA_BOLD_OBLIQUE)
                                    .build())
                            .add(TextCell.builder().text("Folio: 00054651")
                            		.borderWidthTop(1)
                            		.colSpan(2)
                                    .font(HELVETICA_BOLD_OBLIQUE)
                                    .verticalAlignment(TOP)
                                    .horizontalAlignment(RIGHT)
                                    .build())
		                            .build())
            		//Agrega espacio en blanco
            		.addRow(Row.builder()
                            .add(TextCell.builder().text("")
                            		.colSpan(4)
                                    .build())
		                            .build())
            		//Se define el tamaño de cada columna
            		.addColumnsOfWidth(100,150,100,150)
            		//Inicia a llenar los datos del alumno, obtenidos de la creacion del objeto Alumno()
            		.addRow(Row.builder()
                            .padding(5)
                            .add(TextCell.builder().text("Nombre:").build())
                            .add(TextCell.builder().text(alumno.getNombre()+" "+alumno.getApellidos()).build())
                            .add(TextCell.builder().text("Matricula:").build())
                            .add(TextCell.builder().text(String.valueOf(alumno.getMatricula())).build())
                            .build())
            		.addRow(Row.builder()
                            .padding(5)
                            .add(TextCell.builder().text("Grado:").build())
                            .add(TextCell.builder().text(String.valueOf(alumno.getGrado())).build())
                            .add(TextCell.builder().text("Grupo:").build())
                            .add(TextCell.builder().text(String.valueOf(alumno.getGrupo())).build())
                            .build())
            		.addRow(Row.builder()
                            .padding(5)
                            .add(TextCell.builder().text("Plan de estudio:").build())
                            .add(TextCell.builder().text(alumno.getPlanEstudios()).build())
                            .add(TextCell.builder().text("Tutor:").build())
                            .add(TextCell.builder().text(alumno.getNombreTutor()).build())
                            .build())
		            //Agrega espacio en blanco
		            .addRow(Row.builder()
		                    .add(TextCell.builder().text("")
		                    		.colSpan(4)
		                            .build())
		                            .build())
		            //Agrega el encabezado de la tabla para las calificaciones
		            .addRow(Row.builder()
		                    .padding(5)
		                    .add(TextCell.builder().text("Materia:").build())
		                    .add(TextCell.builder().text("Semestre").build())
		                    .add(TextCell.builder().text("Calificación:").build())
		                    .add(TextCell.builder().text("Comentario").build())
		                    .borderWidth(1)
		                    .backgroundColor(LIGHT_GRAY)
		                    .build());

            //Guardar las calificaciones en una lista para su recorrido
            ArrayList<Calificaciones> calif = alumno.getCalificaciones();
            
            //Llenado autmatico de la tabla de las calificaciones
            for(Calificaciones c:calif) {
            	table.addRow(Row.builder()
                		.padding(5)
                        .add(TextCell.builder().text(c.getCurso()).build())
                        .add(TextCell.builder().text(c.getGrado()).build())
                        .add(TextCell.builder().text(String.valueOf(c.getCalificacion()))
                        		.horizontalAlignment(HorizontalAlignment.CENTER)
                        		.build())
                        .add(TextCell.builder().text(c.getTipoEvaluacion()).build())
                        .borderWidth(1)
                        .build())
                .build();
    		}
            
            // Se junta todos los componentes del documento descritos ateriormente
            TableDrawer tableDrawer = TableDrawer.builder()
            		//Se agrega el contenido de la pagina en este caso la tabla
                    .contentStream(contentStream)
                    //Se establece el punto de inicio, la pagina es de 500, la tabla de 400, inicia en 50 y tiene libre 50 (centrado)
                    .startX(50f)
                    //Obtiene la altura de la pagina y establece el inicio del punto mayor menos 50
                    .startY(page.getMediaBox().getUpperRightY() - 50f)
                    //establece el fin de la pagina, si el contenido es mayor se crea una nueva (NO FUNCIONA)
                    .endY(50F)
                    .table(table.build())
                    .build();

            // And go for it!
            tableDrawer.draw();
        }

        //Guarda los cambios del documento, por defecto en la raiz de la carpeta, se puede cambiar
        document.save("ReporteCalif.pdf");
        System.out.println("Generado con exito!");

	}

}
