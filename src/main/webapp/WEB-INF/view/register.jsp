<!DOCTYPE html>
<html lang="en">
<head>
  <title>Tobuz - Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="" />
  <meta name="robots" content="max-image-preview:large" />
  <link rel="canonical" href="" />
  <meta name="generator" content="All in One SEO Pro (AIOSEO) 4.2.8 " />
  <meta property="og:locale" content="en_US" />
  <meta property="og:site_name" content="" />
  <meta property="og:type" content="website" />
  <meta property="og:title" content="" />
  <meta property="og:description" content="" />
  <meta property="og:url" content="" />
  <meta property="og:image" content="" />
  <meta property="og:image:secure_url" content="" />
  <meta property="og:image:width" content="" />
  <meta property="og:image:height" content="" />
  <meta name="twitter:card" content="summary" />
  <meta name="twitter:title" content="" />
  <meta name="twitter:description" content="" />
  <meta name="twitter:image" content="" />
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

 <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
  
</head>




<style type="text/css">


fieldset { 

  padding: 15px; 
  max-width: 90%;
 
}

section { padding: 0 15px; }

.CaptchaWrap { position: relative; }
.CaptchaTxtField { 
  border-radius: 5px; 
  border: 1px solid #ccc; 
  display: block;  
  box-sizing: border-box;
  border-radius: 25px;
}

#UserCaptchaCode { 
  padding: 15px 10px; 
  outline: none; 
  font-size: 16px; 
  font-weight: normal; 
  font-family: 'Open Sans', sans-serif;
  width: 280px;
}
#CaptchaImageCode { 
  text-align:center;
  margin-top: 5px;
  padding: 0px 0;
  width: 280px;
  overflow: hidden;
}

.capcode { 
  font-size: 46px; 
  display: block; 
  -moz-user-select: none;
  -webkit-user-select: none;
  user-select: none; 
  cursor: default;
  letter-spacing: 1px;
  color: #ccc;
  font-family: 'Roboto Slab', serif;
  font-weight: 100;
  font-style: italic;
}

.ReloadBtn { 
  background:url('https://cdn3.iconfinder.com/data/icons/basic-interface/100/update-64.png') left top no-repeat;   
  background-size : 100%;
  width: 32px; 
  height: 30px;
  border: 0px; outline none;
  position: absolute; 
   bottom: 30px;
  left : 310px;
  outline: none;
  cursor: pointer; /**/
}
.btnSubmit {
  margin-top: 15px;
  border: 0px;
  padding: 10px 20px; 
  border-radius: 5px;
  font-size: 18px;
  background-color: #1285c4;
  color: #fff;
  cursor: pointer;
}

.error { 
  color: red; 
  font-size: 12px; 
  display: none; 
}
.success {
  color: green;
  font-size: 18px;
  margin-bottom: 15px;
  display: none;
}

</style>


<body>

<!-- topbar start -->
<header class="fix-navbar">


<div class="topbar desktop-view">
<div class="container" style="float: right">
<div class="row">
<div class="col-lg-3 col-xl-2 col-md-3">
<!-- social icon desktop start -->
<div class="social-icon">
<ul>
<li><a href="#"><i class="fa fa-facebook"></i></a></li>
<li><a href="#"><i class="fa fa-twitter"></i></a></li>
<li><a href="#"><i class="fa fa-instagram"></i></a></li>
<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
</ul>
</div>
<!-- social icon desktop finish -->
</div>
<!-- <div class="col-lg-4 col-xl-4 col-md-3">
Searchbar scrolling top desktop start
<div class="input-group a1">
  <input type="search" class="form-control round-bar" placeholder="Business For Sale india" aria-label="Search" aria-describedby="search-addon" />
  <button type="button" class="btn round-button"><i class="fa fa-search"></i></button>
</div>
Searchbar scrolling top desktop finish
</div> -->

<div class="col-lg-5 col-xl-6 col-md-6 offset-lg-0 offset-xl-2 offset-md-0">
<!-- Add Listing top desktop start -->
<button type="button" class="btn btn-warning add-list-button" onclick="addListings();">+ Add Listing </button>
<!-- Add Listing top desktop Finish -->
<!-- login and ragister top desktop start -->
<span class="login-area"><a href="login">Login</a> | <a href="register">Register</a> </span>
<!-- login and ragister top desktop finish -->
<!-- languages top desktop Start -->
 <div class="lang" id="google_translate_element">
