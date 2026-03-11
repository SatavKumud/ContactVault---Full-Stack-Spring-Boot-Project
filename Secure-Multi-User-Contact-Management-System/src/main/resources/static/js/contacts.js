const baseURL = "http://localhost:8085"

var contactModal;

function openContactModal() {
    // If the modal hasn't been set up yet, set it up NOW
    if (!contactModal) {
        const viewContactModal = document.getElementById('view_contact_modal');
        
        const options = {
            placement: 'bottom-right',
            backdrop: 'dynamic',
            backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
            closable: true,
            onHide: () => { console.log('modal is hidden'); },
            onShow: () => { console.log('modal is shown'); },
            onToggle: () => { console.log('modal has been toggled'); }
        };

        const instanceOptions = {
            id: 'view_contact_modal',
            override: true
        };

        contactModal = new Modal(viewContactModal, options, instanceOptions);
    }

    // Now call show
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactData(id) {
    console.log("Contact ID is: " + id);

    try {
        const response = await fetch(`${baseURL}/api/contacts/${id}`);
        const data = await response.json();
        console.log(data);

        // Fill the text data
        document.querySelector('#contact_name').innerHTML = data.name;
        document.querySelector('#contact_email').innerHTML = data.email;
        document.querySelector("#contact_address").innerHTML = data.address;
        document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
        document.querySelector("#contact_about").innerHTML = data.description;

        // Image fix: Make sure this ID exists in HTML!
        const contactImg = document.querySelector("#contact_image");

// Logic: Use database picture if it exists, otherwise use default
if (data.picture) {
    contactImg.src = data.picture;
} else {
    contactImg.src = 'https://static.vecteezy.com/system/resources/previews/020/765/399/non_2x/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg';
}

        // Favorite fix: Use querySelector
        const favElement = document.querySelector("#contact_favorite");
        if (data.favorite) {
            favElement.innerHTML = "<i class='fas fa-star text-yellow-400'></i> Favorite Contact";
        } else {
            favElement.innerHTML = "Not Favorite Contact";
        }

        // Links
        document.querySelector("#contact_website").href = data.websiteLink;
        document.querySelector("#contact_website").innerHTML = data.websiteLink || "No Website";
        document.querySelector("#contact_linkedIn").href = data.linkedInLink;
        document.querySelector("#contact_linkedIn").innerHTML = data.linkedInLink || "No LinkedIn";
        
        // Open the modal AFTER data is filled
        openContactModal();
        
    } catch(error) {
        console.log("Error: ", error);
    }

    
    
    // Now call the function to show the modal
    //openContactModal();

    // In the future, you will put your fetch/API call here
}

//delete contact function

async function deleteContact(id) {
   Swal.fire({
  title: "Do you want to delete the contact?",
  icon: "warning",
  showCancelButton: true,
  confirmButtonText: "Delete",
  denyButtonText: "No"
}).then((result) => {
  if (result.isConfirmed) {
    const url = `${baseURL}/user/contacts/delete/`+id;
    window.location.replace(url);
  } 
});
    
}