package Manager;

import java.util.ArrayList;

import Model.Processo;

public class Memoria {

	private final int MAX_MEMORIA;
	private int memoriaDisponivel;
	private int time;

	private ArrayList<Processo> processos;
	private ArrayList<Processo> onWaitQueue;
	private Fila fila;
	
	private RoundRobin roundRobin;

	public Memoria(RoundRobin roundRobin, int maxMemoria) {
		this.roundRobin = roundRobin;
		MAX_MEMORIA = maxMemoria;

		onWaitQueue = new ArrayList<Processo>();
		processos = new ArrayList<Processo>();
		
		time = -1;
	}

	private void next() {
		memoriaDisponivel++;
		time++;
		
		Processo processo = processos.get(0);
		if(processo.getChegada() == time) {
			notifica("Add " + processo);
			put(processo);
			processos.remove(0);
		}	
	}

	private void put(Processo processo) {
		if(getMemoriaDisponivel() >= processo.getDuracao()) {
			notifica(" on round " + processo);
			memoriaDisponivel -= processo.getDuracao();
			fila.add(processo);
			return;
		}
		notifica(" On Wait " + processo);
		onWaitQueue.add(processo);
	}

	public int getMemoriaDisponivel() {
		if(memoriaDisponivel > MAX_MEMORIA)
			memoriaDisponivel = MAX_MEMORIA;
		return memoriaDisponivel;
	}

	private Processo getProcesso() {

		if(onWaitQueue.size() > 0) {
			Processo processo = onWaitQueue.get(0);
			if(getMemoriaDisponivel() >= processo.getDuracao()) {
				notifica("Memoria diponivel, Add to round " + processo);
				fila.add(processo);
				return processo;
			}
		}

		return roundRobin.round(fila);
	}

	public void run() {
		while(processos.size() > 0) {
			next();
			run(getProcesso());
		}
	}

	private void run(Processo processo) {
		for(int thisQuantum = 0; thisQuantum < roundRobin.getQuantum(); thisQuantum++) {
			next();
			notifica("Runnig " + processo);
			processo.run();
			if(processo.getDuracao() == 0) {
				notifica("End " + processo);
				break;
			}
		}
	}
	
	private void notifica(String msg) {
		System.out.println(msg);
	}
	
	public void add(Processo processo) {
		processos.add(processo);
	}
}