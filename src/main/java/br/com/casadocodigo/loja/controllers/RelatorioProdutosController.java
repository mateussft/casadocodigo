package br.com.casadocodigo.loja.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Relatorio;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Relatorio gerarRelatorio(@RequestParam("data") Optional<String> data)
			throws ParseException, java.text.ParseException {
		List produtos;
		if (!data.isPresent()) {
			produtos = produtoDao.listar();
		} else {
			String dia = data.get().split("-")[2];
			String mes = data.get().split("-")[1];
			String ano = data.get().split("-")[0];
			String dataLancamento = dia + "/" + mes + "/" + ano;
			SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
			Date dateObj = curFormater.parse(dataLancamento);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateObj);
			produtos = produtoDao.listarPorData(calendar);
		}
		Date dataGeracao = new Date();
		int quantidade = produtos.size();
		return new Relatorio(dataGeracao, quantidade, produtos);
	}

}
