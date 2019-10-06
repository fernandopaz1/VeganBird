package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.util.concurrent.ThreadLocalRandom;


public class Juego extends InterfaceJuego {
	
	private Entorno entorno;
	private Image spaceImg;
	
	private Pajaro bird;
	private int puntaje;
	private Disparo laser;
	private Obstaculo[] tubo;
	private Comida[] comida;
	
	public Juego() {
		entorno = new Entorno(this, "VeganBird", 800, 600);
		
		spaceImg = Herramientas.cargarImagen("space.jpg");
		
		bird = new Pajaro(entorno.ancho()/4, entorno.alto()/2);
		puntaje = 0;
		
		tubo=new Obstaculo[3];
		for(int i=0;i<tubo.length;i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(entorno.alto()-100, entorno.alto());
			tubo[i]=new Obstaculo(entorno.ancho()/3+(entorno.ancho()/3)*i, randomNum,75,225, 1);
			}
		
		comida=new Comida[7];
		for(int i=0;i<comida.length;i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(300,400);
			int parOImpar=randomNum%2;
			int largo=comida.length;
			comida[i]=new Comida(entorno.ancho()/largo+(entorno.ancho()/largo)*i, randomNum,10,10, 1,parOImpar);
			}
		
		entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(spaceImg, entorno.ancho()/2, entorno.alto()/2, 0);
		
		//Dibuja el fondo primero porque sino el fondo va a tapar todo
		
		entorno.cambiarFont("sans", 20, Color.WHITE);
		
		for(int i=0;i<tubo.length;i++) {
			tubo[i].mover(entorno);
			tubo[i].dibujar(entorno);
			if(bird!=null) {
				if(bird.choca(tubo[i], entorno)) {
					bird=null;
				}
			}
		}
		
		
		for(int i=0;i<comida.length;i++) {
			if(comida[i]!=null && bird!=null) {
				if(comida[i].fueraDePantalla() ||comida[i].comido(bird)) {
					int randomNum = ThreadLocalRandom.current().nextInt(300,500);
					int parOImpar=randomNum%2;
					comida[i]=new Comida(entorno.ancho(), randomNum,10,10, 1,parOImpar);
				}
				comida[i].dibujar(entorno);
				comida[i].mover();
			}
		}
		
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
		
		entorno.escribirTexto("score: " + puntaje, entorno.ancho() - 150, 30);

			
		}
		
		// TODO!
		
		
		// TODO!
//		if (el asteroide recibe un impacto) {
//			puntaje++;
//		}

	

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
