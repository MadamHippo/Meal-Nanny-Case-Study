// js file for upload validation and to display logo as thumbnail. Images are really tricky so its separated out

$(document).ready(function() {
	$("#buttonCancel").on("click", function() {
		window.location = moduleURL;
	});

	$("#fileImage").change(function() {
		fileSize = this.files[0].size;

		// Max size set
		if (fileSize > 802400) {
			this.setCustomValidity("You must choose an image less than 800KB!");
			this.reportValidity();
		} else {
			this.setCustomValidity("");
			showImageThumbnail(this);
		}

	});
});

function showImageThumbnail(fileInput) {
	var file = fileInput.files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
		$("#thumbnail").attr("src", e.target.result);
	};

	reader.readAsDataURL(file);
}