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
	public static void main(String[] args) {

		int quantum = 0;//Integer.parseInt(JOptionPane.showInputDialog("Quantum: "));
		// int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de
		// Processos: "));
		boolean continuar = true;
		int memoria = 100;
//		while (continuar) {
//			if (1 == JOptionPane.showConfirmDialog(null, "Adicionar processo?")) {
//				continuar = false;
//			} else {
//				Processo p = new Processo(Integer.parseInt(JOptionPane.showInputDialog("Momento de Chegada: ")),
//						Integer.parseInt(JOptionPane.showInputDialog("Duração: ")));
//				memoria -= p.getDuracao();
//				if (memoria < 0) {
//					JOptionPane.showMessageDialog(null, "Espaço na memória insuficiente!");
//					continuar = false;
//				} else {
//					fila.add(p);
//				}
//			}
//		}
		fila.add(new Processo(0, 7, "A"));
		fila.add(new Processo(7, 2, "B"));
		fila.add(new Processo(5, 5, "C"));
		fila.add(new Processo(4, 3, "D"));
		fila.add(new Processo(2, 9, "E"));
		quantum = 6;
		
		sort();	
		System.out.println(fila.toString());
		System.out.println("Quantum: " + quantum);
	
		while(!fila.isEmpty()) {
			Processo processo = getProcesso();
			System.out.println("Momento de chegada do processo: " + momentoExecucao);
			momentoExecucao += processar(processo, quantum);
		}		
	}

	private static void sort (){
		Collections.sort(fila, new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				return Double.compare(p1.getChegada(), p2.getChegada());
			}
		});
	}

	private static Processo getProcesso() {
		Processo p = fila.get(0);
		fila.remove(0);
		return p;
	}
	

	public static int processar(Processo processo, int quantum) {
		if(processo.getChegada() > momentoExecucao)
			return 1;
		
		System.out.println("Executando o processo: " + processo.toString());
		if(processo.getDuracao() > quantum) {
			processo.setDuracao(processo.getDuracao() - quantum);
			fila.add(processo);
			return quantum;
		}
		int aux = processo.getDuracao();
		processo.setDuracao(0);
		if(processo.getDuracao() < quantum ) 
			return aux;
		
		return quantum;
	}
}
