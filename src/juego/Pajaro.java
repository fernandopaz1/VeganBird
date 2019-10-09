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
	private double subida;
	private double bajada;
	
	public Pajaro(double x, double y,double ancho,double alto) {
		this.x = x;
		this.y = y;
		this.ancho=ancho;
		this.alto=alto;
		this.image = Herramientas.cargarImagen("Bird2.0.gif");
		this.subida=3;
		this.bajada=1;
		
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(image, x, y, 0);
	}
	
		
	public void mover(Entorno e) {	
		if (e.estaPresionada(e.TECLA_ARRIBA)) {
			y-=subida;
			return;
		}
			y+=bajada;

	}
	
	
	public Disparo disparar() {
		// TODO Auto-generated method stub
		return new Disparo(x, y, 10);
	}
	
	public boolean tocaSueloOTecho(Entorno e,Obstaculo suelo) {
		double ground[]=suelo.dameObstaculo();
		return this.y>(e.alto()-ground[3]/4) || this.y<0 ? true: false;
	}
	
	
	public boolean choca(Obstaculo a,Obstaculo suelo,Entorno e) {
		double[] obstaculo= a.dameObstaculo();
		double der=(obstaculo[0]+obstaculo[2]/2);
		double izq=(obstaculo[0]-obstaculo[2]/2);
		double alto1=(obstaculo[1]-obstaculo[3]/2);
		double alto2=(obstaculo[1]-obstaculo[4]+obstaculo[3]/2);
		if((x<=der && x>=izq && y>=alto1) || (x<=der && x>=izq && y<=(alto2)) || this.tocaSueloOTecho(e,suelo)) {
			return true;
		}
		return false;
	}
	
	public boolean comido(Comida comida) {
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
		double[] a= {x,y};
		return a;
	}
	
}
