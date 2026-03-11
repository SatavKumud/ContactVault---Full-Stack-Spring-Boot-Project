console.log("Script loaded successfully");

// Get current theme from localStorage or default to light
let currentTheme = getTheme();
document.documentElement.classList.add(currentTheme);
applyTheme(currentTheme); // Apply theme on page load

// Function to handle theme change button click
function changeTheme() {
    const changeThemeButton = document.querySelector('#theme_change_button');

    if (!changeThemeButton) return; // safety check

    changeThemeButton.addEventListener('click', () => {
        const oldTheme = currentTheme;
        console.log("button clicked");

        // Toggle theme
        if (currentTheme === "dark") {
            currentTheme = "light";
        } else {
            currentTheme = "dark";
        }

        // Save new theme to localStorage
        setTheme(currentTheme);

        // Update HTML class for theme
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(currentTheme);

        // Apply color changes for elements based on theme
        applyTheme(currentTheme);
    });
}

// Function to apply theme-specific styles
function applyTheme(theme) {
    // null-safe: only run if element exists (public navbar has it, user navbar may not)
    const contactVaultText = document.getElementById('text');
    if (contactVaultText) {
        if (theme === "dark") {
            contactVaultText.style.color = "white";
        } else {
            contactVaultText.style.color = "#1f2937";
        }
    }
}

// Set theme in localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Get theme from localStorage
function getTheme() {
    return localStorage.getItem("theme") || "light";
}

// Initialize theme button listener
changeTheme();