function search()
{
	var searchParam = document.forms["searchForm"]["searchInput"].value;
	if (searchParam==null || searchParam==""){
		alert("Please enter a parameter!");
		return false;
	}
	//alert("You Searched for: " + searchParam);
	showResults(searchParam);
}
function showResults(searched){
	 alert(searched);
	 $.ajax({  
		  url: "results.jsp",   
		  success: function() {  
			  $('#searched-item').append(searched);
			  
		  }  
		});  
	
}