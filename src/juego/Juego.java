package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	
	private Entorno entorno;
	private Image spaceImg;
	
	private Ship ship;
	private Asteroid asteroid;   // la pelota
	private int puntaje;
	private Disparo laser;
	
	public Juego() {
		entorno = new Entorno(this, "Asteroids", 800, 600);
		
		spaceImg = Herramientas.cargarImagen("space.jpg");
		
		ship = new Ship(entorno.ancho()/2, entorno.alto()/2);
		asteroid = new Asteroid(400, 100);
		puntaje = 0;

		entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(spaceImg, entorno.ancho()/2, entorno.alto()/2, 0);
		
		//Dibuja el fondo primero porque sino el fondo va a tapar todo
		
		entorno.cambiarFont("sans", 20, Color.WHITE);
		entorno.escribirTexto("score: " + puntaje, entorno.ancho() - 150, 30);
		if(asteroid!=null) {
			asteroid.dibujar(entorno);	
			asteroid.mover();
			asteroid.rebotar(entorno);
		}
		if(laser!=null) {
			laser.dibujar(entorno);
			laser.mover(entorno);
		}
		
		if(ship!=null) {
			ship.dibujar(entorno);
			ship.mover(entorno);
			
			if (entorno.estaPresionada('a')) {
				ship.girarAntihorario();
			}
			
			if (entorno.estaPresionada('d')) {
				ship.girarHorario();
			}
			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				System.out.println("la nave dispara un rayo!");  //hacer clase disparo
				laser=ship.disparar();
			}
		}
		if(laser!=null && asteroid!=null) {
			if(asteroid.explota(laser,entorno)) {
				asteroid=null;
				laser=null;
			}
		}
		
		if(ship!=null && asteroid!=null) {
			if(ship.explota(asteroid,entorno)) {
				ship=null;
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
