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
	private Disparo[] laser;
	private Obstaculo[] tubo;
	private Comida[] comida;
	private Obstaculo[] suelo;
	
//	private double anchoComida=20;
//	private double altoComida=20;
	
	
	private double vida;

	private int cantDisparos=10;
	
	private int cantComida=10;

	
	public Juego() {
		entorno = new Entorno(this, "VeganBird", 800, 600);
		
		fondo = Herramientas.cargarImagen("fondo.jpg");
		imagenSuelo = Herramientas.cargarImagen("suelo.png");
		imagenTubo = Herramientas.cargarImagen("tubo.png");
		laser = new Disparo[cantDisparos];
		bird = new Pajaro(entorno.ancho()/4, entorno.alto()/2);
		puntaje = 0;
		
		tubo=new Obstaculo[3];
		for(int i=0;i<tubo.length;i++) {
			tubo[i]=new Obstaculo( (3+i)*(entorno.ancho()+75)/3, entorno.alto(),false,imagenTubo);
			}
		
		suelo=new Obstaculo[3];
		for(int i=0;i<suelo.length;i++) {
			suelo[i]=new Obstaculo( entorno.ancho()*((1+2*i)/2), entorno.alto(),true,imagenSuelo);
			}
		
		comida=new Comida[cantComida];
		for(int i=0;i<comida.length;i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(300,400);
			int parOImpar=randomNum%2;
			int largo=comida.length;
			comida[i]=new Comida(entorno.ancho()*(largo+1+i)/largo, randomNum, 1,parOImpar);
			}
		
		entorno.iniciar();
	}

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
		if(bird!=null) {
		for(int i=0;i<comida.length;i++) {
			if(comida[i]!=null && bird!=null) {
				for(int j=0;j<cantDisparos;j++) {
					if(laser[j]!=null) {
						comida[i].convertido(laser[j]);
					}
				}	
					
				if(comida[i].fueraDePantalla()) {
					int randomNum = ThreadLocalRandom.current().nextInt(220,320);
					int parOImpar=randomNum%2;
					comida[i]=new Comida(entorno.ancho(), randomNum,1,parOImpar);
					}
				
				if(bird.seComioLa(comida[i])) {
					puntaje=comida[i].damePuntaje(puntaje);
					int randomNum = ThreadLocalRandom.current().nextInt(220,320);
					int parOImpar=randomNum%2;
					comida[i]=new Comida(entorno.ancho()+entorno.ancho()/4, randomNum,1,parOImpar);
				}
				comida[i].dibujar(entorno);
				comida[i].mover();
			}
		}
		
		for (int i = 0 ; i<cantDisparos; i++) {
			if(laser[i]!=null) {
				laser[i].dibujar(entorno);
				laser[i].mover(entorno);
				if (laser[i].fueraDePantalla(entorno)) {
					laser[i]=null;
				}
			}
		}
		
		if (bird!=null) {
			bird.dibujar(entorno);
			bird.caer();
			
			if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				bird.subir();
			}
			
			for (int i = 0 ; i<cantDisparos; i++) {	
				if (bird.puedeDisparar() && entorno.sePresiono(entorno.TECLA_ESPACIO) && laser[i]==null) {		
						laser[i]=bird.disparar();
						break;
				}
			}
	    }
		
		for(int i=0;i<tubo.length;i++) {
			tubo[i].mover(entorno);
			tubo[i].dibujar(entorno);
			if(tubo[i].fueraDePantalla(entorno)) {
				int randomNum = ThreadLocalRandom.current().nextInt(entorno.alto(), entorno.alto()+100);
				tubo[i]=new Obstaculo(entorno.ancho()+75/2, randomNum,false,imagenTubo);					
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
				suelo[i]=new Obstaculo(entorno.ancho()+800, entorno.alto(),true,imagenSuelo);					
			}
		}
		
		
		
		if(puntaje>=0) {
			entorno.cambiarFont("monospaced", 20, Color.WHITE);
		}
		else {
			entorno.cambiarFont("monospaced", 20, Color.red);
		}
		if (puntaje>=0)
			vida=1;
		else vida=0;
		
		entorno.escribirTexto("score: " + puntaje, entorno.ancho() - 150, 30);

		}
		
		if(bird==null) {
			fondo = Herramientas.cargarImagen("end.gif");
			entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
			if (vida>0) {
				entorno.cambiarFont("monospaced", 20, Color.WHITE);
				entorno.escribirTexto("Tenes otra vida, apreta ENTER para continuar", entorno.ancho()/2-260, entorno.alto()/2+170);
			}else {
				entorno.cambiarFont("monospaced", 20, Color.WHITE);
				entorno.escribirTexto("Tus puntos fueron: " + puntaje, entorno.ancho()/2-150, entorno.alto()/2+200);
				}	
		}

			if(entorno.sePresiono(entorno.TECLA_ENTER) && vida>0){
				bird = new Pajaro(entorno.ancho()/4, entorno.alto()/2);
				fondo = Herramientas.cargarImagen("fondo.jpg");
				puntaje=0;
				vida--;
			}
		}


	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}