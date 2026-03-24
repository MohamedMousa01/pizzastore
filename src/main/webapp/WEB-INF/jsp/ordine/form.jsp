<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />

	    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<style>
			.ui-autocomplete-loading {
				background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
			}
			.error_field {
		        color: red;
		    }
		</style>
		<title>Inserisci le due date </title>

	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />

		<!-- Begin page content -->
		<main class="flex-shrink-0">
			<div class="container">

					<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="insert_stats_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>

					<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>

					<div class='card'>
					    <div class='card-header'>
					        <h5>Inserisci nuova data</h5>
					    </div>
					    <div class='card-body'>

					    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>


								<form:form method="post" modelAttribute="insert_stats_attr" action="${pageContext.request.contextPath}/ordine/stats" novalidate="novalidate" class="row g-3">



									<div class="col-md-6">
                                        <label for="dataInzio" class="form-label">Data e Ora inizio <span class="text-danger">*</span></label>
                                        <spring:bind path="dataInzio">
                                            <input type="datetime-local" class="form-control ${status.error ? 'is-invalid' : ''}" name="dataInzio" id="dataInzio" value="${status.value}" required>
                                        </spring:bind>
                                        <form:errors path="dataInzio" cssClass="text-danger" />
                                    </div>



									<div class="col-md-6">
                                        <label for="dataOrdine" class="form-label">Data e Ora FIne <span class="text-danger">*</span></label>
                                        <spring:bind path="dataOrdine">
                                            <input type="datetime-local" class="form-control ${status.error ? 'is-invalid' : ''}" name="dataOrdine" id="dataOrdine" value="${status.value}" required>
                                        </spring:bind>
                                        <form:errors path="dataOrdine" cssClass="text-danger" />
                                    </div>





									<div class="col-12">
										<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									</div>


								</form:form>


						<!-- end card-body -->
					    </div>
					<!-- end card -->
					</div>
				<!-- end container -->
				</div>

		<!-- end main -->
		</main>

		<jsp:include page="../footer.jsp" />

	</body>
</html>