package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Ship {
		
	double x;
	double y;
	double direccion;
	private Image image;
	
	public Ship(double x, double y) {
		this.x = x;
		this.y = y;
		this.direccion = 0;
		this.image = Herramientas.cargarImagen("ship.png");
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

	public Disparo disparar() {
		// TODO Auto-generated method stub
		return new Disparo(x, y, direccion, 10);
	}

}
