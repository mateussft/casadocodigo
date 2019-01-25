package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Pedido;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.models.Usuario;

@RestController
public class PedidosServicoController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/pedidos", method = RequestMethod.GET)
	public ModelAndView verPedidos(RedirectAttributes model) {
		String uri = "https://book-payment.herokuapp.com/orders";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Pedido>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Pedido>>() {
				});
		List<Pedido> pedidos = response.getBody();
		ModelAndView modelAndView = new ModelAndView("/pedidos");
		modelAndView.addObject("pedidos", pedidos);
		return modelAndView;
	}
}
