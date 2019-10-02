package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

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
	}
	
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.yellow);
	}
	
	public void mover() {
		x-=velocidad;
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
	
}
