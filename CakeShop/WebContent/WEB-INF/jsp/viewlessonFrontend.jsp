<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- class Section -->
<section class="page-section class" id="class">
	<div class="container">
		<h2 class="page-section-heading text-center text-uppercase text-secondary mb-5">精選課程</h2>
			<div class="row">
			<c:forEach var="current" items="${messagelessonFrontend}" > 
			<div class="col-md-6 col-lg-4"> 
				<div class="class-item mx-auto" data-toggle="modal" data-target="#demo<c:out value="${current.lessonId}"/>">
					<div class="class-item-caption d-flex align-items-center justify-content-center h-100 w-100">
						<div class="class-item-caption-content text-center text-white">
							<i class="fas fa-3x fa-search-plus"></i>
                  		</div>
                	</div>
                    <img src="/CakeShop/WebContent/images/<c:out value="${current.lessonPicUrl }"/>" style=width:350px;height:300px; class="img-fluid" alt=""> 
                 	<div class="single-class">
                  		<div class="class-text-box">
                    		<h3 class="text-center"><c:out value="${current.lessonName}"/></h3>
                    		<p class="blog-meta">
                      			<span class="price"><i class="fas fa-dollar-sign"></i> <c:out value="${current.lessonPrice}"/></span>
                      			<span class="date"><i class="fas fa-calendar"></i><c:out value="${current.lessonDate}"/></span>
                    		</p>
                    		<p class="excerpt text-justify"><c:out value="${current.lessonDescription}"/></p>                 
                  		</div>
                	</div>
             	</div>  
         	</div> 
			</c:forEach>
			</div>
	</div>
</section>

<c:forEach var="current" items="${messagelessonFrontend}"> 

  <div class="class-modal modal fade" id="demo<c:out value="${current.lessonId}"/>" tabindex="-1" role="dialog" aria-labelledby="classModal1Label" aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
          <div class="modal-content">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">
                <i class="fas fa-times"></i>
              </span>
            </button>
            <div class="modal-body text-center">
              <div class="container">
                <div class="row justify-content-center">
                  <div class="col-lg-8">
                    <!-- class Modal - Title -->
                    <h3 class="text-secondary mt-4 mb-4">【<c:out value="${current.lessonName}"/>】</h3>
                    <!-- class Modal - Image -->
                    <img src="/CakeShop/WebContent/images/<c:out value="${current.lessonPicUrl }"/>" style=width:350px;height:300px; class="img-fluid" alt=""> 
    
                    <!-- class Modal - Text -->
                    <div class="single-class">
                      <div class="class-text-box">
                        <p class="blog-meta">
                          <span class="price"><i class="fas fa-dollar-sign"></i><c:out value="${current.lessonPrice}"/></span>
                          <span class="date"><i class="fas fa-calendar"></i><c:out value="${current.lessonDate}"/></span>
                        </p>
                        <p class="excerpt text-justify"><c:out value="${current.lessonDescription}"/></p>
                       
                        <button class="btn cyan-btn mb-3" href="#" data-dismiss="modal">
                          <i class="fas fa-times fa-fw"></i>
                          Close Window
                        </button>
                      </div>
                    </div>
    
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
</c:forEach>

