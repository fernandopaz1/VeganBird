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
	private boolean enRango;
	private Image image;
	
	public Disparo(double x,double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.ancho = 32;
		this.alto = 32;
		this.velocidad = velocidad;
		this.image = Herramientas.cargarImagen("fireball.gif");
		this.enRango = true;
	}
	
	public void dibujar(Entorno e) {
		if (enRango) {
			e.dibujarImagen(image, x, y, Math.PI/2, 1);
		}
	}

	public void mover() {
		if (enRango) {
			x += velocidad;
			return;
		}
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