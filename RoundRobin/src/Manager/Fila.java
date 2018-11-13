package Manager;

import java.util.LinkedList;
import java.util.Queue;

import Model.Processo;

public class Fila {
	
	private Queue<Processo> round;
	
	public Fila() {
		round = new LinkedList<>();
	}
	
	public void add(Processo processo) {
		round.add(processo);
	}
	
	public Processo next() {
		return round.remove();
	}
}