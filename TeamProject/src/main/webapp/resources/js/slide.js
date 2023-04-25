	$(document).ready(function(){
	
	const slide_banner = setInterval(slidebanner, 4000);
	
	 function slidebanner(){
			$(".slide").animate({left: "-100%"}, 1500, function () {
				$(".slide").append($(".slide li:first-child"));
				$(".slide").css("left", 0);
				})
		}
});
	