<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<section
	style="padding:1em" id="index-section" class="container middle">
	
	<div class="container">
		<h1>Cadastro de Usuarios</h1>
		<p>${message }</p>
		<form:form action="${s:mvcUrl('UC#gravar').build() }" method="post" commandName="usuario">
			<div class="form-group">
				<label>Nome</label>
				<form:input path="nome" cssClass="form-control" />
					<form:errors path="nome" />
			</div>
			<div class="form-group">
		        <label>Email</label>
				<form:input path="email" cssClass="form-control" />
					<form:errors path="email" />
			</div>
			<div class="form-group">
				<label>Senha</label>
				<form:input path="senha" type="password" cssClass="form-control" />
					<form:errors path="senha" />
			</div>
			<div class="form-group">
				<label>Senha Repetida</label>
				<form:input type="password" path="senhaRepetida" cssClass="form-control"/>    
			</div>
			
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div>
	
	</section>
	
</tags:pageTemplate>
