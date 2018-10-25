package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Processo;

public class Main {
	static List<Processo> fila = new ArrayList<Processo>();

	public static void main(String[] args) {

		int quantum = Integer.parseInt(JOptionPane.showInputDialog("Quantum: "));
		// int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de
		// Processos: "));
		boolean continuar = true;
		int memoria = 100;
		while (continuar) {
			if (1 == JOptionPane.showConfirmDialog(null, "Adicionar processo?")) {
				continuar = false;
			} else {
				Processo p = new Processo(Integer.parseInt(JOptionPane.showInputDialog("Momento de Chegada: ")),
						Integer.parseInt(JOptionPane.showInputDialog("Duração: ")));
				memoria -= p.getDuracao();
				if (memoria < 0) {
					JOptionPane.showMessageDialog(null, "Espaço na memória insuficiente!");
					continuar = false;
				} else {
					fila.add(p);
				}
			}
		}

		sort();
		
		System.out.println(fila.toString());
		
		Processo p = pop();

		

	}

	private static void sort() {
		Collections.sort(fila, new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {
				return Double.compare(p1.getChegada(), p2.getChegada());
			}
		});
	}

	private static Processo pop() {
		Processo p = fila.get(0);
		fila.remove(0);
		System.out.println(fila.get(0).toString());
		return p;
	}

}