<!-- <select class="form-select form-select-sm" aria-label=".form-select-sm example">
    <option value="Afrikaans">af</option>
    <option value="Albanian">sq</option>
    <option value="Amharic">am</option>
    <option value="Arabic">ar</option>
    <option value="Armenian">hy</option>
    <option value="Assamese">as</option>
    <option value="Aymara">ay</option>
    <option value="Azerbaijani">az</option>
    <option value="Bambara">bm</option>
    <option value="Basque">eu</option>
    <option value="Belarusian">be</option>
    <option value="Bengali">bn</option>
    <option value="Bhojpuri">bho</option>
    <option value="Bosnian">bs</option>
    <option value="Bulgarian">bg</option>
    <option value="Burmese">my</option>
    <option value="Catalan">ca</option>
    <option value="Cebuano">ceb</option>
    <option value="Chichewa">ny</option>
    <option value="Chinese (Simplified)">zh-CN</option>
    <option value="Chinese (Traditional)">zh-TW</option>
    <option value="Corsican">co</option>
    <option value="Croatian">hr</option>
    <option value="Czech">cs</option>
    <option value="Danish">da</option>
    <option value="Dhivehi">dv</option>
    <option value="Dogri">doi</option>
    <option value="Dutch">nl</option>
    <option value="Esperanto">eo</option>
    <option value="Estonian">et</option>
    <option value="Ewe">ee</option>
    <option value="Filipino">tl</option>
    <option value="Finnish">fi</option>
    <option value="French">fr</option>
    <option value="Frisian">fy</option>
    <option value="Galician">gl</option>
    <option value="Georgian">ka</option>
    <option value="German">de</option>
    <option value="Greek">el</option>
    <option value="Guarani">gn</option>
    <option value="Gujarati">gu</option>
    <option value="Haitian Creole">ht</option>
    <option value="Hausa">ha</option>
    <option value="Hawaiian">haw</option>
    <option value="Hebrew">iw</option>
    <option value="Hindi">hi</option>
    <option value="Hmong">hmn</option>
    <option value="Hungarian">hu</option>
    <option value="Icelandic">is</option>
    <option value="Igbo">ig</option>
    <option value="Ilocano">ilo</option>
    <option value="Indonesian">id</option>
    <option value="Irish Gaelic">ga</option>
    <option value="Italian">it</option>
    <option value="Japanese">ja</option>
    <option value="Javanese">jw</option>
    <option value="Kannada">kn</option>
    <option value="Kazakh">kk</option>
    <option value="Khmer">km</option>
    <option value="Kinyarwanda">rw</option>
    <option value="Konkani">gom</option>
    <option value="Korean">ko</option>
    <option value="Krio">kri</option>
    <option value="Kurdish (Kurmanji)">ku</option>
    <option value="Kurdish (Sorani)">ckb</option>
    <option value="Kyrgyz">ky</option>
    <option value="Lao">lo</option>
    <option value="Latin">la</option>
    <option value="Latvian">lv</option>
    <option value="Lingala">ln</option>
    <option value="Lithuanian">lt</option>
    <option value="Luganda">lg</option>
    <option value="Luxembourgish">lb</option>
    <option value="Macedonian">mk</option>
    <option value="Maithili">mai</option>
    <option value="Malagasy">mg</option>
    <option value="Malay">ms</option>
    <option value="Malayalam">ml</option>
    <option value="Maltese">mt</option>
    <option value="Maori">mi</option>
    <option value="Marathi">mr</option>
    <option value="Meiteilon (Manipuri)">mni-Mtei</option>
    <option value="Mizo">lus</option>
    <option value="Mongolian">mn</option>
    <option value="Nepali">ne</option>
    <option value="Norwegian">no</option>
    <option value="Odia (Oriya)">or</option>
    <option value="Oromo">om</option>
    <option value="Pashto">ps</option>
    <option value="Persian">fa</option>
    <option value="Polish">pl</option>
    <option value="Portuguese">pt</option>
    <option value="Punjabi">pa</option>
    <option value="Quechua">qu</option>
    <option value="Romanian">ro</option>
    <option value="Russian">ru</option>
    <option value="Samoan">sm</option>
    <option value="Sanskrit">sa</option>
    <option value="Scots Gaelic">gd</option>
    <option value="Sepedi">nso</option>
    <option value="Serbian">sr</option>
    <option value="Sesotho">st</option>
    <option value="Shona">sn</option>
    <option value="Sindhi">sd</option>
    <option value="Sinhala">si</option>
    <option value="Slovak">sk</option>
    <option value="Slovenian">sl</option>
    <option value="Somali">so</option>
    <option value="Spanish">es</option>
    <option value="Sundanese">su</option>
    <option value="Swahili">sw</option>
    <option value="Swedish">sv</option>
    <option value="Tajik">tg</option>
    <option value="Tamil">ta</option>
    <option value="Tatar">tt</option>
    <option value="Telugu">te</option>
    <option value="Thai">th</option>
    <option value="Tigrinya">ti</option>
    <option value="Tsonga">ts</option>
    <option value="Turkish">tr</option>
    <option value="Turkmen">tk</option>
    <option value="Twi">ak</option>
    <option value="Ukrainian">uk</option>
    <option value="Urdu">ur</option>
    <option value="Uyghur">ug</option>
    <option value="Uzbek">uz</option>
    <option value="Vietnamese">vi</option>
    <option value="Welsh">cy</option>
    <option value="Xhosa">xh</option>
    <option value="Yiddish">yi</option>
    <option value="Yoruba">yo</option>
    <option value="Zulu">zu</option>
</select>
 -->
