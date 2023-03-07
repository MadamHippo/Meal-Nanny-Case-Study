// logout functionality refactored for better organization
    $(document).ready(function() {
        $("#logoutLink").on("click", function(e) {
            e.preventDefault();
            document.logoutForm.submit();
        });
    });