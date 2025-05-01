$(document).ready(function() {
	const category ="";

////console.log("ready on new version")
	$("#Home").button();
	$( "#Home" ).button( "option", "icon", "ui-icon-check" );
	$( "#Home" ).button( "option", "showIcon", "true" );
	$( "#Home" ).button( "option", "label", "Home" );

	$("#SignUp").button();
	$( "#SignUp" ).button( "option", "icon", "ui-icon-check" );
	$( "#SignUp" ).button( "option", "showIcon", "true" );
	$( "#SignUp" ).button( "option", "label", "SignUp" );

	$("#SignIn").button();
	$( "#SignIn" ).button( "option", "icon", "ui-icon-check" );
	$( "#SignIn" ).button( "option", "showIcon", "true" );
	$( "#SignIn" ).button( "option", "label", "SignIn" );

	$("#user-dash").button();
	$( "#user-dash" ).button( "option", "icon", "ui-icon-check" );
	$( "#user-dash" ).button( "option", "showIcon", "true" );
	$( "#user-dash" ).button( "option", "label", "User Dash" );


	$("#Add").button();
	$( "#Add" ).button( "option", "icon", "ui-icon-check" );
	$( "#Add" ).button( "option", "showIcon", "true" );
	$( "#Add" ).button( "option", "label", "upload Events" );

	$("#calendar").button();
	$( "#calendar" ).button( "option", "icon", "ui-icon-check" );
	$( "#calendar" ).button( "option", "showIcon", "true" );
	$( "#calendar" ).button( "option", "label", "Manage Events (Calendar View)" );

	$("#list").button();
	$( "#list" ).button( "option", "icon", "ui-icon-check" );
	$( "#list" ).button( "option", "showIcon", "true" );
	$( "#list" ).button( "option", "label", "Manage Events (List View)" );



	$("#suggestions").button();
	$( "#suggestions" ).button( "option", "icon", "ui-icon-check" );
	$( "#suggestions" ).button( "option", "showIcon", "true" );
	$( "#suggestions" ).button( "option", "label", "Suggestion Box" );






})

function hiderow(row){
	//console.log(row);
	var rowtohide = document.getElementById(row);
	//rowtohide.setAttribute('display','none')
	$(rowtohide).slideUp();

}

function hidecol(col_no) {
	//console.log(col_no);
	var tbl = document.getElementById('moneyTable');
	var rows = tbl.getElementsByTagName('tr');

	for (var row = 0; row < rows.length; row++) {
		var cols = rows[row].children;
		var coll = cols[col_no];
		$(coll).slideUp();


	}
}
	function restorecells() {
		var tbl = document.getElementById('moneyTable');
		var rows = tbl.getElementsByTagName('tr');
		for (var row = 0; row < rows.length; row++) {
			$(rows[row]).slideDown();
			var cols = rows[row].children;
			for (var i =0; i<cols.length; i++){
				var cell = cols[i];
				$(cell).slideDown();
			}
		}


}



