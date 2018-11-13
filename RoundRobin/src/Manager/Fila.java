package Manager;

import java.util.LinkedList;
import java.util.Queue;

import Model.Processo;

public class Fila {
	
	private Queue<Processo> fila;
	
	public Fila() {
		fila = new LinkedList<>();
	}
	
	public void sendToEnd(Processo processo) {
		fila.remove();
		fila.add(processo);
	}
	
	public void add(Processo processo) {
		fila.add(processo);
	}
	
	public Processo next() {
		return fila.remove();
	}
	
	public int size() {
		return fila.size();
	}
	
	public void remove(Processo processo) {
		fila.remove(processo);
	}
}