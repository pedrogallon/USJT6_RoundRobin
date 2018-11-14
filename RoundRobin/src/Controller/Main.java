package Controller;

import javax.swing.JOptionPane;

import Manager.Memoria;
import Manager.RoundRobin;
import Model.Processo;

public class Main {

	public static void main(String[] args) {
		
		int quantum = Integer.parseInt(JOptionPane.showInputDialog("Insira o Quantum:"));
		Memoria memoria = new Memoria(new RoundRobin(quantum), 100);

		boolean continuar = true;
		// While para inserção dos Processos
		while (continuar) {
			if (1 == JOptionPane.showConfirmDialog(null, "Adicionar processo?")) {
				continuar = false;
			} else {
				Processo p = new Processo(Integer.parseInt(JOptionPane.showInputDialog("Momento de Chegada: ")),
						Integer.parseInt(JOptionPane.showInputDialog("Duração: ")),
						JOptionPane.showInputDialog("Nome: "));
				memoria.add(p);
			}
		}

//		memoria.add(new Processo(0, 5, "A"));
//		memoria.add(new Processo(10, 20, "B"));
//		memoria.add(new Processo(50, 10, "C"));
//		memoria.add(new Processo(3, 20, "D"));
//		memoria.add(new Processo(6, 15, "E"));
//		memoria.add(new Processo(4, 13, "F"));
//		memoria.add(new Processo(13, 12, "G"));
//		memoria.add(new Processo(14, 30, "H"));
//		memoria.add(new Processo(15, 20, "I"));
//		memoria.add(new Processo(17, 15, "J"));
//		memoria.add(new Processo(18, 13, "K"));
//		memoria.add(new Processo(20, 12, "L"));
//		memoria.add(new Processo(21, 30, "M"));
//		memoria.add(new Processo(23, 20, "N"));
//		memoria.add(new Processo(28, 15, "O"));

		memoria.run();
	}
}
