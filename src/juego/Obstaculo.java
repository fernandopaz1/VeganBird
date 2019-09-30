package juego;

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
		e.dibujarImagen(imagen, x, y, 0, 0.08);;
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
