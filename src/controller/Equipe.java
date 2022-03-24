package controller;

import java.util.concurrent.Semaphore;

public class Equipe extends Thread{
	private int pilotos = 2;
	private Semaphore smf_escuderia = new Semaphore(1);
	private Semaphore smf_pista;
	private Carro carros[] = new Carro[pilotos];
	private String nEquipe;
	private String best_time;
	
	public Equipe(String _nome, Semaphore _pista) {
		nEquipe = _nome;
		smf_pista = _pista;
		criaCarro();
	}
	
	@Override
	public void run() {
		try {
			smf_pista.acquire();
			for(int i=0; i < pilotos; i++) {
				carros[i].start();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			smf_pista.release();
		}
		saveTime();
	}
	
	private void saveTime() {
		for(int i = 0; i < pilotos; i++) {
			best_time += carros[i].getTempo() + "\n";
		}
	}
	
	private void criaCarro() {
		for(int i = 0; i < pilotos; i++) {
			carros[i] =  new Carro(nEquipe + " Carro: " + (i+1), smf_escuderia);
		}
	}
}
