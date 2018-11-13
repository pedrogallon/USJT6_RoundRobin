package Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Model.Processo;

public class Memoria {

	private final int MAX_MEMORIA; // Memoria Total
	private int memoriaDisponivel; // Memoria Livre
	private int time;		// Time de execução

	private ArrayList<Processo> processos; // Fila auxiliar de processos inseridos
	private ArrayList<Processo> onWaitQueue; // Fila em disco
	private Fila fila; 			// Fila em memória

	private RoundRobin roundRobin;

	public Memoria(RoundRobin roundRobin, int maxMemoria) {
		this.roundRobin = roundRobin;
		MAX_MEMORIA = maxMemoria;
		memoriaDisponivel = maxMemoria;

		onWaitQueue = new ArrayList<Processo>();
		processos = new ArrayList<Processo>();
		fila = new Fila();

		time = -1; 
	}

	// Começar execução dos processos
	public void run() {
		sortProcessos();
		next();
		// Processa enquanto existir Processos na fila da memória
		while (processos.size()>0) {
			if (fila.size()>0) {
				getProcessoFromWaitQueue();
				run(getProcesso());
			}else {
				next();
				notifica(" -- No Process Available 	-- Time: " + time);
			}
		}
	}

	// Executa Processo por um Time <= 1 quantum, verificando se processos chegaram em cada instante
	private void run(Processo processo) {
		for (int thisQuantum = 0; thisQuantum < roundRobin.getQuantum(); thisQuantum++) {
			next();
			processo.run();
			notifica("Running " + processo + " 	-- Time: " + time + "	| Free Space: " + memoriaDisponivel);
			// Final do Processo
			if (processo.getDuracao() == 0) {
				notifica(" -- End 	" + processo + " 	-- Time: " + time);
				return;
			}
		}

		// Add de volta pois o Time não é 0;
		fila.add(processo);

	}

	// Aumenta Time, aumenta memória livre (1 ms foi processado) e verifica se há processos que chegaram neste Time
	private void next() {
		aumentarMemoriaDisponivel();
		time++;
		for (int i = 0; i < processos.size(); i++) {
			Processo processo = processos.get(0);
			if (processo.getChegada() == time) {
				notifica("- Add " + processo + " 	-- Time: " + time + "	| Free Space: " + memoriaDisponivel);
				put(processo);
				processos.remove(0);
			} else {
				break;
			}
		}
	}

	private void put(Processo processo) {
		// Insere Processo recem chegado na Memória ou na fila de espera em Disco caso não haja espaço
		if (memoriaDisponivel >= processo.getDuracao()) {
			notifica("-- on Memory ");
			memoriaDisponivel -= processo.getDuracao();
			fila.add(processo);
			return;
		}
		notifica("-- On Disk ");
		onWaitQueue.add(processo);
	}

	// Aumenta memória a cada ms pasado, com limite
	public void aumentarMemoriaDisponivel() {
		memoriaDisponivel++;
		if (memoriaDisponivel > MAX_MEMORIA)
			memoriaDisponivel = MAX_MEMORIA;
	}

	// Pega o próximo processo na Fila de Memória
	private Processo getProcesso() {
		return roundRobin.round(fila);
	}
	
	// Verifica se há espaço para um processo ir da fila de disco à de memória
	private void getProcessoFromWaitQueue() {
		if (onWaitQueue.size() > 0) {
			Processo processo = onWaitQueue.get(0);
			
			// Se possuir memoria, envia para fila
			if (memoriaDisponivel >= processo.getDuracao()) {
				notifica(" -- Switched Process "+processo.getNome()+" to Memory ");
				onWaitQueue.remove(0);
				fila.add(processo);
				memoriaDisponivel -= processo.getDuracao();
			}
		}
	}

	// Sysout de preguiçoso
	private void notifica(String msg) {
		System.out.println(msg);
	}

	// Add aos processos
	public void add(Processo processo) {
		processos.add(processo);
	}

	// Organiza os processos em ordem de chegada
	private void sortProcessos() {
		Collections.sort(processos, new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				return Double.compare(p1.getChegada(), p2.getChegada());
			}
		});
	}
}