package juego;



import java.awt.Image;

import entorno.Entorno;


public class Obstaculo {
	
	private static final int FRECUENCIA_DE_MOVIMIENTO_VERTICAL = 100;
	
	private double x;
	private double y;
	private double velocidad;
	private Image imagen;
	private double ancho;
	private double alto;
	private double abertura;
	private boolean esSuelo;
	
	public Obstaculo(double x, double y, boolean suelo,Image imagen) {
		this.x = x;
		this.y = y;
		this.velocidad = 1;
		this.esSuelo=suelo;
		if (suelo) {
			this.ancho=800;
			this.alto=250;
			this.abertura=0;
		}else {
			this.ancho=75;
			this.alto=500;
			this.abertura=650;
		}
		
		this.imagen = imagen;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0, 1);
		if(esSuelo==false) {
			e.dibujarImagen(imagen, x, y-abertura, Math.PI, 1);
		}
	}
	
	public void mover(Entorno e) {
		x-=velocidad;
		if(this.abertura!=0) {
			double f=FRECUENCIA_DE_MOVIMIENTO_VERTICAL;
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
		double[] a= {x,y,ancho,alto,abertura};
		return a;
	}
}