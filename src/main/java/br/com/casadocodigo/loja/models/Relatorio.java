package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Relatorio {
	private Date dataGeracao;
	private int quatidade;
	private List<Produto> produtos;	
	
	public Relatorio(List<Produto> produtos) {
		this.dataGeracao = Calendar.getInstance().getTime();
		this.quatidade = produtos.size();
		this.produtos = produtos;		
	}

	public Relatorio(Date dataGeracao, int quantidade, List produtos) {
		this.dataGeracao=dataGeracao;
		this.quatidade=quantidade;
		this.produtos=produtos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getQuatidade() {
		return quatidade;
	}

	public void setQuatidade(int quatidade) {
		this.quatidade = quatidade;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

}
