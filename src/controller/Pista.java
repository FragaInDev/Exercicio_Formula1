package controller;

import java.util.concurrent.Semaphore;

public class Pista {
	private int qntEquipes = 7;
	private String nomeEquipe[] = {"Mercedes", "Redbull", "Ferrari", "McLaren", "AlphaTauri", "Williams", "Haas"};
	private Semaphore smf_pista = new Semaphore (5);
	private Equipe equipes[] = new Equipe[qntEquipes];
	
	public Pista() {
		main();
	}
	
	private void main() {
		for(int i = 0; i < qntEquipes; i++) {
			equipes[i] = new Equipe(nomeEquipe[i], smf_pista);
			equipes[i].start();
		}
	}
}
