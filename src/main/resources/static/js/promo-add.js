// Submit form to controller
$("#form-add-promo").submit(function(evt) {
	//Block default submit behavior (Auto Refresh)
	evt.preventDefault();
	
	let promo = {};
	promo.title = $("#title").val();
	promo.promotionLink = $("#promotionLink").val();
	promo.site = $("#site").text();
	promo.description = $("#description").val();
	promo.imageLink = $("#imageLink").attr("src");
	promo.price = $("#price").val();
	promo.category = $("#category").val();
	
	console.log('promo > ', promo);
	
	$.ajax({
		method: "POST",
		url: "/promotion/save",
		data: promo,
		beforeSend: function() {
			//Removing the messages and alert borders
			$("span").closest('.error-span').remove();
			$("#title").removeClass("is-invalid");
			$("#promotionLink").removeClass("is-invalid");
			$("#price").removeClass("is-invalid");
			$("#category").removeClass("is-invalid");
			
			//Enable loading
			$("#form-add-promo").hide();
			$("#loader-form").addClass("loader").show();
		},
		success: function() {
			$("#form-add-promo").each(function() {
				this.reset();
			});
			$("#imageLink").attr("src", "/images/promo-dark.png");
			$("#site").text("");
			$("#alert")
				.removeClass("alert alert-danger")
				.addClass("alert alert-success")
				.text("OK! Promotion registered successfully.");		
		},
		statusCode: {
			422: function(xhr) {
				console.log('status error:', xhr.status);
				let errors = $.parseJSON(xhr.responseText);
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
$("#promotionLink").on('change', function() {

	let url = $(this).val();
	
	if (url.length > 7) {
		
		$.ajax({
			method:"POST",
			url: "/meta/info?url=" + url,
			cache: false,
			beforeSend: function() {
				$("#alert").removeClass("alert alert-danger alert-success").text('');
				$("#title").val("");
				$("#site").text("");
				$("#imageLink").attr("src", "");
				$("#loader-img").addClass("loader");
			},
			success: function( data ) {
				console.log(data);
				$("#title").val(data.title);
				$("#site").text(data.site.replace("@", ""));
				$("#imageLink").attr("src", data.image);
			},
			statusCode: {
				404: function() {
					$("#alert").addClass("alert alert-danger").text("No information can be retrieved from that URL.");
					$("#imageLink").attr("src", "/images/promo-dark.png");
				}
			},
			error: function() {
				$("#alert").addClass("alert alert-danger").text("Oops... something went wrong, try again later.");
				$("#imageLink").attr("src", "/images/promo-dark.png");
			},
			complete: function() {
				$("#loader-img").removeClass("loader");
			}
		});
	}
});
