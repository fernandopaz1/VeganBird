package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pajaro {
		
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image image;
	
	private double velocidadDeSubida;
	private double velocidadDeBajada;
	
	private int coolDown;
	
	public Pajaro(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho=40;
		this.alto=40;
		this.image = Herramientas.cargarImagen("gallina.png");
		this.velocidadDeSubida=50;
		this.velocidadDeBajada=2;
		this.coolDown=0;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(image, x, y, 0);
	}
	
	public void caer() {
		y += velocidadDeBajada;
	}
	
	public void subir() {
		y -= velocidadDeSubida;
	}
	
	public boolean puedeDisparar() {
		if(coolDown<=0) {
			return true;}
		coolDown--;
		return false;
	}
	
	public Disparo disparar() {
		// Hacer un arreglo de disparado para que aparezcan muchos tiros en pantalla y se puedan convertir varias comidas a la vez
		coolDown=50;
		return new Disparo(x, y, 10);
	}
	
	public boolean tocaSueloOTecho(Entorno e, Obstaculo suelo) {
		double ground[]=suelo.dameObstaculo();
		return this.y>(e.alto()-ground[3]/4) || this.y<0 ? true: false;
	}
	
	
	public boolean choca(Obstaculo tubo, Obstaculo suelo, Entorno e) {
		double[] obstaculo= tubo.dameObstaculo();
		double der=(obstaculo[0]+obstaculo[2]/2);
		double izq=(obstaculo[0]-obstaculo[2]/2);
		double alto1=(obstaculo[1]-obstaculo[3]/2);
		double alto2=(obstaculo[1]-obstaculo[4]+obstaculo[3]/2);
		if((x<=der && x>=izq && y>=alto1) || (x<=der && x>=izq && y<=(alto2)) || this.tocaSueloOTecho(e,suelo)) {
			return true;
		}
		return false;
	}
	
	public boolean seComioLa(Comida comida) {
		double[] morfi=comida.dameComida();
		double der=(x+ancho/2);
		double izq=(x-ancho/2);
		double arriba=(y-alto/2);
		double abajo=(y+alto/2);
		if(morfi[0]<=der && morfi[0]>=izq && morfi[1]>=arriba && morfi[1]<=abajo) {
			return true;
		}
		return false;
	}
	
	
	public double[] damePajaro() {
		double[] a= {x,y,ancho,alto};
		return a;
	}

	
	
}