package juego;

import java.awt.Image;
import entorno.Entorno;

public class Obstaculo {
	
	private static final int VELOCIDAD_OBSTACULOS = 1;
	private static final int ALTO_DEL_TUBO = 500;
	private static final int ANCHO_DEL_TUBO = 75;
	private static final int ALTO_DEL_SUELO = 250;
	private static final int ANCHO_DEL_SUELO = 800;
	private static final int ABERTURA_VERTICAL_ENTRE_TUBOS = 650;
	private static final int AMPLITUD_DE_MOVIMIENTO_VERTICAL = 40;
	private static final int FRECUENCIA_DE_MOVIMIENTO_VERTICAL = 100;
	private double x;
	private double y;
	private double velocidad;
	private Image imagen;
	private double ancho;
	private double alto;
	private double abertura;
	private boolean esSuelo;
	
	public Obstaculo(double x, double y, boolean suelo,Image imagen) {
		this.x = x;
		this.y = y;
		this.velocidad = VELOCIDAD_OBSTACULOS;
		this.esSuelo = suelo;
		if (suelo) {
			this.ancho = ANCHO_DEL_SUELO;
			this.alto = ALTO_DEL_SUELO;
			this.abertura = 0;
		}else {
			this.ancho = ANCHO_DEL_TUBO;
			this.alto = ALTO_DEL_TUBO;
			this.abertura = ABERTURA_VERTICAL_ENTRE_TUBOS;
		}
		
		this.imagen = imagen;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0, 1);
		if (esSuelo == false) {
			e.dibujarImagen(imagen, x, y-abertura, Math.PI, 1);
		}
	}
	
	public void mover() {
		x -= velocidad;
		if (this.abertura != 0) {
			double f = FRECUENCIA_DE_MOVIMIENTO_VERTICAL;
			double a = AMPLITUD_DE_MOVIMIENTO_VERTICAL;
			y += a*Math.sin((x+y)/f)/f;
		}
	}
	
	public boolean tuboeEsChocadoPor(Pajaro bird) {
		if ( esSuelo ) {
			return false;
		}
		double[] pajaro = bird.damePajaro();
		double bordeDerecho = (x+ancho/2);
		double bordeIzquierdo = (x-ancho/2);
		double bordeSuperiorDeTuboInferior = (y-alto/2);
		double bordeInferiorDeTuboSuperior = (y-abertura+alto/2);
		if((pajaro[0]<=bordeDerecho && pajaro[0]>=bordeIzquierdo && pajaro[1]>=bordeSuperiorDeTuboInferior) 
		|| (pajaro[0]<=bordeDerecho && pajaro[0]>=bordeIzquierdo && pajaro[1]<=(bordeInferiorDeTuboSuperior))) { 
			return true;
		}
		return false;
	}
	
	public double[] dameObstaculo() {
		double[] arrayPosicionDeObstaculo = {x, y, ancho, alto, abertura};
		return arrayPosicionDeObstaculo;
	}
}