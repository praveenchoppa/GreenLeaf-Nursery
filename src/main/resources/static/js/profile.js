const token =
    localStorage.getItem("token");

if (!token) {

    window.location.href =
        "/login.html";
}

async function loadProfile() {

    try {

        const response =
            await fetch("/api/users/me", {

                headers: {
                    Authorization:
                        "Bearer " + token
                }
            });

        if (!response.ok) {

            logout();
            return;
        }

        const user =
            await response.json();

        document.getElementById(
            "username"
        ).innerText =
            user.username;

        document.getElementById(
            "email"
        ).innerText =
            user.email;

        document.getElementById(
            "role"
        ).innerText =
            user.role;

    } catch (e) {

        console.log(e);
    }
}

function logout() {

    localStorage.removeItem(
        "token"
    );

    window.location.href =
        "/login.html";
}

loadProfile();