</div> 
<!-- languages top desktop Finish -->
<!-- country top desktop Start -->
<span class="country">
<div class="dropdown coun-flag">
  <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <img src="images/india-flag.jpg" alt="India" title="India" class="flag"> IN
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="#"><img src="images/ae-flag.jpg" alt="AE" title="AE" class="flag"> AE</a>
    <a class="dropdown-item" href="#"><img src="images/au-flag.jpg" alt="AU" title="AU" class="flag"> AU</a>
	<a class="dropdown-item" href="#"><img src="images/bd-flag.jpg" alt="BD" title="BD" class="flag"> BD</a>
	<a class="dropdown-item" href="#"><img src="images/bh-flag.jpg" alt="BH" title="BH" class="flag"> BH</a>
	<a class="dropdown-item" href="#"><img src="images/lk-flag.jpg" alt="LK" title="LK" class="flag"> LK</a>
	<a class="dropdown-item" href="#"><img src="images/nz-flag.jpg" alt="NZ" title="NZ" class="flag"> AU</a>
	<a class="dropdown-item" href="#"><img src="images/om-flag.jpg" alt="OM" title="OM" class="flag"> OM</a>
	<a class="dropdown-item" href="#"><img src="images/pk-flag.jpg" alt="PK" title="PK" class="flag"> PK</a>
	<a class="dropdown-item" href="#"><img src="images/sa-flag.jpg" alt="SA" title="SA" class="flag"> SA</a>
	<a class="dropdown-item" href="#"><img src="images/sg-flag.jpg" alt="SG" title="SG" class="flag"> SG</a>
	<a class="dropdown-item" href="#"><img src="images/uk-flag.jpg" alt="UK" title="UK" class="flag"> UK</a>
  </div>
</div>
</span>
<!-- country top desktop finish -->
</div>


</div>
</div>
</div>



<!-- topbar finish -->
<!-- navigation start -->
<div class="container">
<nav class="navbar navbar-expand-lg navbar-light fixed-top">
<!-- navigation toggle start -->
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
 <!-- navigation toggle finish --> 
  <a class="navbar-brand" href="/"><img src="images/logo.png" alt="tobuz" title="Tobuz"></a>
  <div class="mobile-menu-right">
 <!-- Search icon mobile start -->
 <div class="searchicon"> <i class="fa fa-search"></i></div>
 <!-- Search icon mobile finsh -->
 <!-- languages bar mobile start -->
 <div class="lang-mobile">
<span class="lang">
<select class="form-select form-select-sm" aria-label=".form-select-sm example">
   <option value="Afrikaans">af</option>
    <option value="Albanian">sq</option>
    <option value="Amharic">am</option>
    <option value="Arabic">ar</option>
    <option value="Armenian">hy</option>
    <option value="Assamese">as</option>
    <option value="Aymara">ay</option>
    <option value="Azerbaijani">az</option>
    <option value="Bambara">bm</option>
    <option value="Basque">eu</option>
    <option value="Belarusian">be</option>
    <option value="Bengali">bn</option>
    <option value="Bhojpuri">bho</option>
    <option value="Bosnian">bs</option>
    <option value="Bulgarian">bg</option>
    <option value="Burmese">my</option>
    <option value="Catalan">ca</option>
    <option value="Cebuano">ceb</option>
    <option value="Chichewa">ny</option>
    <option value="Chinese (Simplified)">zh-CN</option>
    <option value="Chinese (Traditional)">zh-TW</option>
    <option value="Corsican">co</option>
    <option value="Croatian">hr</option>
    <option value="Czech">cs</option>
    <option value="Danish">da</option>
    <option value="Dhivehi">dv</option>
    <option value="Dogri">doi</option>
    <option value="Dutch">nl</option>
    <option value="Esperanto">eo</option>
    <option value="Estonian">et</option>
    <option value="Ewe">ee</option>
    <option value="Filipino">tl</option>
    <option value="Finnish">fi</option>
    <option value="French">fr</option>
    <option value="Frisian">fy</option>
    <option value="Galician">gl</option>
    <option value="Georgian">ka</option>
    <option value="German">de</option>
    <option value="Greek">el</option>
    <option value="Guarani">gn</option>
    <option value="Gujarati">gu</option>
    <option value="Haitian Creole">ht</option>
    <option value="Hausa">ha</option>
    <option value="Hawaiian">haw</option>
    <option value="Hebrew">iw</option>
    <option value="Hindi">hi</option>
    <option value="Hmong">hmn</option>
    <option value="Hungarian">hu</option>
    <option value="Icelandic">is</option>
    <option value="Igbo">ig</option>
    <option value="Ilocano">ilo</option>
    <option value="Indonesian">id</option>
    <option value="Irish Gaelic">ga</option>
    <option value="Italian">it</option>
    <option value="Japanese">ja</option>
    <option value="Javanese">jw</option>
    <option value="Kannada">kn</option>
    <option value="Kazakh">kk</option>
    <option value="Khmer">km</option>
    <option value="Kinyarwanda">rw</option>
    <option value="Konkani">gom</option>
    <option value="Korean">ko</option>
    <option value="Krio">kri</option>
    <option value="Kurdish (Kurmanji)">ku</option>
    <option value="Kurdish (Sorani)">ckb</option>
    <option value="Kyrgyz">ky</option>
    <option value="Lao">lo</option>
    <option value="Latin">la</option>
    <option value="Latvian">lv</option>
    <option value="Lingala">ln</option>
    <option value="Lithuanian">lt</option>
    <option value="Luganda">lg</option>
    <option value="Luxembourgish">lb</option>
    <option value="Macedonian">mk</option>
    <option value="Maithili">mai</option>
    <option value="Malagasy">mg</option>
    <option value="Malay">ms</option>
    <option value="Malayalam">ml</option>
    <option value="Maltese">mt</option>
    <option value="Maori">mi</option>
    <option value="Marathi">mr</option>
    <option value="Meiteilon (Manipuri)">mni-Mtei</option>
    <option value="Mizo">lus</option>
    <option value="Mongolian">mn</option>
    <option value="Nepali">ne</option>
    <option value="Norwegian">no</option>
    <option value="Odia (Oriya)">or</option>
    <option value="Oromo">om</option>
    <option value="Pashto">ps</option>
    <option value="Persian">fa</option>
    <option value="Polish">pl</option>
    <option value="Portuguese">pt</option>
    <option value="Punjabi">pa</option>
    <option value="Quechua">qu</option>
    <option value="Romanian">ro</option>
    <option value="Russian">ru</option>
    <option value="Samoan">sm</option>
    <option value="Sanskrit">sa</option>
    <option value="Scots Gaelic">gd</option>
    <option value="Sepedi">nso</option>
    <option value="Serbian">sr</option>
    <option value="Sesotho">st</option>
    <option value="Shona">sn</option>
    <option value="Sindhi">sd</option>
    <option value="Sinhala">si</option>
    <option value="Slovak">sk</option>
    <option value="Slovenian">sl</option>
    <option value="Somali">so</option>
    <option value="Spanish">es</option>
    <option value="Sundanese">su</option>
    <option value="Swahili">sw</option>
    <option value="Swedish">sv</option>
    <option value="Tajik">tg</option>
    <option value="Tamil">ta</option>
    <option value="Tatar">tt</option>
    <option value="Telugu">te</option>
    <option value="Thai">th</option>
    <option value="Tigrinya">ti</option>
    <option value="Tsonga">ts</option>
    <option value="Turkish">tr</option>
    <option value="Turkmen">tk</option>
    <option value="Twi">ak</option>
    <option value="Ukrainian">uk</option>
    <option value="Urdu">ur</option>
    <option value="Uyghur">ug</option>
    <option value="Uzbek">uz</option>
    <option value="Vietnamese">vi</option>
    <option value="Welsh">cy</option>
    <option value="Xhosa">xh</option>
    <option value="Yiddish">yi</option>
    <option value="Yoruba">yo</option>
    <option value="Zulu">zu</option>
