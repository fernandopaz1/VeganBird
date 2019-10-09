package juego;



import java.awt.Image;

import entorno.Entorno;


public class Obstaculo {
	private double x;
	private double y;
	private double velocidad;
	private Image imagen;
	private double ancho;
	private double alto;
	private double aberturaTubo;
	
	public Obstaculo(double x, double y,double ancho,double alto, double a,Image imagen) {
		this.x = x;
		this.y = y;
		this.velocidad = 1;
		this.ancho=ancho;
		this.alto=alto;
		this.aberturaTubo=a;
		this.imagen = imagen;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0, 1);
		if(this.aberturaTubo!=0) {
		e.dibujarImagen(imagen, x, y-aberturaTubo, Math.PI, 1);
		}
	}
	
	public void mover(Entorno e) {
		x-=velocidad;
		if(this.aberturaTubo!=0) {
			double f=100;
			double a=40;
			y+=a*Math.sin((x+y)/f)/f;
		}
		
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
