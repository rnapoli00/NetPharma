<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, main.model.*, main.bean.*"%>

<% 
    Collection<?> prodotti = (Collection<?>) request.getAttribute("prodotti");

    if (prodotti == null) {
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
        return;
    }
%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="keywords" content="NetPharma, piattaforma e-commerce, e-commerce, shop, farmacia, shop online, prodotti, ricerca, ricerca prodotti">
	<meta name="description" content="E-commerce, lista prodotti">
	<meta name="author" content="Bene Sabato, Cozzolino Lidia, Napoli Riccardo, Penna Alessandro">    
    <title>NetPharma &dash; Ricerca</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">						
	<%@ include file="/commonSources.jsp"%>
</head>            

<body class="bg-light">

	
    <%@ include file="/header.jsp"%>
    <div class="container py-5"> 
		<div class="row">
			<div class="col-md">
			    <h2 class="display-5 text-center pt-5">Ecco il risultato della ricerca&colon;</h2>
			</div>
		</div>
				<br>
				<table class="table text-center">
						<thead class="bg-secondary text-white">
						    <tr>
						      <th scope="col">Prodotto</th>
						      <th class="text-center" scope="col">Nome Prodotto</th>
						    </tr>
						  </thead>
						  <tbody> 
					  <%
					        Iterator<?> it = prodotti.iterator();
					        while(it.hasNext()) {
					            Prodotto prodotto = (Prodotto)it.next(); 
					  %>		
						  <tr>
								<th scope="row">
								</th>
								           <td><a href="<%=response.encodeURL(request.getContextPath() + "/Prodotto?id=" + prodotto.getId())%>"><%=prodotto.getNome()%></a></td>
										  <td>&dash;</td>
							  <% } %>
						  </tr>
					

						</tbody>
						</table>
				</div>
    

</body>
</html>