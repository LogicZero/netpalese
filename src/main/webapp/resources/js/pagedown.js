/**
 * 
 */

function setupPagedown () {
    var converter2 = new Markdown.Converter();

    converter2.hooks.chain("preConversion", function (text) {
        return text.replace(/\b(a\w*)/gi, "*$1*");
    });

    converter2.hooks.chain("plainLinkText", function (url) {
        return "This is a link to " + url.replace(/^https?:\/\//, "");
    });
    
    var help = function () { 
    	alert("Do you need help?"); 
    };
    
    var options = {
        helpButton: { handler: help },
        strings: { quoteexample: "whatever you're quoting, put it right here" }
    };
    var editor2 = new Markdown.Editor(converter2, null, options);
    
    editor2.run();
}

$(document).ready(function(){
	setupPagedown();
});