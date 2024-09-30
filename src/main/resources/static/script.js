// Submit the driver creation form
document.getElementById('driver-form').addEventListener('submit', async (e) => {
    e.preventDefault(); // Prevent the default form submission

    // Gather driver details from the form
    const driver = {
        partnerName: document.getElementById('partnerName').value,
        firstName: document.getElementById('firstName').value,
        middleName: document.getElementById('middleName').value,
        lastName: document.getElementById('lastName').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        referralCode: document.getElementById('referralCode').value
    };

    // Send a POST request to create a driver
    const response = await fetch('/api/drivers/createDriverDetails', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(driver)
    });

    if (response.ok) {
        alert('Driver created successfully!');
        document.getElementById('driver-form').reset(); // Reset the form
    } else {
        alert('Error creating driver!');
    }
});

// Fetch all drivers when the button is clicked
document.getElementById('fetch-drivers').addEventListener('click', async () => {
    const response = await fetch('/api/drivers/getAllDrivers');

    if (response.ok) {
        const drivers = await response.json();
        const driverList = document.getElementById('driver-list');
        driverList.innerHTML = ''; // Clear the current list

        // Populate the list with driver details
        drivers.forEach(driver => {
            const listItem = document.createElement('li');
            listItem.textContent = `${driver.firstName} ${driver.lastName} - ${driver.phoneNumber}`;
            driverList.appendChild(listItem);
        });
    } else {
        alert('Error fetching drivers!');
    }
});

// Fetch driver by ID
document.getElementById('fetch-driver').addEventListener('click', async () => {
    const id = document.getElementById('driverId').value;

    const response = await fetch(`/api/drivers/getDriverByIdDetails/${id}`);

    if (response.ok) {
        const driver = await response.json();
        console.log(driver); // Log the driver object to check its structure
        const driverDetails = document.getElementById('driver-details');
        
        // Display the fetched driver's name and phone number
        driverDetails.innerHTML = `<h3>Driver Details:</h3>
                                   <p>Name: ${driver.name || 'N/A'}</p>
                                   <p>Phone: ${driver.phonenumber || 'N/A'}</p>`;
    } else {
        document.getElementById('driver-details').innerHTML = '<p>Driver not found!</p>';
    }
});
