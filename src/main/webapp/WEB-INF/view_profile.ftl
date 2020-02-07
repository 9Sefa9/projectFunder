<html>
<head><title>ProjectFunder</title>
<link rel="stylesheet" type="text/css" href="/css/view_profile.css">
<body>
	<div id="wrapper">
		<div id="header">
			<h1> Project Funder</h1>
		</div>
		
						<a href="new_project?user=${user.getEmail()}">Projekt Erstellen</a>
		<div id="view_profile_divid">
			<h2>Profil von: ${user.getFirstname()} </h2>
            <p>Benutzername:${user.getEmail() }</p>
            <p>Anzahl erstellter Projekte: ${user.getProjectsCount() }</p>
            <p>Anzahl unterstützter Projekte: ${user.getSupportedCount() }</p>
		<div id="created_projects_divid">
			<h2> Erstellte Projekte</h2>
			<ul id="created_projects_base_ul_id">
		
				<!--
				1 einzelnes <li> erzeugt ein Projekt mit jeweiligen Eigenschaften. Eventuell zur Laufzeit generieren.
				Vorlage: siehe unten:
				-->
				<#list projects as project>
				
				<li id= "created_projects_li_id">
				
					<div id="created_projects_list_divid">
					<a href="https://google.de/">${project.getTitel()}</a>
					<br><img src="TO BE FILLED" alt="Projekt Bild bitte füllen."/>
					<p>Aktuell: ${project.getSpendenbetrag()} €</p>
					<p>Limit: ${project.getFinanzierungsLimit()}</p>
					<p>Status: ${project.getStatus()}</p>
					</div>
				</li>
				</#list>
		
			</ul>
		</div>
			
		<div id="supported_projects_div">
			<h2> Unterstützte Projekte</h2>
			<ul id="supported_projects_base_ul_id">
		
				<!--
				1 einzelnes <li> erzeugt ein Projekt mit jeweiligen Eigenschaften. Eventuell zur Laufzeit generieren.
				Vorlage: siehe unten:
				-->supported
				
				<#list supported as support>
				<li id= "supported_projects_li_id">
					<div id="supported_projects_list_divid">
					<a href="https://google.de/">${support.getTitel()}</a>
					<img src="TO BE FILLED" alt="Projekt Bild bitte füllen."/>
					<p>Limit: ${support.getFinanzierungsLimit()}</p>
					<p>Status:${support.getStatus()}</p>
					<p>Gespendet: ${support.getSpendenbetrag()} €</p>
                    </div>
				</li>
				</#list>
				
			</ul>
		</div>
		</div>
	</div>
</body>
</html>
