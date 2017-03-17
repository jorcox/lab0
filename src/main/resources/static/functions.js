$(document).ready(function() {
	registerSearch();
});

function registerSearch() {
	$("#search").submit(function(ev){
		event.preventDefault();
		$.getJSON($(this).attr("action"), {q: $("#q").val()}, function(data) {

			var template = '{{#tweets}}'
			+'<div class="row panel panel-default">'
			+	    '<div class="panel-heading">'
			+	        '<a href="https://twitter.com/{{fromUser}}"'
			+	           'target="_blank"><b>@{{fromUser}}</b></a>'
			+	        '<div class="pull-right">'
			+	            '<a href="https://twitter.com/{{fromUser}}/status/{{idStr}}'
			+	               'target="_blank"><span class="glyphicon glyphicon-link"></span></a>'
			+	        '</div>'
			+	    '</div>'
			+	    '<div class="panel-body">{{unmodifiedText}}</div>'
			+'</div>'
			+'{{/tweets}}'

			var html = Mustache.to_html(template, data);

			$("#resultsBlock").empty().append(html);
			
		});	
	});
}

