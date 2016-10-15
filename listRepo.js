var request = require('request');
var fs = require("fs");
var Promise = require('bluebird');
var parse = require('parse-link-header');


////// FILL IN THE BLANKS

var token = "token " + "";
var userId = "BS-Mock";
var repoName = "createIssues";

var urlRoot = "https://github.ncsu.edu/api/v3";
// NCSU Enterprise endpoint:
// https://github.ncsu.edu/api/v3


createIssues(userId,repoName);



function createIssues(userName, repo)
{
	var options = {
		url: urlRoot + '/orgs/' + userId + '/repos', 
		method: 'GET',
		headers:
		{
			"User-Agent": "EnableIssues",
			"content-type": "application/json",
			"Authorization": token
			
		}
			
	};

	// Send a http request to url and specify a callback that will be called upon its return.
	request(options, function (error, response, body) 
	{
		var obj = JSON.parse(body);
		//console.log( obj );
		for( var i = 0; i < obj.length; i++ )
		{
			var name = obj[i].name;
			console.log( name );
		}
	});	
}
