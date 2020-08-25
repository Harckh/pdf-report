package com.example;

import java.util.Random;

public class Calificaciones {

	String curso;
	String grado;
	int calificacion;
	String tipoEvaluacion;
	
	public Calificaciones() {
		Random r = new Random();
		
		curso = "NombreCurso";
		grado = "Grado";
		calificacion = r.nextInt(6)+5;
		tipoEvaluacion = "Ordinario";
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}
	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}
	
	
}
