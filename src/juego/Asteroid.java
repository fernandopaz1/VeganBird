package juego;

import java.awt.Color;
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
	
	public double[] dameAsteroide() {
		double[] a= {this.x,this.y};
		return a;
	}
	
	public double distanciaA(double[] p) {
		double d= Math.sqrt(Math.pow(this.x-p[0], 2)+Math.pow(this.y-p[1], 2));
		return d;
	}
	
	public boolean explota(Disparo disparo, Entorno e) {
		double distancia= this.distanciaA(disparo.dameDisparo());
		if(distancia<=this.size/2) {
			e.dibujarCirculo(x, y, size, Color.RED);
			return true;
		}
		return false;
	}
}
