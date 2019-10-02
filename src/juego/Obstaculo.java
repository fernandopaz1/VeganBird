package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private double x;
	private double y;
	private double velocidad;
	private Image imagen;
	private double ancho;
	private double alto;
	
	public Obstaculo(double x, double y,double ancho,double alto, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.ancho=ancho;
		this.alto=alto;
		this.imagen = Herramientas.cargarImagen("tubo.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0, 0.08);
		e.dibujarImagen(imagen, x, y-400, Math.PI, 0.08);
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.red);
		e.dibujarRectangulo(x, y-400, ancho, alto, Math.PI, Color.red);
	}
	
	public void mover(Entorno e) {
		if(x<0) {
			x=e.ancho();
		}
		x-=velocidad;
		
	}

	public double[] dameObstaculo() {
		double[] a= {x,y,ancho,alto};
		return a;
	}
}
