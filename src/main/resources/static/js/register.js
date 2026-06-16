async function registerUser() {

    const username =
        document.getElementById("username").value.trim();

    const email =
        document.getElementById("email").value.trim();

    const password =
        document.getElementById("password").value.trim();

    const message =
        document.getElementById("message");

    message.innerText = "";
    message.className = "msg";

    try {

        const response =
            await fetch("/api/auth/register", {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    username,
                    email,
                    password
                })
            });

        const data =
            await response.json();

        if (!response.ok) {

            message.classList.add("error");
           message.innerText =
    data.message || "Registration failed";

            return;
        }

        message.classList.add("success");
        message.innerText =
            "Registration successful. Redirecting...";

        setTimeout(() => {

            window.location.href =
                "/login.html";

        }, 1500);

    } catch (e) {

        message.classList.add("error");
        message.innerText =
            "Server error";
    }
}