package juego;

import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Comida {
	private static final int VELOCIDAD = 1;
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double velocidad;
	private boolean verdura;
	private boolean convertida;
	private Image imagen;
	
    Random randomGenerator = new Random();
	boolean randomBoolean = randomGenerator.nextBoolean();
	
	public Comida(double x, double y, boolean verdura) {
		this.x = x;
		this.y = y;
		this.ancho = 20;
		this.alto = 20;
		this.velocidad = VELOCIDAD;
		this.verdura = randomBoolean;
		this.convertida = false;
		this.imagen = this.verdura ? Herramientas.cargarImagen("Brocoli.png") : Herramientas.cargarImagen("Hamburguesa.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0);
	}
	
	public void mover() {
		double f = this.verdura ? 100 : 20;
		double a = this.verdura ? 20 : 40;
		x -= velocidad;
		y += a*Math.sin(x/f)/f;
	}
	
	
	public double[] dameComida() {
		double[] arrayPosicionDeComida= {x,y,ancho,alto};
		return arrayPosicionDeComida;
	}
	
	public void recibeDisparo(Disparo shot) {
		double[] disparo = shot.dameDisparo();
		double der = (x+ancho/2);
		double izq = (x-ancho/2);
		double arriba = (y-alto/2);
		double abajo = (y+alto/2);
		if (disparo[0] <= der && disparo[0] >= izq && disparo[1] >= arriba && disparo[1] <= abajo) {
			this.convertida = true;
			this.verdura = true;
			this.imagen = Herramientas.cargarImagen("Brocoli.png");
		}
	}
	
	public int damePuntaje(int p) {
		if(x<0) {
			return 0;
		}
		if (!this.verdura) {
			p -= 5;
			return p;
		}
		return this.convertida ? 3 : 5;
	}
}