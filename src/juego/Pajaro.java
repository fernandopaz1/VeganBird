package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Pajaro {
		
	private static final int ANCHO_DE_PAJARO = 40;
	private static final int ALTO_DE_PAJARO = 40;
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private Image image;
	private double velocidadDeSubida;
	private double velocidadDeBajada;
	
	public Pajaro(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = ANCHO_DE_PAJARO;
		this.alto = ALTO_DE_PAJARO;
		this.image = Herramientas.cargarImagen("gallina.png");
		this.velocidadDeSubida = 60;
		this.velocidadDeBajada = 2;
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
	
	
	public Disparo disparar() {
		return new Disparo(x, y, 10);
	}
	
	public boolean tocaSuelo(Obstaculo suelo) {
		double ground[] = suelo.dameObstaculo();
		return this.y > (ground[1]-ground[3]/4) ? true: false;
	}
	
	public boolean tocaTecho(Entorno e) {
		return this.y < 0 ? true: false;
	}
				
	public boolean chocaConElTubo(Obstaculo tubo) {
		double[] obstaculo = tubo.dameObstaculo();
		double bordeDerecho = (obstaculo[0]+obstaculo[2]/2);
		double bordeIzquierdo = (obstaculo[0]-obstaculo[2]/2);
		double bordeSuperiorDeTuboInferior = (obstaculo[1]-obstaculo[3]/2);
		double bordeInferiorDeTuboSuperior = (obstaculo[1]-obstaculo[4]+obstaculo[3]/2);
		if((x<=bordeDerecho && x>=bordeIzquierdo && y>=bordeSuperiorDeTuboInferior) 
		|| (x<=bordeDerecho && x>=bordeIzquierdo && y<=(bordeInferiorDeTuboSuperior))) { 
			return true;
		}
		return false;
	}
	
	public boolean seComioLaComida(Comida comida) {
		double[] food = comida.dameComida();
		double der = (x+ancho/2);
		double izq = (x-ancho/2);
		double arriba = (y-alto/2);
		double abajo = (y+alto/2);
		if (food[0] <= der && food[0] >= izq && food[1] >= arriba && food[1] <= abajo) {
			return true;
		}
		return false;
	}
	
	public double[] damePajaro() {
		double[] arrayPosicionDePajaro = {x, y, ancho, alto};
		return arrayPosicionDePajaro;
	}
}