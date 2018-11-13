package Model;

public class Processo implements Comparable<Processo> {
	
	private int chegada;
	private int duracao;
	private String nome;

	public Processo(int chegada, int duracao, String nome) {
		this.chegada = chegada;
		this.duracao = duracao;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getChegada() {
		return chegada;
	}
	public void setChegada(int chegada) {
		this.chegada = chegada;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	@Override
	public String toString() {
		return "Processo: " + nome + "	| Duração restante: " + duracao + "	| Chegada: " + chegada ;
	}

	@Override
	public int compareTo(Processo outroProcesso) {
		return this.getChegada() <= outroProcesso.getChegada() ? 1 : 0;
	}

	public void run() {
		setDuracao(getDuracao() -1);
	}
}
