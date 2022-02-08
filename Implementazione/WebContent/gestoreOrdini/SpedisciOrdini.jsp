<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, main.model.* , main.bean.*"%>
    
    <% 
   
    
    Collection<?> ordini = (Collection<?>) request.getAttribute("ordini");

    if (ordini == null) {
        response.sendRedirect(response.encodeRedirectURL("/NetPharma/OrdiniDaSpedire"));
        return;
    }
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="keywords" content="NetPharma, farmacia online">
	<meta name="description" content="Lista Ordini - Gestore Ordini">
	<meta name="author" content="Bene Sabato, Cozzolino Lidia, Napoli Riccardo, Penna Alessandro">  
	<%@ include file="/commonSources.jsp"%>
	<title>Spedisci ordini</title>
</head>
<body class="pt-5">
	<%@ include file="/headerGestori.jsp"%>
	<form method="get" action="<%=response.encodeURL("/NetPharma/GestisciOrdini")%>">
	<%
	Iterator<?> it = ordini.iterator();
	while(it.hasNext()) {
		
		Ordine ordine = (Ordine)it.next();
		if(ordine.getStato().equals("No")) {
			
		
	%>
	<h3><%=ordine.getCliente()%> </h3>
	<h3><%=ordine.getPrezzo()%> </h3>
	<h3><%=ordine.getData_ordine() %></h3>
	<input type="text" placeholder="Giorni" class="form-control" id="giorni" value="<%=request.getAttribute("giorni")%>" name="giorni">
	<input type="submit" class="custom-control-input mb-3" id="scelta" name="scelta" value=<%=ordine.getId()%>>
	
	<%
	}
	}
	%>
</form>
</body>
</html>