package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double velocidad;
	private boolean enPantalla;
	private Image image;
	
	public Disparo(double x,double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.ancho=32;
		this.alto=32;
		this.velocidad = velocidad;
		this.image = Herramientas.cargarImagen("fireball.gif");
		this.enPantalla=true;
	}
	
	public void dibujar(Entorno e) {
		if(enPantalla) {
			e.dibujarImagen(image, x, y, Math.PI/2, 1);
		}
	}

	// cuidado!
	public void estaEnPantalla(Entorno e) {
		enPantalla= y<0 || y>e.alto() || x<0 || x>e.ancho() ? false:true;
	}
	
	public void mover(Entorno e) {
		if(enPantalla) {
			x+=velocidad;
			return;
		}
	}
	
	public double[] dameDisparo() {
		double[] a= {this.x,this.y,this.ancho,this.alto};
		return a;
	}
	
	public boolean fueraDePantalla(Entorno e) {
		if(x-ancho>e.ancho()) {
			return true;
		}
		return false;
	}
	
}