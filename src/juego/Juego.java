package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	
	private Entorno entorno;
	private Image spaceImg;
	
	private Pajaro bird;
	private int puntaje;
	private Disparo laser;
	private Obstaculo tubo;
	private Obstaculo tubo2;
	
	public Juego() {
		entorno = new Entorno(this, "VeganBird", 800, 600);
		
		spaceImg = Herramientas.cargarImagen("space.jpg");
		
		bird = new Pajaro(entorno.ancho()/4, entorno.alto()/2);
		puntaje = 0;
		
		tubo=new Obstaculo(200, 500,50,100, 1);
		entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(spaceImg, entorno.ancho()/2, entorno.alto()/2, 0);
		
		//Dibuja el fondo primero porque sino el fondo va a tapar todo
		
		entorno.cambiarFont("sans", 20, Color.WHITE);
		entorno.escribirTexto("score: " + puntaje, entorno.ancho() - 150, 30);
		
		tubo.mover(entorno);
		tubo.dibujar(entorno);
		if(laser!=null) {
			laser.dibujar(entorno);
			laser.mover(entorno);
		}
		
		if(bird!=null) {
			bird.dibujar(entorno);
			bird.mover(entorno);
			
			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				System.out.println("la nave dispara un rayo!");  //hacer clase disparo
				laser=bird.disparar();
			}
		}
		
		// TODO!
		
		
		// TODO!
//		if (el asteroide recibe un impacto) {
//			puntaje++;
//		}

	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
