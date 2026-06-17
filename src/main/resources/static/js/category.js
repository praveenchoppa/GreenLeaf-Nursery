const yearEl = document.getElementById("year");
if (yearEl) yearEl.innerText = new Date().getFullYear();

const flowersGrid = document.getElementById("flowersGrid");
const catTitle = document.getElementById("catTitle");
const catHint = document.getElementById("catHint");
const flowerSearch = document.getElementById("flowerSearch");
const flowerEmpty = document.getElementById("flowerEmpty");

let flowers = [];

function getParam(key) {
  return new URLSearchParams(window.location.search).get(key);
}

function safeText(v) {
  return (v === null || v === undefined) ? "" : v;
}

function renderFlowers(list) {

  flowersGrid.innerHTML = "";

  if (!list || list.length === 0) {

    flowerEmpty.innerHTML = `
      <div class="empty-icon">🌸</div>
      <div class="empty-title">
        No Flowers Available
      </div>
      <div class="empty-sub">
        Flowers will be added soon.
      </div>
    `;

    flowerEmpty.classList.remove("hidden");
    return;
  }

  flowerEmpty.classList.add("hidden");

  list.forEach(f => {

    const card = document.createElement("div");
    card.className = "card";

    card.innerHTML = `
      <img
        class="flower-img"
        src="${safeText(f.imageUrl)}"
        alt="${safeText(f.name)}"
      />

      <div class="card-body">

        <div class="card-title">
          ${safeText(f.name)}
        </div>

        <p class="card-sub">
          ${safeText(f.description)}
        </p>

        <div class="flower-meta">
          <span class="badge">
            ₹ ${safeText(f.price)}
          </span>

          <span class="badge">
            🪴 ${safeText(f.potSize)}
          </span>

          <span class="badge">
            ⚖️ ${safeText(f.weight)} kg
          </span>
        </div>

      </div>
    `;

    card.onclick = () => {

      const back =
        encodeURIComponent(
          window.location.href
        );

      window.location.href =
        `flower.html?id=${f.id}&back=${back}`;
    };

    flowersGrid.appendChild(card);
  });
}

async function loadFlowers() {

  const id = getParam("id");
  const name = getParam("name") || "Category";

  catTitle.innerText = `🌼 ${name}`;
  catHint.innerText = `Loading flowers in ${name}...`;

  flowersGrid.innerHTML = `
    <div class="empty-state">
      <div class="empty-icon">⏳</div>
      <div class="empty-title">
        Loading Flowers...
      </div>
    </div>
  `;

  try {

    const res =
      await fetch(
        `/api/flowers?categoryId=${id}`
      );

    const response =
      await res.json();

    flowers =
      response.data.content;

    catHint.innerText =
      `Found ${flowers.length} flower(s) in ${name}`;

    renderFlowers(flowers);

  } catch (e) {

    console.log(
      "Error loading flowers",
      e
    );

    catHint.innerText =
      "Could not load flowers.";

    renderFlowers([]);
  }
}

if (flowerSearch) {
  flowerSearch.addEventListener("input", (e) => {
    const q = e.target.value.toLowerCase().trim();
    const filtered = flowers.filter(fl => (fl.name || "").toLowerCase().includes(q));
    renderFlowers(filtered);
  });
}

loadFlowers();
