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
	private double aberturaTubo;
	
	public Obstaculo(double x, double y,double ancho,double alto, double a) {
		this.x = x;
		this.y = y;
		this.velocidad = 1;
		this.ancho=ancho;
		this.alto=alto;
		this.aberturaTubo=a;
		this.imagen = Herramientas.cargarImagen("tuboresize.png");
	}
	
	public void dibujar(Entorno e) {
		//e.dibujarRectangulo(x, y, ancho, alto, 0, Color.red);
		//e.dibujarRectangulo(x, y-aberturaTubo, ancho, alto, Math.PI, Color.red);
		e.dibujarImagen(imagen, x, y, 0, 1);
		e.dibujarImagen(imagen, x, y-aberturaTubo, Math.PI, 1);
	}
	
	public void mover(Entorno e) {
		x-=velocidad;
		y+=0.1*Math.sin(x/100);
		
	}
	public boolean fueraDePantalla(Entorno e) {
		if((x+ancho)<0) {
			return true;
		}
		return false;
	}

	public double[] dameObstaculo() {
		double[] a= {x,y,ancho,alto,aberturaTubo};
		return a;
	}
}
