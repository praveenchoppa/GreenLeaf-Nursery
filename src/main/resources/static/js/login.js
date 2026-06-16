async function login() {

    const username =
        document.getElementById("username").value.trim();

    const password =
        document.getElementById("password").value.trim();

    const error =
        document.getElementById("error");

    error.innerText = "";

    try {

        const response =
            await fetch("/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    username,
                    password
                })
            });

        const data =
            await response.json();

        if (!response.ok) {

            error.innerText =
                data.message || "Login failed";

            return;
        }

        localStorage.setItem(
            "token",
            data.token
        );

       localStorage.setItem(
    "role",
    data.role
);

if (
    data.role === "ROLE_ADMIN"
) {

    window.location.href =
        "/admin/dashboard.html";

} else {

    window.location.href =
        "/index.html";
}

    } catch (e) {

        error.innerText =
            "Server error";
    }
}