</select></span>
</div>
 <!-- languages bar mobile finish -->
  <!-- country bar mobile start -->
  <div class="mobile-country">
  <span class="country">
<div class="dropdown coun-flag">
  <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   <img src="images/india-flag.jpg" alt="India" title="India"> IN
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="#"><img src="images/ae-flag.jpg" alt="AE" title="AE" class="flag"> AE</a>
    <a class="dropdown-item" href="#"><img src="images/au-flag.jpg" alt="AU" title="AU" class="flag"> AU</a>
	<a class="dropdown-item" href="#"><img src="images/bd-flag.jpg" alt="BD" title="BD" class="flag"> BD</a>
	<a class="dropdown-item" href="#"><img src="images/bh-flag.jpg" alt="BH" title="BH" class="flag"> BH</a>
	<a class="dropdown-item" href="#"><img src="images/lk-flag.jpg" alt="LK" title="LK" class="flag"> LK</a>
	<a class="dropdown-item" href="#"><img src="images/nz-flag.jpg" alt="NZ" title="NZ" class="flag"> AU</a>
	<a class="dropdown-item" href="#"><img src="images/om-flag.jpg" alt="OM" title="OM" class="flag"> OM</a>
	<a class="dropdown-item" href="#"><img src="images/pk-flag.jpg" alt="PK" title="PK" class="flag"> PK</a>
	<a class="dropdown-item" href="#"><img src="images/sa-flag.jpg" alt="SA" title="SA" class="flag"> SA</a>
	<a class="dropdown-item" href="#"><img src="images/sg-flag.jpg" alt="SG" title="SG" class="flag"> SG</a>
	<a class="dropdown-item" href="#"><img src="images/uk-flag.jpg" alt="UK" title="UK" class="flag"> UK</a>
  </div>
</div>
</span>
  </div>
  <!-- country bar mobile finish -->
</div>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
	 <!-- mobile menu close button start -->
	<li class="d-inline d-lg-none">
     <button data-toggle="collapse" data-target="#navbarSupportedContent" class="close float-right"><img src="images/close.png"/></button></li>
	 <!-- mobile menu close button finish -->
	 <!-- login and register area mobile start -->
	 <li class="login-area d-lg-none">
	 <img src="images/moble-menu-login.png" style="margin-right:10px;"/> <a href="login">Login</a> | <a href="register">Register</a> 
	 </li>
	 <!-- login and register area mobile finish -->
	  <!-- Add Listing mobile start -->
	 <li class="add-list d-lg-none">
	 <img src="images/plus.png" width="15" height="15" style="margin-right:5px;" onclick="addListings ();/> Add Listing  
	 </li>
	 <!-- Add Listing mobile Finish -->
	 <!-- mobile menu start -->
	 
     <li class="nav-item"> <a class="nav-link" href="index">Home</a></li>
    <li class="nav-item dropdown"> 
	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Buy a Business
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="businessForSale">Business For Sale</a>
          <a class="dropdown-item" href="commercialForSaleGrid">Commercial for a sale</a>
          <a class="dropdown-item" href="distress-sale.html">Distress Sale</a>
		  <a class="dropdown-item" href="franchiseeOpportunitiesGrid">Franchisee Opportunities</a>
        </div>
	</li>
	<li class="nav-item dropdown">
