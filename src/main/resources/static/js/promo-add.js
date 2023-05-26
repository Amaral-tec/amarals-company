// Submit form to controller
$("#form-add-promo").submit(function(evt) {
	//Block default submit behavior (Auto Refresh)
	evt.preventDefault();
	
	var promo = {};
	promo.titulo = $("#titulo").val();
	promo.linkPromocao = $("#linkPromocao").val();
	promo.site = $("#site").text();
	promo.descricao = $("#descricao").val();
	promo.linkImagem = $("#linkImagem").attr("src");
	promo.preco = $("#preco").val();
	promo.category = $("#category").val();
	
	console.log('promo > ', promo);
	
	$.ajax({
		method: "POST",
		url: "/promocao/save",
		data: promo,
		beforeSend: function() {
			//Removing the messages and alert borders
			$("span").closest('.error-span').remove();
			$("#titulo").removeClass("is-invalid");
			$("#linkPromocao").removeClass("is-invalid");
			$("#preco").removeClass("is-invalid");
			$("#category").removeClass("is-invalid");
			
			//Enable loading
			$("#form-add-promo").hide();
			$("#loader-form").addClass("loader").show();
		},
		success: function() {
			$("#form-add-promo").each(function() {
				this.reset();
			});
			$("#linkImagem").attr("src", "/images/promo-dark.png");
			$("#site").text("");
			$("#alert")
				.removeClass("alert alert-danger")
				.addClass("alert alert-success")
				.text("OK! Promotion registered successfully.");		
		},
		statusCode: {
			422: function(xhr) {
				console.log('status error:', xhr.status);
				var errors = $.parseJSON(xhr.responseText);
				$.each(errors, function(key, val){
					$("#" + key).addClass("is-invalid");
					$("#error-" + key)
						.addClass("invalid-feedback")
						.append("<span class='error-span'>" + val + "</span>")
				});
			}
		},
		
		error: function(xhr) {
			console.log("> error: ", xhr.responseText);
			$("#alert").addClass("alert alert-danger").text("Unable to register for this promotion.");
		},
		complete: function() {
			$("#loader-form").fadeOut(800, function() {
				$("#form-add-promo").fadeIn(250);
				$("#loader-form").removeClass("loader");
			});
		}
	});
});

// Function to capture meta tags
$("#linkPromocao").on('change', function() {

	var url = $(this).val();
	
	if (url.length > 7) {
		
		$.ajax({
			method:"POST",
			url: "/meta/info?url=" + url,
			cache: false,
			beforeSend: function() {
				$("#alert").removeClass("alert alert-danger alert-success").text('');
				$("#titulo").val("");
				$("#site").text("");
				$("#linkImagem").attr("src", "");
				$("#loader-img").addClass("loader");
			},
			success: function( data ) {
				console.log(data);
				$("#titulo").val(data.title);
				$("#site").text(data.site.replace("@", ""));
				$("#linkImagem").attr("src", data.image);
			},
			statusCode: {
				404: function() {
					$("#alert").addClass("alert alert-danger").text("Nenhuma informação pode ser recuperada dessa url.");
					$("#linkImagem").attr("src", "/images/promo-dark.png");
				}
			},
			error: function() {
				$("#alert").addClass("alert alert-danger").text("Ops... algo deu errado, tente mais tarde.");
				$("#linkImagem").attr("src", "/images/promo-dark.png");
			},
			complete: function() {
				$("#loader-img").removeClass("loader");
			}
		});
	}
});