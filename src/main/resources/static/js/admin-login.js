async function login() {

  const username =
    document.getElementById("username").value.trim();

  const password =
    document.getElementById("password").value.trim();

  const errorMsg =
    document.getElementById("errorMsg");

  errorMsg.style.display = "none";

  if (!username || !password) {

    errorMsg.innerText =
      "Please enter username and password";

    errorMsg.style.display = "block";

    return;
  }

  try {

    const res = await fetch("/api/auth/login", {

      method: "POST",

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify({
        username,
        password
      })
    });

    if (!res.ok) {

      errorMsg.innerText =
        "Invalid username/password";

      errorMsg.style.display = "block";

      return;
    }

    const data = await res.json();

    localStorage.setItem(
      "adminToken",
      data.token
    );

    window.location.href =
      "/admin/dashboard.html";

  } catch (e) {

    errorMsg.innerText =
      "Server error. Try again.";

    errorMsg.style.display = "block";
  }
}