<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Sell a Business
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="buyers-investors-grid.html">Buyers / Investors</a>
          <a class="dropdown-item" href="brokers">Brokers</a>
          <a class="dropdown-item" href="businesServices">Business Services</a>
		  
        </div>
	</li>
	
	<li class="nav-item"> <a class="nav-link" href="realEstate">Real Estate</a></li>
	<li class="nav-item dropdown"> 
	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Investors
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="business-advisory.html">Business Advisory</a>
          <a class="dropdown-item" href="business-setup.html">Business Setup</a>
          <a class="dropdown-item" href="business-valuation.html">Business Valuation</a>
		  <a class="dropdown-item" href="#">Business Loan</a>
        </div>
	</li>
	<li class="nav-item"> <a class="nav-link" href="blog">Blog</a></li>
	<li class="nav-item"> <a class="nav-link" href="about">About Us</a></li>
	<li class="nav-item"> <a class="nav-link" href="contact">Contact</a></li>
	
	<!-- mobile menu finish -->
	<!-- social icon area start -->
	<li class="social d-lg-none">
	<span class="icon-social"><a href="#"><i class="fa fa-facebook"></i></a></span> <span class="icon-social"><a href="#"><i class="fa fa-twitter"></i></a></span> <span class="icon-social"><a href="#"><i class="fa fa-instagram"></i></a></span> <span class="icon-social"><a href="#"><i class="fa fa-linkedin"></i></a></span> 
	 </li>
	 <!-- social icon area finish -->
  </ul>
  </div>
</nav>
</div>
<!-- navigation finish -->
</header>
<!-- register form area start -->
<section class="padtb50">
<div class="container">
<div class="row justify-content-md-center mb-20px">
<div class="col-md-12 col-lg-12 col-xl-10 about-us-text-area">
<div class="ragisterarea">
<!-- register form title start -->
<h1 class="ml4">Register on Tobuz <Br/><span>Please fill up following details to register on Tobuz.com</span></h1>
<!-- register form title finish -->
<div class="alert alert-danger" style="border-radius: 15px; width:90% ;margin-left: 5%">

</div>
<div class="alert alert-success" style="border-radius: 15px; width:90% ;margin-left: 5%"> </div>
<form>
<div class="row justify-content-md-center">
<div class="col-md-6 col-lg-6 col-xl-5">
<!-- Name start -->
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><img src="images/name.png" width="33" height="19"/></span>
  </div>
  <input type="text" id="name" class="form-control login-user" placeholder="Name" aria-label="Name" aria-describedby="basic-addon1">
</div>
<!-- Name finish -->
<!-- Phone No start -->
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><img src="images/phone.png" width="33" height="21"/></span>
  </div>
  <input type="text" id="phone" class="form-control login-user" placeholder="Phone No" aria-label="phone" aria-describedby="basic-addon1">
</div>
<!-- Phone No finishs -->
<!-- Password start -->
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><img src="images/lock.png" width="33" height="20"/></span>
  </div>
  <input type="password" id="password" class="form-control login-user" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1">
</div>
<!-- Password finish -->
<!-- Register start -->
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><img src="images/register-as.png" width="34" height="20"/></span>
  </div>
  <SELECT id="role" class="form-control login-user" placeholder="Register as" aria-label="Register" aria-describedby="basic-addon1">
 <option value="BROKER">BROKER</option>
  <option value="BUYER">BUYER</option>
  <option value="SELLER">SELLER</option>
</SELECT>
</div>
<!-- Register finish -->
</div>
<div class="col-md-6 col-lg-6 col-xl-5 offset-lg-0 offset-md-0 offset-xl-1">
<!-- Email Address start -->
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><img src="images/envelop.png" width="33" height="19"/></span>
  </div>
  <input type="text" id="email" class="form-control login-user" placeholder="Email Address" aria-label="Username" aria-describedby="basic-addon1">
</div>
<!-- Email Address finish -->
<!-- City start -->
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><img src="images/city.png" width="34" height="21"/></span>
  </div>
  <input type="text" id="city" class="form-control login-user" placeholder="City" aria-label="City" aria-describedby="basic-addon1">
</div>
<!-- City finish -->
<!-- Confirm Password start -->
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><img src="images/co-password.png" width="33" height="22"/></span>
  </div>
  <input type="password" id="confirmPass" class="form-control login-user" placeholder="Confirm Password" aria-label="copassword" aria-describedby="basic-addon1">
</div>
<!-- Confirm Password finish -->
</div>
</div>
<!-- clicking start -->
<p class="by">By clicking on register, you agree to our <a href="login" style="color:#0070cc;">Terms of Service</a> and <a href="" style="color:#0070cc;">Privacy Policy</a>.</p>
<!-- clicking finish -->
<!-- Enter Captcha start -->
<!--
 <input type="text" class="form-control captcha" placeholder="Enter Captcha (Not Case Sensitive)" aria-label="copassword" aria-describedby="basic-addon1"> <img src="images/rechapcha.jpg" style="margin-top:5px;"/>
</div> -->

 <div class="captcha-area">
  <fieldset>
    <input type="text" id="UserCaptchaCode" class="CaptchaTxtField" placeholder='Enter Captcha - Case Sensitive'>
    
    <div class="container">
  <div class="row">
    <div class="col-md-6">
      <div class="div1">
        <!-- content for div 1 -->
      </div>
    </div>
    <div class="col-md-6">
      <div class="div2">
        <!-- content for div 2 -->
      </div>
    </div>
  </div>
