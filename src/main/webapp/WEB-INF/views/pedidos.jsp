<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<section id="index-section" class="container middle">
	<table>
	<thead>
				<tr>
				
					<th width="10%">ID</th>
					<th width="10%">Valor</th>
					<th width="10%">Data Pedido</th>
					<th width="30%">Titulos</th>
					
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${pedidos}" var="pedido">
					<tr>
						
						<td class="item-title">${pedido.id }</td>
						<td class="numeric-cell">${pedido.valor}</td>			
						<td class="numeric-cell"><fmt:formatDate value="${pedido.data.time}" pattern="dd/MM/yyyy"/></td>
						<td class="numeric-cell">
							<c:forEach items="${pedido.produtos}" var="produto">
						${produto.titulo},
						</c:forEach>
						</td>
					</tr>
				</c:forEach>
	
	</tbody>
	</table>
	</section>
	
</tags:pageTemplate>
