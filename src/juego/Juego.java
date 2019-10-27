package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Juego extends InterfaceJuego {
	
	private Entorno entorno;
	private Image fondo;
	private Image imagenSuelo;
	private Image imagenTubo;
	private Pajaro bird;
	private int puntaje;
	private Disparo[] disparo;
	private Obstaculo[] tubo;
	private Comida[] comida;
	private Obstaculo[] suelo;
	private double vida;
	private int cantDisparos=10;
	private int cantComida=10;

	public Juego() {
		
		entorno = new Entorno(this, "VeganBird", 800, 600);
		fondo = Herramientas.cargarImagen("fondo.jpg");
		imagenSuelo = Herramientas.cargarImagen("suelo.png");
		imagenTubo = Herramientas.cargarImagen("tubo.png");
		disparo = new Disparo[cantDisparos];
		bird = new Pajaro(entorno.ancho()/4, entorno.alto()/2);
		puntaje = 0;
		tubo = new Obstaculo[3];
		
		//Genera el randomBoolean para saber si la comida es verdura o no
	    Random randomGenerator = new Random();
		boolean randomBoolean = randomGenerator.nextBoolean();
		
		for (int i = 0; i < tubo.length; i++) {
			tubo[i] = new Obstaculo( (3+i)*(entorno.ancho()+75)/3, entorno.alto(), false, imagenTubo);
			}
		
		suelo = new Obstaculo[3];
		for (int i = 0; i < suelo.length; i++) {
			suelo[i] = new Obstaculo( entorno.ancho()*((1+2*i)/2), entorno.alto(), true, imagenSuelo);
			}
		
		comida = new Comida[cantComida];
		for (int i = 0; i < comida.length; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(300,400);
			int largo = comida.length;
			comida[i] = new Comida(entorno.ancho()*(largo+1+i)/largo, randomNum, 1, randomBoolean);
			}
		
		entorno.iniciar();
	}
	
	
	public static boolean comidaFueraDeJuego(Comida comida) {
		double[] arrayPosicionDeComida =comida.dameComida();
		return arrayPosicionDeComida[0]<0 ? true:false;
	}
	
	public static boolean obstaculoFueraDeJuego(Obstaculo obst) {
		double[] posicionDeObtaculo =obst.dameObstaculo();
		double bordeDerechoDeObstaculo=posicionDeObtaculo[0]+posicionDeObtaculo[2]/2;
		return bordeDerechoDeObstaculo<0 ? true:false;
	}
	

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
		
	    Random randomGenerator = new Random();
		boolean randomBoolean = randomGenerator.nextBoolean();
		
		if (bird != null) {
			for (int i = 0; i < comida.length; i++) {
				if (comida[i] != null && bird != null) {
					for (int j = 0; j < cantDisparos; j++) {
						if (disparo[j] != null) {
							comida[i].recibeDisparo(disparo[j]);
						}
					}	
				
				
				if (bird.seComioLaComida(comida[i])) {
					puntaje = comida[i].damePuntaje(puntaje);
					int randomNum = ThreadLocalRandom.current().nextInt(220,320);
					comida[i] = new Comida(entorno.ancho()+entorno.ancho()/4, randomNum, 1, randomBoolean);
				}
				comida[i].dibujar(entorno);
				comida[i].mover();
				

				if (comidaFueraDeJuego(comida[i])) {
					int randomNum = ThreadLocalRandom.current().nextInt(220,320);
					comida[i]=null;
					comida[i] = new Comida(entorno.ancho(), randomNum, 1, randomBoolean);
				}
			}
		}
		
		for (int i = 0 ; i < cantDisparos; i++) {
			if (disparo[i] != null) {
				disparo[i].dibujar(entorno);
				disparo[i].mover();
				if (disparo[i].fueraDeRango(entorno)) {
					disparo[i] = null;
				}
			}
		}
		
		if (bird != null) {
			bird.dibujar(entorno);
			bird.caer();
			if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				bird.subir();
			}
			
			for (int i = 0 ; i < cantDisparos; i++) {	
				if (entorno.sePresiono(entorno.TECLA_ESPACIO) && disparo[i] == null) {		
						disparo[i] = bird.disparar();
						break;
				}
			}
	    }
		
		for (int i = 0; i < tubo.length; i++) {
			tubo[i].mover();
			tubo[i].dibujar(entorno);
			if(obstaculoFueraDeJuego(tubo[i])) {
				int randomNum = ThreadLocalRandom.current().nextInt(entorno.alto(), entorno.alto()+100);
				tubo[i]=null;
				tubo[i] = new Obstaculo(entorno.ancho()+75/2, randomNum, false, imagenTubo);					
			}
			
			if (bird != null) {
				if (bird.chocaConElTubo(tubo[i]) || bird.tocaSuelo(suelo[i]) || bird.tocaTecho(entorno)) {
					bird = null;
				}
			}
		}
		
		for (int i = 0; i < suelo.length; i++) {
			suelo[i].mover();
			suelo[i].dibujar(entorno);
			if(obstaculoFueraDeJuego(suelo[i])) {
				suelo[i] = new Obstaculo(entorno.ancho()+800, entorno.alto(), true, imagenSuelo);					
			}
		}
		
		if (puntaje >= 0) {
			entorno.cambiarFont("monospaced", 20, Color.WHITE);
		}else {
			entorno.cambiarFont("monospaced", 20, Color.red);
		}
		
		entorno.escribirTexto("score: " + puntaje, entorno.ancho() - 150, 30);
		
		if (puntaje >= 10) {
			vida = 1;
		}else {
			vida = 0;
		}
		
		
		}
		
		if (bird == null) {
			fondo = Herramientas.cargarImagen("end.gif");
			entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
			if (vida > 0) {
				entorno.cambiarFont("monospaced", 20, Color.WHITE);
				entorno.escribirTexto("Tenes otra vida, apreta ENTER para continuar", entorno.ancho()/2-260, entorno.alto()/2+170);
				if (entorno.sePresiono(entorno.TECLA_ENTER)){
					bird = new Pajaro(entorno.ancho()/4, entorno.alto()/2);
					fondo = Herramientas.cargarImagen("fondo.jpg");
					puntaje = 0;
					vida--;
				}
			}else {
				entorno.cambiarFont("monospaced", 20, Color.WHITE);
				entorno.escribirTexto("Tus puntos fueron: " + puntaje, entorno.ancho()/2-150, entorno.alto()/2+200);
			}
		}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}