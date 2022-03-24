package controller;

import java.util.concurrent.Semaphore;

public class Carro extends Thread{
	private String id;
	private Semaphore smf;
	private int menor_tempo;
	private int voltas = 5;
	
	public Carro(String _id, Semaphore smf_escuderia) {
		id = _id;
		smf = smf_escuderia;
	}
	
	@Override
	public void run() {
		try {
			smf.acquire();
			correr();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			smf.release();
		}
	}
	
	public void correr() {
		for(int i=0; i < voltas; i++) {
			System.out.println(id + " está dando a " + (i+1) + "a. volta.....");
			
			int tVolta = (int) ((Math.random() * 19001) + 1000); //1 - 20s
			
			if (tVolta < menor_tempo) {
				menor_tempo = tVolta;
			}
			
			System.out.println(id + ": " + "volta finalizada. Tempo: " + (tVolta / 1000) + " segundos.");
		}
	}
	
	public String getTempo() {
		return id + " menor tempo: " + menor_tempo;
	}
}
