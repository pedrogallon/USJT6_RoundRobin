package Manager;

import Model.Processo;

public class RoundRobin {

	private int quantum;
	
	public RoundRobin(int quantum) {
		this.quantum = quantum;
	}
	
	public int getQuantum() {
		return quantum;
	}
	
	// Pegar proximo processo da fila
	public Processo round(Fila fila) {
		Processo processo = fila.next();
		return processo;
	}
}