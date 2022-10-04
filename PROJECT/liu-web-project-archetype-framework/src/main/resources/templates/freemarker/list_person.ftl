<!DOCTYPE html>

<!--
 * @author 刘老师
 *
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 -->

<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Probably the most complete UI kit out there. Multiple functionalities and controls added,  extended color palette and beautiful typography, designed as its own extended version of Bootstrap at  the highest level of quality.                             ">
    <meta name="author" content="Webpixels">
    <title>Boomerang UI Kit - Free Bootstrap Framework by Webpixels</title>
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700,800|Roboto:400,500,700" rel="stylesheet">
    <!-- Plugins -->
    <link rel="stylesheet" href="../assets/vendor/animate/animate.min.css" type="text/css">
    <!-- Theme CSS -->
    <link type="text/css" href="../assets/css/theme.css" rel="stylesheet">
    <!-- Demo CSS - No need to use these in your project -->
    <link rel="stylesheet" href="../assets/vendor/jquery-scrollbar/css/jquery-scrollbar.css">
    <link type="text/css" href="../assets/vendor/highlight/css/highlight.min.css" rel="stylesheet">
    <link type="text/css" href="../assets/css/demo.css" rel="stylesheet">
  </head>
  <body>
  
  <main class="main">
  
  	<#include "menu.ftl">
      
      <section class="content">
        <div class="header">
          <div class="navigation-trigger d-xl-none" data-action="aside-open" data-target=".sidebar">
            <div class="navigation-trigger-inner">
              <i class="navigation-trigger-line"></i>
              <i class="navigation-trigger-line"></i>
              <i class="navigation-trigger-line"></i>
            </div>
          </div>
          <div class="header-brand d-xl-none">
            <h1><a href="index.html">Boomerang wqe UI Kit</a></h1>
          </div>
				<div class="header-nav ml-auto">
					<ul>
						<li class="nav-item ml-4">
							<a href="../index" class="btn btn-sm btn-primary">主页</a></li>
					</ul>
				</div>
			</div>
			<div class="content-inner content-docs">
				<div class="container">
					<div class="pt-3 pb-4 mb-4 border-bottom">
						<div class="row">
							<div class="col-lg-6">
								<h2 class="heading h2 font-weight-bold">全列表</h2>
								<p>AJAX / JSON 加载的列表页面</p>
							</div>
							<div class="col-lg-12">
								<div class="code-example">
									<table class="table table-hover align-items-center">
										<thead>
											<tr>
												<th scope="col">
													<div class="custom-control custom-checkbox">
														<input type="checkbox" class="custom-control-input" id="th_checkbox" /> 
														<label class="custom-control-label" for="th_checkbox"></label>
													</div>
												</th>
												<th scope="col">Index</th>
												<th scope="col">Name</th>
												<th scope="col">Email</th>
												<th scope="col">Location</th>
											</tr>
										</thead>
										<tbody id="t_1">

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="content-inner content-docs">
				<div class="container">
					<div class="pt-3 pb-4 mb-4 border-bottom">
						<div class="row">
							<div class="col-lg-6">
								<h2 class="heading h2 font-weight-bold">分页列表</h2>
								<p>AJAX / JSON 加载的列表页面</p>
							</div>
							<div class="col-lg-12">
								<div class="code-example">
									<table class="table table-hover align-items-center">
										<thead>
											<tr>
												<th scope="col">
													<div class="custom-control custom-checkbox">
														<input type="checkbox" class="custom-control-input" id="th_checkbox" /> 
														<label class="custom-control-label" for="th_checkbox"></label>
													</div>
												</th>
												<th scope="col">Index</th>
												<th scope="col">Name</th>
												<th scope="col">Email</th>
												<th scope="col">Location</th>
											</tr>
										</thead>
										<tbody id="t_2">

										</tbody>
									</table>
									<nav aria-label="Page navigation example">
									    <ul class="pagination" id="p_1">
									        <li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
									        <li class="page-item"><a class="page-link" href="#">1</a></li>
									        <li class="page-item active"><a class="page-link" href="#">2</a></li>
									        <li class="page-item"><a class="page-link" href="#">3</a></li>
									        <li class="page-item"><a class="page-link" href="#">4</a></li>
									        <li class="page-item"><a class="page-link" href="#">5</a></li>
									        <li class="page-item"><a class="page-link" href="#">6</a></li>
									        <li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-right"></i></a></li>
									    </ul>
									</nav>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<#include "footer.ftl">
		</section>
    </main>
    <!-- Core -->
    <script src="../assets/vendor/jquery/jquery.min.js"></script>
    <script src="../assets/vendor/popper/popper.min.js"></script>
    <script src="../assets/js/bootstrap/bootstrap.min.js"></script>
    <!-- FontAwesome 5 -->
    <script src="../assets/vendor/fontawesome/js/fontawesome-all.min.js" defer></script>
    <!-- Scripts used only for this demo only - Remove these in your project -->
    <script src="../assets/vendor/jquery-scrollbar/js/jquery-scrollbar.min.js"></script>
    <script src="../assets/vendor/jquery-scrollLock/jquery-scrollLock.min.js"></script>
    <script src="../assets/vendor/sticky-kit/sticky-kit.min.js"></script>
    <script src="../assets/vendor/highlight/js/highlight.min.js"></script>
    <script src="../assets/vendor/clipboard-js/clipboard.min.js"></script>
    <script src="../assets/vendor/holderjs/holder.min.js"></script>
    <script src="../assets/js/demo.js"></script>
    <!-- Theme JS -->
    <script src="../assets/js/theme.js"></script>

	<script type="text/javascript">

		$(document).ready(function() {
			$.ajax({
				url : './jsonListPerson',
				type : "GET",
				cache : false,
				datatype : "JSON",
				contentType : "application/json",
				success : function(o) {
					var list = JSON.parse(o);
                    var content = "";

                    for(var i = 0; i < list.length; i ++) {
                        content +=
                            "<tr class=\"bg-white\" scope=\"row\">" +
	                            "<td>" +
	                                "<div class=\"custom-control custom-checkbox\">" +
	                                    "<input type=\"checkbox\" class=\"custom-control-input\" id=\"td_checkbox_" + (i+1) + "\" />" +
	                                        "<label class=\"custom-control-label\" for=\"td_checkbox_" + (i+1) + "\"></label>" +
	                                "</div>" +
	                            "</td>" +
	                            "<td>" + (i+1) + "</td>" +
	                            "<td>" +
	                                "<div class=\"media align-items-center\">" +
	                                    "<img src=\"../assets/images/prv/thumb-1.jpg\" class=\"avatar avatar-lg mr-3\" />" +
	                                    "<div class=\"media-body\">" +
	                                        "<h6 class=\"h5 font-weight-normal mb-0\">" + list[i].realName + "</h6>" +
	                                        "<span class=\"font-weight-normal text-muted\">" + list[i].job + "</span>" +
	                                    "</div>" +
	                                "</div>" +
	                            "</td>" +
	                            "<td>" + list[i].email + "</td>" +
	                            "<td>" + list[i].location + "</td>" +
                        	"</tr>";
                    }
                    
                    $("#t_1").html(content);
				}
			});
			
			
			doPaging(0, 5);
		});

		function paging(page, size) {
			
		}
		
		function doPaging(page, size) {
			$.ajax({
				url : "./jsonListPersonPaging/" + page + "/" + size,
				type : "GET",
				cache : false,
				datatype : "JSON",
				contentType : "application/json",
				success : function(o) {
					var jsonObject = JSON.parse(o);
					var list = jsonObject.content;
                    var contentData = "";
                    var contentPaging = "";

                    for(var i = 0; i < list.length; i ++) {
                    	contentData +=
                            "<tr class=\"bg-white\" scope=\"row\">" +
	                            "<td>" +
	                            	"<div class=\"custom-control custom-checkbox\">" +
	                            	"<input type=\"checkbox\" class=\"custom-control-input\" id=\"td_checkbox_2_" + (i+1) + "\" />" +
	                                	"<label class=\"custom-control-label\" for=\"td_checkbox_2_" + (i+1) + "\"></label>" +
	                        		"</div>" +
	                            "</td>" +
	                            "<td>" + (i+1) + "</td>" +
	                            "<td>" +
	                                "<div class=\"media align-items-center\">" +
	                                    "<img src=\"../assets/images/prv/thumb-1.jpg\" class=\"avatar avatar-lg mr-3\" />" +
	                                    "<div class=\"media-body\">" +
	                                        "<h6 class=\"h5 font-weight-normal mb-0\">" + list[i].realName + "</h6>" +
	                                        "<span class=\"font-weight-normal text-muted\">" + list[i].job + "</span>" +
	                                    "</div>" +
	                                "</div>" +
	                            "</td>" +
	                            "<td>" + list[i].email + "</td>" +
	                            "<td>" + list[i].location + "</td>" +
                        	"</tr>";
                    }
                    
                    contentPaging +=  "<li class=\"page-item\"><a class=\"page-link\" href=\"#\"><i class=\"fas fa-angle-left\"></i></a></li>";
                    
                    for(var i = 0; i < jsonObject.totalPages; i++) {

                    	if(page != i+1/* 当前页面非选中 */) 
                    		contentPaging +=  "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">" + (i+1) + "</a></li>";
                  		else
                  			contentPaging +=  "<li class=\"page-item active\"><a class=\"page-link\" href=\"#\">" + (i+1) + "</a></li>";
                    }
                    
                    contentPaging +=  "<li class=\"page-item\"><a class=\"page-link\" href=\"#\"><i class=\"fas fa-angle-right\"></i></a></li>";
                    
			        
                    $("#t_2").html(contentData);
                    $("#p_1").html(contentPaging);
				}
			});
		}
		
		
	</script>
  </body>
</html>