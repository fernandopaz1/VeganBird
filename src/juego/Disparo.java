package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {
	
	double x;
	double y;
	double direccion;
	double velocidad;
	boolean enPantalla;
	private Image image;
	
	
	public Disparo(double x,double y,double direccion, double velocidad) {
		this.x = x;
		this.y = y;
		this.direccion = direccion-Math.PI/2;
		this.velocidad = velocidad;
		this.image = Herramientas.cargarImagen("fireball.gif");
		this.enPantalla=true;
	}
	
	
	public void dibujar(Entorno e) {
		if(enPantalla) {
			e.dibujarImagen(image, x, y, direccion+Math.PI/2, 1);
		}
	}
	
	public void estaEnPantalla(Entorno e) {
		enPantalla= y<0 || y>e.alto() || x<0 || x>e.ancho() ? false:true;
		
	}
	
	public void mover(Entorno e) {
		if(enPantalla) {
			x+=velocidad*Math.cos(direccion);
			y+=velocidad*Math.sin(direccion);
			return;
		}
	}
	
}
