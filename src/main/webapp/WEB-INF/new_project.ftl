<html>
<head><title>ProjectFunder</title></head>
<link rel="stylesheet" type="text/css" href="/css/new_project.css">
<body>
<div id="wrapper">
		<div id="header">
			<h1> Neues Projekt erstellen</h1>
		</div>

		<div id="new_project_divid">
			<div id="ul_divid">
				<form method="post">
			<ul>
			<li>
  				<h2>Titel:</h2> <input type="text" id="input_title_name_id" name="title"><br>
			</li>
			<li>
			<h3>Finanzierungslimit:</h3> <input type="number" id="finance_limit_id" name="financeLimit"><br>
			</li>
			<li>
			<!--<input type="submit" value="Submit">-->
				<h2>Kategorie:</h2> 
				Health & Wellness <input type="radio" id="input_category_healtWellness_id" name="healthWellness"><br>
			</li>
			<li>
			Art & Creative Works <input type="radio" id="input_category_artCreativeWorks_id" name="artCreativeWork"><br>
				
			</li>
			<li>
			Education <input type="radio" id="input_category_education_id" name="education"><br>
			</li>
			<li>
			Tech & Innovation <input type="radio" id="input_category_techInnovation_id" name="techInnovation"><br>
			</li>
			<li>
			<h2>Vorgänger:</h2> 
				Ubuntu Touch <input type="radio" id="input_category_ubuntuTouch_id" name="UbuntuTouch"><br>
			</li>
			<li>
				Ubuntu Touch Pro <input type="radio" id="input_category_ubuntuTouchPro_id" name="UbuntuTouchPro"><br>
			</li>
			<li>
				Kein Vorgänger <input type="radio" id="input_category_noPredecessor_id" name="NoPredecessor"><br>
			</li>
			<li>
				<h2>Beschreibung:</h2> <textarea name="description"></textarea>
			</li>
			<li>
				<input style="padding:1em 1em 1em 1em;" type="submit" value="Erstellen" name="Create">
			</li>
			</ul>	
			</form>
			</div>
		</div>
</div>
</body>
</html>