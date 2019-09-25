package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Asteroid {

	private double x;
	private double y;
	private double direccion;
	private double velocidad;
	private Image image;
	private double size;
	
	public Asteroid(double x, double y) {
		this.x = x;
		this.y = y;
		this.direccion = 3 * Math.PI / 4;
		this.size = 80;
		this.velocidad = 2;
		this.image = Herramientas.cargarImagen("asteroid.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(image, x, y, direccion+Math.PI/2);
	}
	
	public void mover() {
		x += velocidad * Math.cos(direccion);
		y += velocidad * Math.sin(direccion);
	}
	
	public void rebotar(Entorno e) {
		if (0 > x - size/2 || e.ancho() <= x + size/2 ||
				0 > y - size/2 || e.alto() <= y + size/2) {
			direccion += Math.PI/2;
		}
	}
	
}
