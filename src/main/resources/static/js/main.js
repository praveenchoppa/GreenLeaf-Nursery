const yearEl = document.getElementById("year");
if (yearEl) yearEl.innerText = new Date().getFullYear();

const categoriesGrid = document.getElementById("categoriesGrid");
const emptyState = document.getElementById("emptyState");
const catSearch = document.getElementById("catSearch");

let categories = [];

function renderCategories(list) {
  categoriesGrid.innerHTML = "";

  if (!list || list.length === 0) {
    emptyState.classList.remove("hidden");
    return;
  }

  emptyState.classList.add("hidden");

  list.forEach(cat => {
    const card = document.createElement("div");
    card.className = "card";
    card.innerHTML = `
      <div class="card-top"></div>
      <div class="card-body">
        <div class="card-title">${cat.name}</div>
        <p class="card-sub">Tap to view variations & details</p>
      </div>
    `;

    card.onclick = () => {
      window.location.href = `category.html?id=${cat.id}&name=${encodeURIComponent(cat.name)}`;
    };

    categoriesGrid.appendChild(card);
  });
}

async function loadCategories() {
  try {
    const res = await fetch("/api/categories");
    const response = await res.json();
    categories = response.data;
    renderCategories(categories);
  } catch (e) {
    console.log("Error loading categories", e);
    renderCategories([]);
  }
}

if (catSearch) {
  catSearch.addEventListener("input", (e) => {
    const q = e.target.value.toLowerCase().trim();
    const filtered = categories.filter(c => c.name.toLowerCase().includes(q));
    renderCategories(filtered);
  });
}

loadCategories();
