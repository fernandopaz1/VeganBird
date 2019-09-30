package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Ship {
		
	private double x;
	private double y;
	double direccion;
	private Image image;
	private double velocidad;
	
	public Ship(double x, double y) {
		this.x = x;
		this.y = y;
		this.direccion = 0;
		this.image = Herramientas.cargarImagen("ship.png");
		this.velocidad=2;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(image, x, y, direccion);
	}

	public void girarAntihorario() {
		direccion -= 0.1;
	}
	
	public void girarHorario() {
		direccion += 0.1;
	}
	
	public boolean estaEnPantalla(Entorno e) {
		return y<0 || y>e.alto() || x<0 || x>e.ancho() ? false:true;	
	}
	
	public void mover(Entorno e) {
		if(this.estaEnPantalla(e)) {	
			if (e.estaPresionada(e.TECLA_DERECHA)) {
				x+=velocidad;
			}
			
			if (e.estaPresionada(e.TECLA_IZQUIERDA)) {
				x-=velocidad;
			}
			if (e.estaPresionada(e.TECLA_ARRIBA)) {
				y-=velocidad;
			}
			
			if (e.estaPresionada(e.TECLA_ABAJO)) {
				y+=velocidad;
			}
		}
	}
	
	
	public Disparo disparar() {
		// TODO Auto-generated method stub
		return new Disparo(x, y, direccion, 10);
	}
	
	
	public double distanciaA(double[] p) {
		double d= Math.sqrt(Math.pow(this.x-p[0], 2)+Math.pow(this.y-p[1], 2));
		return d;
	}
	
	public boolean explota(Asteroid a,Entorno e) {
		double distancia= this.distanciaA(a.dameAsteroide());
		if(distancia<10) {
			e.dibujarCirculo(x, y, 20, Color.RED);
			return true;
		}
		return false;
	}
	
}
