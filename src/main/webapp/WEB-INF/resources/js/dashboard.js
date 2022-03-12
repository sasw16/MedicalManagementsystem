document.addEventListener("DOMContentLoaded", function (event) {

    $('#table-paged').DataTable();
    
        $.each($('.slot'), function (key, value) {
	        $(this).click(function (e) {
	            $('.btn-success')
	                .removeClass('btn-success')
	                .addClass('btn-outline-success');
	
	            $(this)
	                .removeClass('btn-outline-success')
	                .addClass('btn-success');
	        });
    	});
    	
	$.fn.stars = function() {
	    return this.each(function(i,e){$(e).html($('<span/>').width($(e).text()*16));});
	};
	
	
	
	$('.stars').stars();

    const showNavbar = (toggleId, navId, bodyId, headerId) => {
        const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId),
            headerpd = document.getElementById(headerId)


        // show navbar
        nav.classList.toggle('show1')
        // change icon
        toggle.classList.toggle('bxs-chevrons-left')
        // add padding to body
        bodypd.classList.toggle('body-pd')
        // add padding to header
        headerpd.classList.toggle('body-pd')

        // Validate that all variables exist
        if (toggle && nav && bodypd && headerpd) {
            toggle.addEventListener('click', () => {
                // show navbar
                nav.classList.toggle('show1')
                // change icon
                toggle.classList.toggle('bxs-chevrons-left')
                // add padding to body
                bodypd.classList.toggle('body-pd')
                // add padding to header
                headerpd.classList.toggle('body-pd')
            })
        }
    }

    showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header')

    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink() {
        if (linkColor) {
            linkColor.forEach(l => l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l => l.addEventListener('click', colorLink))

    // Your code to run since DOM is loaded and ready
});  

                                    