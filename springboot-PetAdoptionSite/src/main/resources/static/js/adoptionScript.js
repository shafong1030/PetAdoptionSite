//!!!For adoption page!!!//
    /*definition of elements in the form*/
			// Get all checkbox elements
	const checkboxes = document.querySelectorAll('input[type="checkbox"]');

	// Add event listener to each checkbox
	checkboxes.forEach((checkbox) => {
	  checkbox.addEventListener('change', (event) => {
	    // Get the parent element of the checkbox
	    const parent = event.target.parentNode;

	    // Check if the parent element has a subgroup
	    if (parent.querySelector('.checkbox-subgroup')) {
	      // Get all checkboxes in the subgroup
	      const subgroupCheckboxes = parent.querySelectorAll('.checkbox-subgroup input[type="checkbox"]');

	      // If the parent checkbox is checked, check all checkboxes in the subgroup
	      if (event.target.checked) {
	        subgroupCheckboxes.forEach((subgroupCheckbox) => {
	          subgroupCheckbox.checked = true;
	        });
	      } else {
	        // If the parent checkbox is unchecked, uncheck all checkboxes in the subgroup
	        subgroupCheckboxes.forEach((subgroupCheckbox) => {
	          subgroupCheckbox.checked = false;
	        });
	      }
	    }
	  });
	});