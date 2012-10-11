/**
 * 
 * Juni 2012
 * Javascript für das DatenbankLayout 
 * Author:Janine und Atilla
 */

$(document).ready(
		function() {
			$(".angeDropDown .ui-icon-triangle-1-s").remove();
			$(".angeDropDown button .ui-button-text").remove();
			$(".angeDropDown button").append(
					"<span class=\"ui-button-text\">▼</span>");

			$(".produktTabview .ui-tabs-panels").remove();
			$(".produktHeader .ui-panel-content").remove();
			$(".angeDlg").load(
					function() {
						$(".angeDropDown .ui-icon-triangle-1-s").remove();
						$(".angeDropDown button .ui-button-text").remove();
						$(".angeDropDown button").append(
								"<span class=\"ui-button-text\">▼</span>");
					});
		});



function cleanLayout() {
	$(".produktHeader .ui-panel-content").remove();
	$(".produktTabview .ui-tabs-panels").remove();
}

function tabDone() {

	$(".produktTabview .ui-tabs-nav .ui-state-active").addClass("tabDone");
}
