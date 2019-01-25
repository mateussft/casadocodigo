<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<section id="index-section" class="container middle">
	
	<a href="${s:mvcUrl('UC#form').build()}">Novo Usuario</a>
		<h1>Lista de Usuarios</h1>
		<p>${message }</p>
		<p> ${sucesso} </p>
		<p> ${falha} </p>
	
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
				<th></th>
			</tr>
			<c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome }</td>
					<td>${usuario.email }</td>
					<td>${usuario.roles }</td>
			<td>
<form action="${s:mvcUrl('UC#alterarRole').build() }" method="POST" >
								<input type="hidden" name="email" value="${usuario.email }">
<%-- 									<form:hidden path="email"/> --%>
								<button type="submit" ><img src="http://www.108talkradio.com/wp-content/uploads/2018/04/1232/lets-take-more-than-seven-seconds-with-corey-champagne.png" ></button>
							</form>

 			</td> 
				</tr>
			</c:forEach>
		</table>
	</section>
	
</tags:pageTemplate>