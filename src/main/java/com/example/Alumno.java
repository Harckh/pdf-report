package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Alumno {

	int matricula;
	String nombre;
	String apellidos;
	String planEstudios;
	String nombreTutor;
	int grado;
	char grupo;
	ArrayList<Calificaciones> calificaciones;
	
	public Alumno() {
		Random r = new Random();
		matricula = r.nextInt(10000)+1000;
		nombre = "Alumno";
		apellidos = "Apellidos alumno";
		planEstudios = "Plan 20XX";
		nombreTutor = "Nombre del tutor";
		grado = 6;
		grupo = 'A';
		calificaciones = new ArrayList<Calificaciones>();
		for(int i=0;i<20;i++) {
			Calificaciones c = new Calificaciones();
			calificaciones.add(c);
		}
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getPlanEstudios() {
		return planEstudios;
	}
	public void setPlanEstudios(String planEstudios) {
		this.planEstudios = planEstudios;
	}
	public String getNombreTutor() {
		return nombreTutor;
	}
	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}
	public int getGrado() {
		return grado;
	}
	public void setGrado(int grado) {
		this.grado = grado;
	}
	public char getGrupo() {
		return grupo;
	}
	public void setGrupo(char grupo) {
		this.grupo = grupo;
	}
	public ArrayList<Calificaciones> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(ArrayList<Calificaciones> calificaciones) {
		this.calificaciones = calificaciones;
	}
	
	
}
