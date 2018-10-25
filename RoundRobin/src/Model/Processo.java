package Model;

public class Processo {

	int chegada;
	int duracao;

	
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
	
	public Processo(int chegada, int duracao) {
		super();
		this.chegada = chegada;
		this.duracao = duracao;
	}
	@Override
	public String toString() {
		return "Processo [chegada=" + chegada + ", duracao=" + duracao + "]";
	}
	
	
	
}