</div>
    
    <span id="WrongCaptchaError" class="error"></span>
    <span class='CaptchaWrap'>
      <span id="CaptchaImageCode" class="CaptchaTxtField">
        <canvas id="CapCode" class="capcode" width="300" height="80"></canvas>
         
      </span> 
     <input type="button" class="ReloadBtn" onclick='CreateCaptcha();'>
    </span>
    <!-- <input type="button" class="btnSubmit" onclick="CheckCaptcha();" value="Submit"> -->
  </fieldset>
</div>

<!-- Enter Captcha finish -->
<!-- Register button start -->
<div class="register-button-area"><button type="submit" id="registerBtn" class="btn register-button">Register </button></div>
<!-- Register button finish -->
</form>
<!-- or sign in using start -->
<div class="or"><img src="images/or.png" width="222" style="margin-bottom:10px;" height="11" align="center"/></div>
<!-- or sign in using finish -->
<!-- social area start -->
<div class="row justify-content-md-center">
<!-- Google start -->
<div class="col-md-4 col-lg-4 col-xl-4">
<a href="#"><div class="facebook-register-button"><img src="images/google-plus.png" width="25" title="Google" alt="Google" class="ragister-icon" height="25"/> <span class="ragister-icon">| </span>Sign in with Google</div></a>
</div>
<!-- Google finish -->
<!-- Facebook start -->
<div class="col-md-4 col-lg-4 col-xl-4">
<a href="#"><div class="facebook-register-button"><img src="images/facebook-icon.png" width="25" title="Facebook" alt="Facebook" class="ragister-icon" height="25"/> <span class="ragister-icon">|</span> Sign in with Facebook</div></a>
</div>
<!-- Facebook finish -->
<!-- Linkedin start -->
<div class="col-md-4 col-lg-4 col-xl-4">
<a href="#"><div class="facebook-register-button"><img src="images/linkedin-icon.png" title="Linkdin" alt="Linkdin" width="25" class="ragister-icon" height="25"/> <span class="ragister-icon">|</span> Sign in with Linkedin</div></a>
</div>
<!-- Linkedin finish -->
</div>
<!-- Already have an account? start -->
<p class="an-account">Already have an account? <a href="#" style="color:#0070cc;">Click here</a> to login.</p>
<!-- Already have an account? finish -->
</div>
</div>
</div>
</div>
</section>
<!-- Signup for email alerts area start -->
<section class="padd-30 sign-subscriobe">
<div class="container">
<div class="row justify-content-md-center">
<div class="col-lg-10 col-xl-9 col-md-12">
<div class="row">
<!-- Signup for email alerts title start -->
<div class="col-lg-4 col-md-4 col alerts-email">Signup for email alerts</div>
<!-- Signup for email alerts title finish -->
<div class="col-lg-8 col-md-8 email-alerts">

<div class="input-group">
<!-- Signup for email alerts input start -->
  <input type="search" class="form-control email-alerts-searbox" placeholder="Email Address" aria-label="Search" aria-describedby="search-addon" />
  <!-- Signup for email alerts input finish -->
  <!-- Signup for email alerts button start -->
  <button type="button" class="btn aleart-button">Submit</button>
  <!-- Signup for email alerts button finish -->
</div>

</div>
</div>
</div>
</div>
</div>
</section>
<!-- Signup for email alerts area finish -->

<!-- footer area start -->
<footer class="padd-30">
<div class="container">
<div class="row ">
<!-- footer area 1 start -->
<div class="col-lg-3 col-md-6 mb-30 col-6">
<h2>For Investors</h2>
<ul>
<li><a href="businessForSale">Business For Sale</a></li>
<li><a href="#">Investment Opportunities</a></li>
<li><a href="#">Business Seeking Loan</a></li>
<li><a href="#">Business Assets For Sale</a></li>
<li><a href="franchiseeOpportunitiesGrid">Franchise Opportunities</a></li>
</ul>
</div>
<!-- footer area 1 finish -->
<!-- footer area 2 start -->
<div class="col-lg-3 col-md-6 mb-30 col-6">
<h2>For Businesses</h2>
<ul>
<li><a href="#">Individual Investors</a></li>
<li><a href="#">Business Buyers</a></li>
<li><a href="#">Venture Capital Firms</a></li>
<li><a href="#">Private Equity Firms</a></li>
<li><a href="#">Business Lenders</a></li>
<li><a href="#">Financial Advisors</a></li>
</ul>
</div>
<!-- footer area 2 finish -->
<!-- footer area 3 start -->
<div class="col-lg-4 col-md-6 mb-30 col-6">
<h2>Get Started</h2>
<ul>
<li><a href="createBusinessListings">Sell Your Business</a></li>
<li><a href="#">Invest Or Buy Business</a></li>
<li><a href="#">Business Services</a></li>
<li><a href="#">Business Brokers</a></li>
<li><a href="franchiseeOpportunitiesGrid">Franchisee Opportunities</a></li>
<li><a href="#">Distress Business Sale</a></li>
<li><a href="#">Buy Or Sell Commercial Properties</a></li>
</ul>
</div>
<!-- footer area 3 finish -->
<!-- footer area 4 start -->
<div class="col-lg-2 col-md-6 mb-30 col-6">
<h2>Company</h2>
<ul>
<li><a href="about">About</a></li>
<li><a href="testimonial">Testimonials</a></li>
<li><a href="blog">Blog</a></li>
<li><a href="faq">FAQ</a></li>
<li><a href="contact">Contact</a></li>
<li><a href="#">Terms Of Service</a></li>
<li><a href="#">Privacy Policy</a></li>
</ul>
</div>
<!-- footer area 4 finish -->
</div>
<div class="row">
<!-- we accept area start -->
<div class="col-xl-2 col-md-4 col-lg-3 col-6"><div class="logo"><img src="images/logo.png" title="Tobuz" alt="Tobuz" width="185" /></div></div>
<div class="col-xl-7 col-md-5 col-lg-6 col-6"><div class="visa-area">we accept: <img src="images/visa-icon.jpg" title="we accept" alt="we accept"/></div></div>
<!-- we accept area finish -->
<div class="col-lg-3 col-md-3 col-xl-2">
<!-- footer social area start -->
<div class="social-icon-footer">
<ul>
<li><a href="#"><i class="fa fa-facebook"></i></a></li>
<li><a href="#"><i class="fa fa-twitter"></i></a></li>
<li><a href="#"><i class="fa fa-instagram"></i></a></li>
<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
</ul>
</div>
<!-- footer social area finish -->
</div>
</div>
<!-- footer copyright area start -->
<div class="row copyright"><div class="col-lg-12">Terms Of Service | Privacy Policy © 2022 Designed By FQI</div></div>
<!-- footer copyright area finish -->
</div>
<!-- footer mobile bottom bar area start -->
<div class="fix-mobile-bar">
  <a href="#home"><img src="images/home-icon.png" width="18" height="22"/><br/>Home</a>
  <a href="#news"><img src="images/icon-5.png" width="19" height="22"/><br/>Add listing</a>
  <a href="#contact"><img src="images/search.png" width="16" height="22"/><br/>Search</a>
  <a href="#contact"><img src="images/login-icon.png" width="16" height="22"/><br/>Login</a>
