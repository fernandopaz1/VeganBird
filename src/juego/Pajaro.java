package juego;

import java.awt.Color;
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
	
	public Pajaro(double x, double y) {
		this.x = x;
		this.y = y;
		this.alto=40;
		this.ancho=40;
		this.image = Herramientas.cargarImagen("ship.png");
		this.subida=4;
		this.bajada=1;
		
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(image, x, y, 0);
		e.dibujarRectangulo(x, y, 40, 40, 0, Color.yellow);
	}
	
		
	public void mover(Entorno e) {	
		if (e.estaPresionada(e.TECLA_ARRIBA) && !this.tocaTecho(e)) {
			y-=subida;
			return;
		}
		if(!this.tocaSuelo(e)) {
			y+=bajada;
		}
	}
	
	
	public Disparo disparar() {
		// TODO Auto-generated method stub
		return new Disparo(x, y, 0, 10);
	}
	
	public boolean tocaSuelo(Entorno e) {	
		return this.y>e.alto() ? true: false;
	}
	
	public boolean tocaTecho(Entorno e) {	
		return this.y<0 ? true: false;
	}
	
	
	public boolean choca(Obstaculo a,Entorno e) {
		double[] obstaculo= a.dameObstaculo();
		double der=(obstaculo[0]+obstaculo[2]/2);
		double izq=(obstaculo[0]-obstaculo[2]/2);
		double alto=(obstaculo[1]-obstaculo[3]/2);
		double alto2=(obstaculo[1]-450+obstaculo[3]/2);
		if((x<=der && x>=izq && y>=alto) || (x<=der && x>=izq && y<=(alto2))) {
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
