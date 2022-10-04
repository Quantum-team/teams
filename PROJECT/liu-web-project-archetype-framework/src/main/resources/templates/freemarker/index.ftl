<!DOCTYPE html>

<!-- 
 * @author 刘老师
 * 
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 -->
 
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Probably the most complete UI kit out there. Multiple functionalities and controls added,  extended color palette and beautiful typography, designed as its own extended version of Bootstrap at  the highest level of quality.                             ">
    <meta name="author" content="Webpixels">
    <title>今天你练Java了吗？ </title>
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700,800|Roboto:400,500,700" rel="stylesheet">
    <!-- Theme CSS -->
    <link type="text/css" href="/assets/css/theme.css" rel="stylesheet">
    <!-- Demo CSS - No need to use these in your project -->
    <link type="text/css" href="/assets/css/demo.css" rel="stylesheet">
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-transparent navbar-dark bg-dark py-4">
      <div class="container">
        <a class="navbar-brand" href="./"><strong>Boomerang</strong> UI Kit</a>
        <button class="navbar-toggler" type="button" data-action="offcanvas-open" data-target="#navbar_main" aria-controls="navbar_main" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse offcanvas-collapse" id="navbar_main">
          <ul class="navbar-nav ml-auto align-items-lg-center">
            <h6 class="dropdown-header font-weight-600 d-lg-none px-0">Menu</h6>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbar_main_dropdown_1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">页面</a>
              <div class="dropdown-menu" aria-labelledby="navbar_1_dropdown_1">
                <a class="dropdown-item" href="index">主页</a>
                <a class="dropdown-item" href="list/">列表</a>
                <a class="dropdown-item" href="./pages/about.html">上传</a>
                <a class="dropdown-item" href="menu/list_product">购买</a>
              </div>
            </li>
            <li class="nav-item">
              <a href="https://github.com/webpixels/boomerang-ui-kit/archive/master.zip" target="_blank" class="nav-link d-lg-none">Download Free</a>
              <a href="logout" target="" class="btn btn-sm bg-white d-none d-lg-inline-flex">退出</a>
            </li>
            <div class="dropdown-divider d-lg-none my-4"></div>
            <h6 class="dropdown-header font-weight-600 d-lg-none px-0">Social Media</h6>
          </ul>
        </div>
      </div>
    </nav>
    <main class="main">
      <section class="spotlight parallax bg-cover bg-size--cover" data-spotlight="fullscreen" style="background-image: url('assets/images/backgrounds/1.jpg')">
        <span class="mask bg-primary alpha-7"></span>
        <div class="spotlight-holder py-lg pt-lg-xl">
          <div class="container d-flex align-items-center no-padding" style="margin-top: 7%;">
            <div class="col">
              <div class="row cols-xs-space align-items-center text-center text-md-left justify-content-center">
                <div class="col-7">
                  <div class="text-center mt-5">
                    <h2 class="heading display-4 font-weight-400 text-white mt-5 animated" data-animation-in="fadeInUp" data-animation-delay="2000">
                      <span class="font-weight-700">今天你练 Java 了吗？</span>
                    </h2>
                    <p class="lead text-white mt-3 lh-180 c-white animated" data-animation-in="fadeInUp" data-animation-delay="2500">
                      <strong class="text-white">——  老刘 —— </strong>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
    
    <#include "footer.ftl">
    
    <!-- Core -->
    <script src="./assets/vendor/jquery/jquery.min.js"></script>
    <script src="./assets/vendor/popper/popper.min.js"></script>
    <script src="./assets/js/bootstrap/bootstrap.min.js"></script>
    <!-- FontAwesome 5 -->
    <script src="./assets/vendor/fontawesome/js/fontawesome-all.min.js" defer></script>
    <!-- Page plugins -->
    <script src="./assets/vendor/bootstrap-select/js/bootstrap-select.min.js"></script>
    <script src="./assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
    <script src="./assets/vendor/input-mask/input-mask.min.js"></script>
    <script src="./assets/vendor/nouislider/js/nouislider.min.js"></script>
    <script src="./assets/vendor/textarea-autosize/textarea-autosize.min.js"></script>
    <!-- Theme JS -->
    <script src="./assets/js/theme.js"></script>
  </body>
</html>
