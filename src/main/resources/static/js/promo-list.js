let pageNumber = 0;

$(document).ready(function(){
	$("#loader-img").hide();
	$("#fim-btn").hide();
});

// Infinite scroll effect
$(window).scroll(function() {
	
	let scrollTop = $(this).scrollTop();
	 content = $(document).height() - $(window).height();
	
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
	
	$.ajax({
		method: "GET",
		url: "/promotion/list/ajax",
		data: {
			page: pageNumber
		},
		beforeSend: function() {
			$("#loader-img").show();
		},
		success: function( response ) {			
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