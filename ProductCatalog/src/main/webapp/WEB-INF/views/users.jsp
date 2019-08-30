<%@ include file="../common/header.jspf"%>
<%@ include file="../common/session/session-navbar.jspf"%>

<div>
	<h1>${userName}'sCatalog</h1>
</div>



<div class="Container">
	body content

	<h3>
		<font color="red"> <em> ${error_message} </em>
		</font>
	</h3>


	<div class="row">
		<div class="col-md-12">

			<div id="mdb-lightbox-ui"></div>

			<div class="mdb-lightbox">

				<c:forEach items="${pxtList}" var="pxt">
					<figure class="col-md-4">

						<img alt="picture" src="data:image/jpg;Base64,${pxt.pxtImage}"
							class="img-fluid" width="400" height="200">

						<table class="table table-striped">
							<tr>
								<td><strong>Name: </strong> ${pxt.pxtName}</td>
								<td><strong>Category: </strong>${pxt.pxtCategory}</td>
							</tr>
							<tr>
								<td><a class="btn btn-danger"
									href="/delete-product.se?id=${pxt.pxtID}&name=${pxt.pxtName}&category=${pxt.pxtCategory}&owner=${pxt.pxtOwner}">Delete</a></td>
								<td><a class="btn btn-success"
									href="/update-product.se?id=${pxt.pxtID}&name=${pxt.pxtName}&category=${pxt.pxtCategory}&owner=${pxt.pxtOwner}">Update</a></td>
							</tr>

							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr></tr>

						</table>
						
					</figure>
				</c:forEach>

			</div>

		</div>
	</div>

	<!-- 
<div class="row">
  <div class="col-md-12">

    <div id="mdb-lightbox-ui"></div>

    <div class="mdb-lightbox">

      <figure class="col-md-4">
        <a href="https://mdbootstrap.com/img/Photos/Lightbox/Original/img%20(145).jpg" data-size="1600x1067">
          <img alt="picture" src="https://mdbootstrap.com/img/Photos/Lightbox/Thumbnail/img%20(145).jpg" class="img-fluid">
        </a>
      </figure>
      
      </div>

  </div>
</div>
-->
	<!--  
	<table class="table table-striped" >
		<thead>
			<th>Item Name</th>		
			<th>Item Category</th>
			<th>Image</th>
			
		</thead>
		<tbody>
		
			<c:forEach items="${pxtList}" var="pxt">
			
			

			
				<tr>
					<td>${pxt.pxtName}</td>			
					<td>${pxt.pxtCategory}</td>
					<td><img src="data:image/jpg;Base64,${pxt.pxtImage}" width="40" height="50"/></td>
					
					<td><a class="btn btn-danger" href="/delete-product.se?id=${pxt.pxtID}&name=${pxt.pxtName}&category=${pxt.pxtCategory}&owner=${pxt.pxtOwner}">Delete</a></td>
								
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
-->

	Work in progress


</div>

<%@ include file="../common/footer.jspf"%>