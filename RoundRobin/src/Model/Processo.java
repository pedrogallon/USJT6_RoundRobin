package Model;

public class Processo {
	
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
		return "Processo [chegada=" + chegada + ", duracao=" + duracao + nome + "]";
	}
}