</div>
<!-- footer mobile bottom bar area finish -->
<!-- whatsapp icon area start -->
<a href="https://api.whatsapp.com/send?phone=51955081075&text=Hola%21%20Quisiera%20m%C3%A1s%20informaci%C3%B3n%20sobre%20Varela%202." class="float-icon" target="_blank">
<i class="fa fa-whatsapp my-float"></i>
</a>
<!-- whatsapp icon area finish -->
</footer>
<!-- footer area finish -->
</body>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	
 <!-- <script src="js/jquery.slim.min.js"></script> -->
   <script src="js/popper.min.js"></script>
   <script src="js/bootstrap.bundle.min.js"></script>
  <script src="js/main.js"></script>
</div>

<script type="text/javascript">

$( document ).ready(function() {
	 $(".alert-danger").hide();
	 $(".alert-success").hide();
	 
	  
	 $( "#registerBtn" ).click(function(event) {
	 	
	 	  var name = $("#name").val();
	 	  var email = $("#email").val();
	 	  var phoneNo = $("#phone").val();
	 	  var city = $("#city").val();
	 	  var password = $("#password").val();
	 	  var role = $("#role").val();
	 	  var confirmPass =  $("#confirmPass").val();
	 		if( $.trim(name) == ''){
	 			 $(".alert-danger").show();
	 			 $(".alert-danger").html("<strong>Enter Name </strong> ");
	 			 event.preventDefault();
	 			 return;
	 		}
	 		if( $.trim(email) == ''){
	 			 $(".alert-danger").show();
	 			 $(".alert-danger").html("<strong>Enter Email </strong> ");
	 			 event.preventDefault();
	 			 return;
	 		}
	 		const validateEmail = (email) => {
	 			  return String(email)
	 			    .toLowerCase()
	 			    .match(
	 			      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
	 			    );
	 			};
	 			  if (!validateEmail(email)) {
	 				  $(".alert-danger").show();
	 					 $(".alert-danger").html("<strong>Enter A Valid Email </strong> ");
	 					  window.scrollTo(0, 0);
	 					 event.preventDefault();
	 					 return;
	 			  }
	 			  if( $.trim(phoneNo) == ''){
	 					 $(".alert-danger").show();
	 					 $(".alert-danger").html("<strong>Enter Phone No </strong> ");
	 					   window.scrollTo(0, 0);
	 					 event.preventDefault();
	 					 return;
	 				}
	 			  if( $.trim(city) == ''){
	 					 $(".alert-danger").show();
	 					 $(".alert-danger").html("<strong>Enter City </strong> ");
	 					   window.scrollTo(0, 0);
	 					 event.preventDefault();
	 					 return;
	 				}
	 			  if( $.trim(password) == ''){
	 					 $(".alert-danger").show();
	 					 $(".alert-danger").html("<strong>Enter Password </strong> ");
	 					  window.scrollTo(0, 0);
	 					 event.preventDefault();
	 					 return;
	 				}
	 			  
	 			  if( $.trim(confirmPass) == ''){
	 					 $(".alert-danger").show();
	 					 $(".alert-danger").html("<strong>Enter Confirm Password </strong> ");
	 					  window.scrollTo(0, 0);
	 					 event.preventDefault();
	 					 return;
	 				}
	 			  
	 			  if( $.trim(confirmPass) != $.trim(password)){
	 					 $(".alert-danger").show();
	 					 $(".alert-danger").html("<strong>Password and Confirm Password do not match</strong> ");
	 					   window.scrollTo(0, 0);
	 					 event.preventDefault();
	 					 return;
	 				}
	 			  
	 	 var captcha =  CheckCaptcha ();
	 	
	 	  if(!captcha){
				
				 event.preventDefault();
				 return;
			}
	 	  var getUrl = window.location;
	 	  var url = getUrl .protocol + "//" + getUrl.host + "/registerUser" ;
	 	/*   alert("url :"+url);
	 	  $.ajax({
	 	        url: url,
	 	        type: 'POST',
	 	        data: { "name": name, "email" : email,"phoneNo":phoneNo , "city":city , "password":password , "role":role } ,
	 	        contentType: 'application/json; charset=utf-8',
	 	        success: function (response) {
	 	            alert(response.status);
	 	        },
	 	        error: function () {
	 	            alert("error");
	 	        }
	 	    });  */
	 	
	 	  var  data =  { "name": name, "email" : email,"phoneNo":phoneNo , "city":city , "password":password , "role":role };
	 	  
	 	  $.ajax({
	 	        type: "POST",
	 	        contentType: "application/json",
	 	        url: url,
	 	        async: false,
	 	        data: JSON.stringify(data),
	 	        dataType: 'json',
	 	        cache: false,
	 	        timeout: 600000,
	 	        success: function (data) {
	 		
	 				 $(".alert-danger").hide();
	 	        	$(".alert-success").show();
	 				 $(".alert-success").html("<strong>Registered Successfully </strong> ");
	 				  window.scrollTo(0, 0);
	 				 event.preventDefault();
	 				 return;
	 	        },
	 	        error: function (e) {
	 	        	
	 	        	$(".alert-success").hide();
	 	        	 $(".alert-danger").show();
	 				 $(".alert-danger").html("<strong>Error in Register</strong> ");
	 				  window.scrollTo(0, 0);
	 				 event.preventDefault();
	 				 return;

	 	        }
	 	    });
	 	  
	 	 event.preventDefault();
	 	});
	 
	 
	 
	 
	  new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
	   $( "#google_translate_element" ).find( "select" ).addClass("form-select form-select-sm");
	   $( "#google_translate_element" ).attr("aria-label",".form-select-sm example");
	   
	   new google.translate.TranslateElement({pageLanguage: 'en'}, ' google_translate_element_mob');
	   $( "#google_translate_element_mob" ).find( "select" ).addClass("form-select form-select-sm");
	   $( "#google_translate_element_mob" ).attr("aria-label",".form-select-sm example"); 
	  
	
	 
	});


	
	
	

