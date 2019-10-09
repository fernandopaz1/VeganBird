package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Comida {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double velocidad;
	private boolean verdura;
	private boolean convertida;
	private Image imagen;
	
	public Comida(double x, double y, double ancho, double alto, double velocidad, int verdura) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
		this.verdura = verdura==1 ? true:false;
		this.convertida=false;
		this.imagen= this.verdura ? Herramientas.cargarImagen("Brocoli.png") : Herramientas.cargarImagen("Hamburguesa.png");
	}
	
	public void dibujar(Entorno e) {
		/*if(this.verdura) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.green);
		e.dibujarImagen(imagen, x, y, 0);
		return;
		}
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.red);
		*/
		e.dibujarImagen(imagen, x, y, 0);
	}
	
	public void mover() {
		x-=velocidad;
		y=300+20*Math.sin(x/50);
	}
	public boolean fueraDePantalla() {
		if(x<0) {
			return true;
		}
		return false;
	}
	
	public double[] dameComida() {
		double[] a= {x,y};
		return a;
	}
	
	
	
	public void convertido(Disparo shot) {
		double[] disparo=shot.dameDisparo();
		double der=(x+ancho/2);
		double izq=(x-ancho/2);
		double arriba=(y-alto/2);
		double abajo=(y+alto/2);
		if(disparo[0]<=der && disparo[0]>=izq && disparo[1]>=arriba && disparo[1]<=abajo) {
			this.convertida=true;
			this.verdura=true;
			this.imagen=Herramientas.cargarImagen("Brocoli.png");
		}
	}
	
	public int damePuntaje(int p) {
		if(!this.verdura) {
			p-=5;
			return p;
		}
		return this.convertida ? p+3 :p+5;
	}
}
