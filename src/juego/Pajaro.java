package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pajaro {
		
	private double x;
	private double y;
	private Image image;
	private double subida;
	private double bajada;
	
	public Pajaro(double x, double y) {
		this.x = x;
		this.y = y;
		this.image = Herramientas.cargarImagen("ship.png");
		this.subida=4;
		this.bajada=1;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(image, x, y, 0);
	}
	
		
	public void mover(Entorno e) {	
		if (e.estaPresionada(e.TECLA_ESPACIO) && !this.tocaTecho(e)) {
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
		double alto2=(obstaculo[1]-400+obstaculo[3]/2);
		if((x<=der && x>=izq && y>=alto) || (x<=der && x>=izq && y<=(alto2))) {
			return true;
		}
		return false;
	}
	
	public double[] damePajaro() {
		double[] a= {x,y};
		return a;
	}
	
}
