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
	private Image image;
	
	public Disparo(double x,double y) {
		this.x = x;
		this.y = y;
		this.ancho = 32;
		this.alto = 32;
		this.velocidad = 10;
		this.image = Herramientas.cargarImagen("disparo.gif");
	}
	
	public void dibujar(Entorno e) {
			e.dibujarImagen(image, x, y, Math.PI/2, 1);
	}

	public void mover() {
			x += velocidad;
			return;
	}
	
	public double[] dameDisparo() {
		double[] arrayPosicionDeDisparo = {this.x,this.y,this.ancho,this.alto};
		return arrayPosicionDeDisparo;
	}
	
	public boolean fueraDeRango(Entorno e) {
		if (x-ancho > e.ancho()) {
			return true;
		}
		return false;
	}
}