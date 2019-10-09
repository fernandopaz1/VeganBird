package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.util.concurrent.ThreadLocalRandom;


public class Juego extends InterfaceJuego {
	
	private Entorno entorno;
	private Image fondo;
	private Image imagenSuelo;
	private Image imagenTubo;
	
	private Pajaro bird;
	private int puntaje;
	private Disparo laser;
	private Obstaculo[] tubo;
	private Comida[] comida;
	private Obstaculo[] suelo;
	
	private double anchoComida=20;
	private double altoComida=20;
	
	private double anchoTubo=75;
	private double altoTubo=500;
	
	private double anchoSuelo=800;
	private double altoSuelo=100;
	
	private double anchoPajaro=40;
	private double altoPajaro=40;
	
	
	private int cantComida=10;
	private double aberturaTubo=650;
	
	public Juego() {
		entorno = new Entorno(this, "VeganBird", 800, 600);
		
		fondo = Herramientas.cargarImagen("fondo.png");
		imagenSuelo = Herramientas.cargarImagen("suelo.png");
		imagenTubo = Herramientas.cargarImagen("tubo.png");
		
		bird = new Pajaro(entorno.ancho()/4, entorno.alto()/2,anchoPajaro,altoPajaro);
		puntaje = 0;
		
		tubo=new Obstaculo[3];
		for(int i=0;i<tubo.length;i++) {
			tubo[i]=new Obstaculo( (3+i)*(entorno.ancho()+75)/3, entorno.alto(),anchoTubo,altoTubo, aberturaTubo+100,imagenTubo);
			}
		
		suelo=new Obstaculo[3];
		for(int i=0;i<suelo.length;i++) {
			suelo[i]=new Obstaculo( entorno.ancho()*((1+2*i)/2), entorno.alto(),anchoSuelo,altoSuelo,0,imagenSuelo);
			}
		comida=new Comida[cantComida];
		for(int i=0;i<comida.length;i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(300,400);
			int parOImpar=randomNum%2;
			int largo=comida.length;
			comida[i]=new Comida(entorno.ancho()*(i)/largo, randomNum,anchoComida,altoComida, 1,parOImpar);
			}
		
		entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
		
		
		for(int i=0;i<suelo.length;i++) {
			suelo[i].mover(entorno);
			suelo[i].dibujar(entorno);
			if(suelo[i].fueraDePantalla(entorno)) {
				suelo[i]=new Obstaculo(entorno.ancho()+anchoSuelo, entorno.alto(),anchoSuelo,altoSuelo, 0,imagenSuelo);					
			}
		}
		//Dibuja el fondo primero porque sino el fondo va a tapar todo
		
		
		
		for(int i=0;i<comida.length;i++) {
			if(comida[i]!=null && bird!=null) {
				if(laser!=null) {
					comida[i].convertido(laser);
				}
				
				if(comida[i].fueraDePantalla()) {
					int randomNum = ThreadLocalRandom.current().nextInt(220,320);
					int parOImpar=randomNum%2;
					comida[i]=new Comida(entorno.ancho(), randomNum,anchoComida,altoComida, 1,parOImpar);
				}
				
				if(bird.comido(comida[i])) {
					puntaje=comida[i].damePuntaje(puntaje);
					int randomNum = ThreadLocalRandom.current().nextInt(220,320);
					int parOImpar=randomNum%2;
					comida[i]=new Comida(entorno.ancho()+entorno.ancho()/4, randomNum,anchoComida,altoComida, 1,parOImpar);
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
				System.out.println("fium!");  //hacer clase disparo
				laser=bird.disparar();
			}
	    }
		
		for(int i=0;i<tubo.length;i++) {
			tubo[i].mover(entorno);
			tubo[i].dibujar(entorno);
			if(tubo[i].fueraDePantalla(entorno)) {
				int randomNum = ThreadLocalRandom.current().nextInt(entorno.alto(), entorno.alto()+100);
				tubo[i]=new Obstaculo(entorno.ancho()+anchoTubo/2, randomNum,anchoTubo,altoTubo, aberturaTubo,imagenTubo);					
			}
			if(bird!=null) {
				if(bird.choca(tubo[i],suelo[0], entorno)) {
					bird=null;
				}
			}
		}
		
		

		for(int i=0;i<suelo.length;i++) {
			suelo[i].mover(entorno);
			suelo[i].dibujar(entorno);
			if(suelo[i].fueraDePantalla(entorno)) {
				suelo[i]=new Obstaculo(entorno.ancho()+anchoSuelo, entorno.alto(),anchoSuelo,altoSuelo, 0,imagenSuelo);					
			}
		}
		
		
		if(puntaje>=0) {
			entorno.cambiarFont("sans", 20, Color.WHITE);
		}
		else {
			entorno.cambiarFont("sans", 20, Color.red);
		}
		
		entorno.escribirTexto("score: " + puntaje, entorno.ancho() - 150, 30);

		if(bird==null) {
			entorno.escribirTexto("Perdiste: " + puntaje, entorno.ancho()/2, entorno.alto()/2);
		}	
		
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
