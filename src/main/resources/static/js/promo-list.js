// Infinite scroll effect
let pageNumber = 0;

$(document).ready(function(){
	$("#loader-img").hide();
	$("#fim-btn").hide();
});

$(window).scroll(function() {
	
	let scrollTop = $(this).scrollTop();
	let content = $(document).height() - $(window).height();
	
	//quando a pagina está zoon, o scrip não consegue calcular com precisão
	 //o tamanho do scroll - isso gera um bug no código
	if (scrollTop >= content) {
		pageNumber++;
		setTimeout(function(){
			loadByScrollBar(pageNumber);
		}, 200);
	}
});

function loadByScrollBar(pageNumber) {

	let site = $("#autocomplete-input").val();
	
	$.ajax({
		method: "GET",
		url: "/promotion/list/ajax",
		data: {
			page: pageNumber,
			site: site
		},
		beforeSend: function() {
			$("#loader-img").show();
		},
		success: function(response) {			
			if (response.length > 150) {
			
				$(".row").fadeIn(250, function() {
					$(this).append(response);
				});
			
			} else {
				$("#fim-btn").show();
				$("#loader-img").removeClass("loader");
			}
		},
		error: function(xhr) {
			alert("Oops... an error has occurred: " + xhr.status + " - " + xhr.status.Text);
		},
		complete: function() {
			$("#loader-img").hide();
		}
	})  
}

// Autocomplete search bar
$("#autocomplete-input").autocomplete({
	source: function(request, response) {
		$.ajax({
			method: "GET",
			url: "/promotion/site",
			data: {
				term: request.term
			},
			success: function(result) {
				response(result);
			}
		});
	}
});

$("#autocomplete-submit").on("click", function() {
	let site = $("#autocomplete-input").val();
	$.ajax({
		method: "GET",
		url: "/promotion/site/list",
		data: {
			site : site
		},
		beforeSend: function() {
			pageNumber = 0;
			$("#fim-btn").hide();
			$(".row").fadeOut(400, function(){
				$(this).empty();
			});
		},
		success: function(response) {
			$(".row").fadeIn(250, function(){
				$(this).append(response);
			});
		},
		error: function(xhr) {
			alert("Oops... an error has occurred: " + xhr.status + " - " + xhr.status.Text);
		}
	});
});

// Add likes
$(document).on("click", "button[id*='likes-btn-']", function() {
	let id = $(this).attr("id").split("-")[2];
	
	$.ajax({
		method: "POST",
		url: "/promotion/like/" + id,
		success: function(response) {
			$("#likes-count-" + id).text(response);	
		},
		error: function(xhr) {
			alert("Oops... an error has occurred: " + xhr.status + " - " + xhr.status.Text);
		}
	});
});
