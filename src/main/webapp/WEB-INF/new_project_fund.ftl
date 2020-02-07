<html>
<head><title>ProjectFunder</title></head>
<link rel="stylesheet" type="text/css" href="/css/new_project.css">
<body>
<div id="wrapper">
		<div id="header">
			<h1>${project.getTitel()}</h1>
		</div>

		<div id="new_project_fund_divid">
			<div id="ul_divid">
				<form method="post">
			<ul> 
			<input type="hidden" id="referer" name="kennung" value=${project.getKennung()}>
			<li>
  				<h2>Spendenbetrag(€):</h2> <input type="number" name="donation"><br>
			</li>
            <li>
                <h3>Anonym spenden? <input type="checkbox" id="finance_limit_id"></h3>
            </li>
                <input style="padding:1em 1em 1em 1em; color:white; background-color:red;" type="submit" value="Spenden">
			<li>
			
			</ul>	
			</form>
			</div>
		</div>
</div>
</body>
</html>