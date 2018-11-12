package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Processo;

public class Main {
	static List<Processo> fila = new ArrayList<Processo>();
	static int momentoExecucao = 0;
	static int memoriaAtual = 0;

	public static void main(String[] args) {
		int quantum;
		//int quantum = Integer.parseInt(JOptionPane.showInputDialog("Quantum: "));
		boolean continuar = true;
		int memoria = 100;
		// While para inserção dos Processos
//		while (continuar) {
//			if (1 == JOptionPane.showConfirmDialog(null, "Adicionar processo?")) {
//				continuar = false;
//			} else {
//				Processo p = new Processo(Integer.parseInt(JOptionPane.showInputDialog("Momento de Chegada: ")),
//						Integer.parseInt(JOptionPane.showInputDialog("DuraÃ§Ã£o: ")));
//				memoria -= p.getDuracao();
//				if (memoria < 0) {
//					JOptionPane.showMessageDialog(null, "Espaço na memória insuficiente!");
//					continuar = false;
//				} else {
//					fila.add(p);
//				}
//			}
//		}
		
		// -- Variaveis de Teste --
		fila.add(new Processo(0, 13, "A"));
		fila.add(new Processo(2, 12, "B"));
		fila.add(new Processo(4, 30, "C"));
		fila.add(new Processo(10, 20, "D"));
		fila.add(new Processo(17, 15, "E"));
		quantum = 6;
		sort();
		
		System.out.println("Quantum: " + quantum);
		System.out.println("------------\n");
		
		// Processa enquanto existir processos na fila
		while (!fila.isEmpty()) {
			memoriaAtual = getMemoria();
			Processo processo = getProcesso();

			momentoExecucao += processar(processo, quantum);
		}
		System.out.println("Momento: "+momentoExecucao+"\nExecução terminada.");
	}

	private static int getMemoria() {
		int m = 0;
		for (Processo p : fila) {
			if (p.getChegada() <= momentoExecucao) {
				m +=p.getDuracao();
			}
		}
		return m;
	}

	// Ordena a fila de acordo com a ordem de chegada
	private static void sort() {
		Collections.sort(fila, new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				return Double.compare(p1.getChegada(), p2.getChegada());
			}
		});
	}
	// Pega o primeiro processo da fila
	private static Processo getProcesso() {
		Processo p = fila.get(0);
		fila.remove(0);
		return p;
	}
	
	// Realiza ações de acordo com a realação entre Quantum e Duração do processo
	// Retorna um int que simboliza a quantidade de tempo de processamento
	// (0, duracao do processo ou duracao do Quantum)
	public static int processar(Processo processo, int quantum) {
		// Verifica se o processo não chegou (tempo de chegada > tempo atual)
		if (processo.getChegada() > momentoExecucao) {
//			System.out.println("Processo " + processo.getNome() + " pulado. \n");
			fila.add(processo);
			return 0;
		}
		System.out.print("Momento: " + momentoExecucao);
		System.out.println("	| Memória: "+memoriaAtual);
		System.out.println("Executando " + processo.toString());
		
		// Caso Duração seja maior que o Quantum
		if (processo.getDuracao() > quantum) {
			processo.setDuracao(processo.getDuracao() - quantum);
			fila.add(processo);
			System.out.println("Tempo de processamento: "+quantum + "\n	--v--\n");
			return quantum;
		}
		// Caso Duração seja menor ou igual ao Quantum
		int aux = processo.getDuracao();
		processo.setDuracao(0);
		System.out.println("Tempo de processamento: "+aux + "\n	--v--\n");
		return aux;
	}
}
