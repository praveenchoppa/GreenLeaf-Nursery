const yearEl = document.getElementById("year");
if (yearEl) yearEl.innerText = new Date().getFullYear();

function getParam(key) {
  return new URLSearchParams(window.location.search).get(key);
}

function safe(v, fallback = "") {
  return (v === null || v === undefined || v === "") ? fallback : v;
}

async function loadFlower() {
  const flowerId = getParam("id");
  const backUrl = getParam("back");

  const backBtn = document.getElementById("backBtn");
  if (backUrl) backBtn.href = decodeURIComponent(backUrl);

  if (!flowerId) {
    document.getElementById("flowerName").innerText = "Flower not found";
    document.getElementById("flowerDesc").innerText = "Missing flower id in URL";
    return;
  }

  try {
    const res = await fetch(`/api/flowers/${flowerId}`);
    const f = await res.json();

    document.title = `${safe(f.name, "Flower")} - Details`;

    document.getElementById("flowerImg").src = safe(f.imageUrl, "https://via.placeholder.com/800x600");
    document.getElementById("flowerName").innerText = safe(f.name, "Flower");
    document.getElementById("flowerDesc").innerText = safe(f.description, "Fresh and healthy plant.");

    document.getElementById("priceBadge").innerText = `₹ ${safe(f.price, "--")}`;
    document.getElementById("potBadge").innerText = `🪴 ${safe(f.potSize, "--")}`;
    document.getElementById("weightBadge").innerText = `⚖️ ${safe(f.weight, "--")} kg`;

  } catch (e) {
    console.log("Error loading flower", e);
    document.getElementById("flowerName").innerText = "Error loading flower";
    document.getElementById("flowerDesc").innerText = "Please try again later.";
  }
}

loadFlower();
