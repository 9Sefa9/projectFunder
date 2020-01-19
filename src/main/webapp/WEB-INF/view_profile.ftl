<html>
<head><title>ProjectFunder</title>
<link rel="stylesheet" type="text/css" href="/css/view_profile.css">
<body>
	<div id="wrapper">
		<div id="header">
			<h1> Project Funder</h1>
		</div>

		<div id="view_profile_divid">
			<h2>Profil von: </h2>
            <p>Benutzername: </p>
            <p>Anzahl erstellter Projekte: </p>
            <p>Anzahl unterstützter Projekte: </p>
		<div id="created_projects_divid">
			<h2> Erstellte Projekte</h2>
			<ul id="created_projects_base_ul_id">
		
				<!--
				1 einzelnes <li> erzeugt ein Projekt mit jeweiligen Eigenschaften. Eventuell zur Laufzeit generieren.
				Vorlage: siehe unten:
				-->
				
				<li id= "created_projects_li_id">
					<div id="created_projects_list_divid">
					<a href="https://google.de/">Projekt Titel</a>
					<br><img src="TO BE FILLED" alt="Projekt Bild bitte füllen."/>
					<p>Aktuell: X €</p>
					<p>Status: geschlossen</p>
					</div>
				</li>
		
			</ul>
		</div>
			
		<div id="supported_projects_div">
			<h2> Unterstützte Projekte</h2>
			<ul id="supported_projects_base_ul_id">
		
				<!--
				1 einzelnes <li> erzeugt ein Projekt mit jeweiligen Eigenschaften. Eventuell zur Laufzeit generieren.
				Vorlage: siehe unten:
				-->
				
				<li id= "supported_projects_li_id">
					<div id="supported_projects_list_divid">
					<a href="https://google.de/">Projekt Name</a>
					<img src="TO BE FILLED" alt="Projekt Bild bitte füllen."/>
					<p>Limit: Max</p>
					<p>Status: offen</p>
					<p>Gespendet: X €</p>
                    </div>
				</li>
				
			</ul>
		</div>
		</div>
	</div>
</body>
</html>
