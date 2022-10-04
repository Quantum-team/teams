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
        <div class="content-inner content-docs">
          <div class="container">
            <div class="pt-3 pb-4 mb-4 border-bottom">
              <div class="row">
                <div class="col-lg-6">
                  <h2 class="heading h2 font-weight-bold">模拟手机购买和支付页面</h2>
                  <h5>以下表格用于模拟<a style="color: red;">手机浏览器支付</a>请求，手机端会自动调用沙箱支付宝APP，完成支付业务。</h5>
                  <h5>请使用手机自带原生浏览器，<a style="color: red;">不要用百度/QQ等内嵌搜索引擎！！</a></h5>
				</div>
				<div class="col-lg-12">
					<div class="code-example">
						<table class="table table-hover align-items-center">
							<thead>
								<tr>
									<th scope="col">
										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input"
												id="th_checkbox" /> <label class="custom-control-label"
												for="th_checkbox"></label>
										</div>
									</th>
									<th scope="col">图片</th>
									<th scope="col">商品信息</th>
									<th scope="col">价格</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody id="t_1">
								<tr>
									<td>1</td>
									<td>12</td>
									<td>世界上最贵的豆豆鞋</td>
									<td>9.9</td>
									<td>
										<button id="pay-id" value="1" type="button" class="btn btn-secondary btn-animated btn-animated-y">
										    <span class="btn-inner--visible">马上购买</span>
										    <span class="btn-inner--hidden"><i class="fas fa-shopping-cart"></i></span>
										</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
            </div>
              <div class="row">
                <div class="col-lg-6">
                  <h2 class="heading h2 font-weight-bold">模拟PC购买和支付页面</h2>
                  <h5>以下表格用于模拟<a style="color: red;">PC端Web浏览器支付</a>请求，使用手机端沙箱支付宝APP扫码完成支付业务。</h5>
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
									<th scope="col">图片</th>
									<th scope="col">商品信息</th>
									<th scope="col">价格</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody id="t_2">
								<tr>
									<td>1</td>
									<td><img src="../assets/images/slider/dd-shoes.jpg" style="width: 200px; height: 200px;"></td>
									<td><a style="font-size: 26px;">世界上最贵的豆豆鞋</a></td>
									<td><a style="font-size: 26px; color: red">9.9</a></td>
									<td>
										<button id="pay-id-pc" value="1" type="button" class="btn btn-secondary btn-animated btn-animated-y">
										    <span class="btn-inner--visible">马上购买</span>
										    <span class="btn-inner--hidden"><i class="fas fa-shopping-cart"></i></span>
										</button>
									</td>
								</tr>
								<tr>
									<td>2</td>
									<td><img src="../assets/images/slider/dd-shoes2.jpg" style="width: 200px; height: 200px;"></td>
									<td><a style="font-size: 26px;">世界上最贵的豆豆鞋</a></td>
									<td><a style="font-size: 26px; color: red">19.9</a></td>
									<td>
										<button id="pay-id-pc" value="2" type="button" class="btn btn-secondary btn-animated btn-animated-y">
										    <span class="btn-inner--visible">马上购买</span>
										    <span class="btn-inner--hidden"><i class="fas fa-shopping-cart"></i></span>
										</button>
									</td>
								</tr>
							</tbody>
						</table>
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
			
			$("#pay-id").click(function(event) {
				var id = $("#pay-id").val();
				window.location.href = "../payment/checkout/" + id;
			});
			
			$("#pay-id-pc").click(function(event) {
				var id_pc = $("#pay-id-pc").val();
				window.location.href = "../payment/checkout_pc/" + id_pc;
			});
			

		});

	</script>
  </body>
</html>