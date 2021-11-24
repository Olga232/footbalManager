<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Photoshoot 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Football Manager</title>
<style type="text/css">
   TABLE {
    color: #C0C0C0;
   }
</style>
<link href="static/css/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="static/scripts/jquery-3.6.0.js"></script> 
<script type="text/javascript" src="static/scripts/jquery.poptrox-0.1.js"></script>
</head>
<body>
<div id="header" class="container">
	<div id="logo">
		<h1>Football Manager</h1>
	</div>
	<div id="menu">
		<ul>
			<li><a href="team">All teams</a></li>
			<li><a href="player">All players</a></li>
		</ul>
	</div>
</div>

<div id="poptrox">
	<!-- start -->
	<ul id="gallery">
		<li><img src="static/images/img1.jpg" alt="" /></li>
		<li><img src="static/images/img3.jpg" alt="" /></li>
		<li><img src="static/images/img4.jpg" alt="" /></li>
		<li><img src="static/images/img2.jpg" alt="" /></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<br class="clear" />
	<script type="text/javascript">
		$('#gallery').poptrox();
		</script>

</div>
<div id="page">
	<div id="bg1">
		<div id="bg2">
			<div id="bg3">
				<div id="content">
