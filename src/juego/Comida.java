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
		this.imagen= this.verdura ? Herramientas.cargarImagen("coffe.png") : Herramientas.cargarImagen("papitas.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.yellow);
		e.dibujarImagen(imagen, x, y, 0);
	}
	
	public void mover() {
		x-=velocidad;
		y=this.verdura ? 300+100*Math.sin(x/100):300+100*Math.cos(x/50);
	}
	public boolean fueraDePantalla() {
		if(x<0) {
			return true;
		}
		return false;
	}
	
	public boolean comido(Pajaro bird) {
		double[] pajaro=bird.damePajaro();
		double der=(x+ancho/2);
		double izq=(x-ancho/2);
		double arriba=(y-alto/2);
		double abajo=(y+alto/2);
		if(pajaro[0]<=der && pajaro[0]>=izq && pajaro[1]>=arriba && pajaro[1]<=abajo) {
			return true;
		}
		return false;
	}
	
	public void convertido(Disparo shot) {
		double[] disparo=shot.dameDisparo();
		double der=(x+ancho/2);
		double izq=(x-ancho/2);
		double arriba=(y-alto/2);
		double abajo=(y+alto/2);
		if(disparo[0]<=der && disparo[0]>=izq && disparo[1]>=arriba && disparo[1]<=abajo) {
			this.convertida=true;
			this.imagen= this.verdura ? Herramientas.cargarImagen("coffe.png") : Herramientas.cargarImagen("papitas.png");
		}
	}
}