var cd;

$(function(){
  CreateCaptcha();
});

// Create Captcha
function CreateCaptcha() {
  //$('#InvalidCapthcaError').hide();
  var alpha = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
                    
  var i;
  for (i = 0; i < 6; i++) {
    var a = alpha[Math.floor(Math.random() * alpha.length)];
    var b = alpha[Math.floor(Math.random() * alpha.length)];
    var c = alpha[Math.floor(Math.random() * alpha.length)];
    var d = alpha[Math.floor(Math.random() * alpha.length)];
    var e = alpha[Math.floor(Math.random() * alpha.length)];
    var f = alpha[Math.floor(Math.random() * alpha.length)];
  }
  cd = a + ' ' + b + ' ' + c + ' ' + d + ' ' + e + ' ' + f;
  $('#CaptchaImageCode').empty().append('<canvas id="CapCode" class="capcode" width="300" height="80"></canvas>')
  
  var c = document.getElementById("CapCode"),
      ctx=c.getContext("2d"),
      x = c.width / 2,
      img = new Image();

  img.src = "https://pixelsharing.files.wordpress.com/2010/11/salvage-tileable-and-seamless-pattern.jpg";
  img.onload = function () {
      var pattern = ctx.createPattern(img, "repeat");
      ctx.fillStyle = pattern;
      ctx.fillRect(0, 0, c.width, c.height);
      ctx.font="46px Roboto Slab";
      ctx.fillStyle = '#ccc';
      ctx.textAlign = 'center';
      ctx.setTransform (1, -0.12, 0, 1, 0, 15);
      ctx.fillText(cd,x,55);
  };
  
  
}

// Validate Captcha
function ValidateCaptcha() {
  var string1 = removeSpaces(cd);
  var string2 = removeSpaces($('#UserCaptchaCode').val());
  if (string1 == string2) {
    return true;
  }
  else {
    return false;
  }
}

// Remove Spaces
function removeSpaces(string) {
  return string.split(' ').join('');
}

// Check Captcha
function CheckCaptcha() {
  var result = ValidateCaptcha();
  if( $("#UserCaptchaCode").val() == "" || $("#UserCaptchaCode").val() == null || $("#UserCaptchaCode").val() == "undefined") {
    $('#WrongCaptchaError').text('Please enter code given below in a picture.').show();
    $('#UserCaptchaCode').focus();
  } else {
    if(result == false) { 
      $('#WrongCaptchaError').text('Invalid Captcha! Please try again.').show();
      CreateCaptcha();
      $('#UserCaptchaCode').focus().select();
      return false;
    }
    else { 
      $('#UserCaptchaCode').val('').attr('place-holder','Enter Captcha - Case Sensitive');
      CreateCaptcha();
      $('#WrongCaptchaError').fadeOut(100);
      $('#SuccessMessage').fadeIn(500).css('display','block').delay(5000).fadeOut(250);
      return true;
    }
  }  
}
	
	
	function addListings(){
	 var getUrl = window.location;
	 var url = getUrl .protocol + "//" + getUrl.host + "/createBusinessListings" ;
	
		window.location.href =url;
		}
	
	
	
	
	
	
</script>
</html>
