<html>
<head><title>ProjectFunder</title>
<link rel="stylesheet" type="text/css" href="/css/view_main.css">
<body>
	<div id="wrapper">
		<div id="header">
			<h1> Project Funder</h1>
		</div>
	
		<div id="view_main_divid">
			<p>
				<!--Die Datenbank funder ist ${db2exists}-->
				<div id="btn_profil_divid">
					<button id="btn_profil_id">Mein Profil</button>
				</div>
			</p>
		

		<div id="open_projects_divid">
			<h2> Offene Projekte</h2>
			<ul id="open_projects_base_ul_id">
		
				<!--
				1 einzelnes <li> erzeugt ein Projekt mit jeweiligen Eigenschaften. Eventuell zur Laufzeit generieren.
				Vorlage: siehe unten:
				-->
				<#list projects as project>
				<li id= "open_projects_li_id">
					<div id="open_projects_list_divid">
					<p>${project.getTitel()}</p>
					<p>Erstellt von: ${project.getErsteller()}</p>
					<p>Aktueller Betrag: ${project.getSpendenbetrag()} Euro</p>
					</div>
				</li>
				</#list>
			</ul>
		</div>
			
		<div id="occupied_projects_div">
			<h2> Abgeschlossene Projekte</h2>
			<ul id="occupied_projects_base_ul_id">
		
				<!--
				1 einzelnes <li> erzeugt ein Projekt mit jeweiligen Eigenschaften. Eventuell zur Laufzeit generieren.
				Vorlage: siehe unten:
				-->
				<#list fprojects as fproject>
				<li id= "occupied_projects_li_id">
					<div id="occupied_projects_list_divid">
					<p>Projekt Name</p>
					<img src="TO BE FILLED" alt="Projekt Bild bitte füllen."/>
					<p>Erstellt von: Max</p>
					<p>Aktueller Betrag: 0 Euro</p>
					</div>
				</li>
				</#list>
			</ul>
		</div>
		</div>
	</div>
</body>
</html>
