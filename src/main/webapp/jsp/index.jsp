<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
	<h1 class="text-center">Vending Machine</h1>
	<h2 id="errorMessage"></h2>
	<hr/>
	<div class="container">
            <div class ="row">
                <div class="row col-md-9" id="item-boxes">
                    <form role="form" action="getItemSelection">
                    <c:forEach var="currentItem" items="${itemList}">
                        <button class="col-lg-3 border item-selections" type="submit" style="margin: 15px" name="itemSelection" value="${currentItem.menuSelection}">
                            <p class="text-left">
                                <c:out value="${currentItem.menuSelection}"/>
                            </p>
                            <div class="text-center">
                                <p>
                                    <c:out value="${currentItem.itemName}"/>
                                </p>
                                <p>
                                   $<c:out value="${currentItem.cost}"/>
                                </p>
                                <br/>
                                <p>
                                    Quantity Left: ${currentItem.inventory}
                                </p>
                            </div>
                            
                        </button>
                    </c:forEach>
                    </form>
		</div>
		<div class="text-center col-md-3" id="forms-div">
                    <form role="form" action="addMoney">
			<h4>Total $ in</h4>
                        <p name="userBank" style="border: 1px solid; padding: 5px"> <c:out value="${userBank}"/></p>
                            <div class="row">
                                <input type="submit" name="dollar" class="border btn btn-default" value="Add Dollar"/>
                                <input type="submit" name="quarter" class="border btn btn-default" value="Add Quarter"/>
                            </div>
                            <div class="row">
                                <input type="submit" name="dime" class="border btn btn-default" value="Add Dime"/>
                                <input type="submit" name="nickel" class="border btn btn-default" value="Add Nickel"/>
                            </div>
                            <hr/>
                    </form>
			<h4>Messages</h4>
                        <div style="border: 1px solid; padding: 20px"><c:out value="${message}"/></div>
                        <br/>
                        
			<div class="row" id="item-row"><h4 class="col-md-6">Item:    </h4>
                             <div name="menuSelection" class="col-md-4" style="border: 1px solid; padding: 10px"><c:out value="${itemSelected.menuSelection}"/></div>
			</div>
                             <a href="makePurchase?menuSelection=${currentItem.menuSelection}" class="btn btn-default" value="Make Purchase">Make Purchase</a>
                            <hr/>
                        <div>
                            
			<h4>Change</h4>
			<div style="border: 1px solid; padding: 20px"> 
                            <c:out value = "${change.quarters}"/>
                            <c:out value= "${change.dimes}"/> 
                            <c:out value = "${change.nickels}"/> 
                        </div>
                            <a href="${pageContext.request.contextPath}/getChange" class="btn btn-default" value="Change Return">Get Change</a>
                        </div>
                </div>
            </div>
        </div>
	
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

