const token =
    localStorage.getItem("token");

const role =
    localStorage.getItem("role");

const loginLink =
    document.getElementById("loginLink");

const logoutLink =
    document.getElementById("logoutLink");

const profileLink =
    document.getElementById("profileLink");

const dashboardLink =
    document.getElementById("dashboardLink");

if (token) {

    if (loginLink)
        loginLink.style.display = "none";

    if (logoutLink)
        logoutLink.style.display = "inline-block";

    if (
        role === "ROLE_USER"
        && profileLink
    ) {
        profileLink.style.display =
            "inline-block";
    }

    if (
        role === "ROLE_ADMIN"
        && dashboardLink
    ) {
        dashboardLink.style.display =
            "inline-block";
    }
}

if (logoutLink) {

    logoutLink.onclick = function () {

        localStorage.removeItem(
            "token"
        );

        localStorage.removeItem(
            "role"
        );

        window.location.href =
            "/index.html";
    };
}