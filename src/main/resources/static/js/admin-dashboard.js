function getToken() {
  return localStorage.getItem("token");
}

function logout() {
  localStorage.removeItem("token");
  window.location.href = "/login.html";
}

function requireLogin() {

  if (!getToken()) {
    window.location.href = "/login.html";
  }
}

requireLogin();

async function api(url, options = {}) {

  const token = getToken();

  const headers = options.headers || {};

  headers["Authorization"] =
    `Bearer ${token}`;

  const response = await fetch(url, {
    ...options,
    headers
  });

  if (
    response.status === 401 ||
    response.status === 403
  ) {

    localStorage.removeItem("token");

    alert("Session expired. Please login again.");

    window.location.href =
      "/login.html";

    return null;
  }

  return response;
}

async function uploadFlowerImage() {

  const fileInput =
    document.getElementById("flowerImgFile");

  const file = fileInput.files[0];

  if (!file) {
    return alert("Please choose an image first");
  }

  const formData = new FormData();

  formData.append("file", file);

  const res = await api("/api/admin/upload", {
    method: "POST",
    body: formData
  });

  if (!res) return;

  if (!res.ok) {

    const err = await res.json();

    return alert(err.message);
  }

const response = await res.json();

const imageUrl = response.data;

  document.getElementById(
    "flowerImgUrl"
  ).value = imageUrl;

  alert(" Image uploaded successfully!");
}

async function loadCategories() {

  const res = await fetch("/api/categories");

const response = await res.json();

const cats = response.data;

  const select =
    document.getElementById("flowerCategory");

  select.innerHTML = "";

  cats.forEach(c => {

    const op = document.createElement("option");

    op.value = c.id;
    op.textContent = c.name;

    select.appendChild(op);
  });

  const catList =
    document.getElementById("catList");

  catList.innerHTML = "";

  cats.forEach(c => {

    const row = document.createElement("div");

    row.className = "rowItem";

    row.innerHTML = `
      <div class="rowLeft">
        <div class="rowTitle">${c.name}</div>
        <div class="rowSub">ID: ${c.id}</div>
      </div>

      <div class="rowActions">
        <button
          class="btn btn-ghost"
          onclick="deleteCategory(${c.id})"
        >
          Delete
        </button>
      </div>
    `;

    catList.appendChild(row);
  });
}

async function loadFlowers() {

  const res = await fetch("/api/flowers");

  const response = await res.json();

const flowers = response.data.content;

  const flowerList =
    document.getElementById("flowerList");

  flowerList.innerHTML = "";

  flowers.forEach(f => {

    const row = document.createElement("div");

    row.className = "rowItem";

    const flowerObj =
      JSON.stringify(f).replaceAll("'", "\\'");

    row.innerHTML = `
      <div class="rowLeft">
        <div class="rowTitle">
          ${f.name} (₹${f.price})
        </div>

        <div class="rowSub">
          Pot: ${f.potSize}
          • Weight: ${f.weight ?? "-"}kg
          • ID: ${f.id}
        </div>
      </div>

      <div class="rowActions">

        <button
          class="btn btn-ghost"
          onclick='startEditFlower(${flowerObj})'
        >
          Edit
        </button>

        <button
          class="btn btn-ghost"
          onclick="deleteFlower(${f.id})"
        >
          Delete
        </button>

      </div>
    `;

    flowerList.appendChild(row);
  });
}

async function addCategory() {

  const name =
    document.getElementById("catName")
      .value
      .trim();

  if (!name) {
    return alert("Enter category name");
  }

  const res = await api(
    "/api/admin/categories",
    {
      method: "POST",

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify({ name })
    }
  );

  if (!res) return;

  if (!res.ok) {

    const err = await res.json();

    return alert(err.message);
  }

  document.getElementById("catName").value = "";

  await loadCategories();
}

async function deleteCategory(id) {

  const ok =
    confirm("Delete this category?");

  if (!ok) return;

  const res = await api(
    `/api/admin/categories/${id}`,
    {
      method: "DELETE"
    }
  );

  if (!res) return;

  if (!res.ok) {

    const err = await res.json();

    return alert(err.message);
  }

  await loadCategories();
}

async function addFlower() {

  const categoryId =
    document.getElementById(
      "flowerCategory"
    ).value;

  const name =
    document.getElementById(
      "flowerName"
    ).value.trim();

  const price =
    document.getElementById(
      "flowerPrice"
    ).value;

  const weight =
    document.getElementById(
      "flowerWeight"
    ).value;

  const potSize =
    document.getElementById(
      "flowerPotSize"
    ).value.trim();

  const description =
    document.getElementById(
      "flowerDesc"
    ).value.trim();

  const imageUrl =
    document.getElementById(
      "flowerImgUrl"
    ).value.trim();

  if (!name || !price || !potSize) {

    return alert(
      "Name, Price and Pot Size are required"
    );
  }

  if (!imageUrl) {
    return alert(
      "Please upload an image first"
    );
  }

  const res = await api(
    "/api/admin/flowers",
    {
      method: "POST",

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify({
        categoryId: Number(categoryId),
        name,
        price: Number(price),
        weight: weight ? Number(weight) : null,
        potSize,
        description,
        imageUrl
      })
    }
  );

  if (!res) return;

  if (!res.ok) {

    const err = await res.json();

    return alert(err.message);
  }

  clearFlowerForm();

  await loadFlowers();

  alert(" Flower added successfully!");
}

async function deleteFlower(id) {

  const ok =
    confirm("Delete this flower?");

  if (!ok) return;

  const res = await api(
    `/api/admin/flowers/${id}`,
    {
      method: "DELETE"
    }
  );

  if (!res) return;

  if (!res.ok) {

    const err = await res.json();

    return alert(err.message);
  }

  await loadFlowers();

  alert(" Flower deleted successfully!");
}

function startEditFlower(f) {

  document.getElementById(
    "editFlowerId"
  ).value = f.id;

  document.getElementById(
    "flowerCategory"
  ).value = f.category?.id ?? "";

  document.getElementById(
    "flowerName"
  ).value = f.name ?? "";

  document.getElementById(
    "flowerPrice"
  ).value = f.price ?? "";

  document.getElementById(
    "flowerWeight"
  ).value = f.weight ?? "";

  document.getElementById(
    "flowerPotSize"
  ).value = f.potSize ?? "";

  document.getElementById(
    "flowerDesc"
  ).value = f.description ?? "";

  document.getElementById(
    "flowerImgUrl"
  ).value = f.imageUrl ?? "";

  document.getElementById(
    "addBtn"
  ).style.display = "none";

  document.getElementById(
    "updateBtn"
  ).style.display = "inline-flex";

  document.getElementById(
    "cancelEditBtn"
  ).style.display = "inline-flex";

  window.scrollTo({
    top: 0,
    behavior: "smooth"
  });
}

function cancelEdit() {

  document.getElementById(
    "editFlowerId"
  ).value = "";

  clearFlowerForm();

  document.getElementById(
    "addBtn"
  ).style.display = "inline-flex";

  document.getElementById(
    "updateBtn"
  ).style.display = "none";

  document.getElementById(
    "cancelEditBtn"
  ).style.display = "none";
}

async function updateFlower() {

  const id =
    document.getElementById(
      "editFlowerId"
    ).value;

  if (!id) {
    return alert(
      "No flower selected to update"
    );
  }

  const categoryId =
    document.getElementById(
      "flowerCategory"
    ).value;

  const name =
    document.getElementById(
      "flowerName"
    ).value.trim();

  const price =
    document.getElementById(
      "flowerPrice"
    ).value;

  const weight =
    document.getElementById(
      "flowerWeight"
    ).value;

  const potSize =
    document.getElementById(
      "flowerPotSize"
    ).value.trim();

  const description =
    document.getElementById(
      "flowerDesc"
    ).value.trim();

  const imageUrl =
    document.getElementById(
      "flowerImgUrl"
    ).value.trim();

  if (!name || !price || !potSize) {

    return alert(
      "Name, Price and Pot Size are required"
    );
  }

  if (!imageUrl) {

    return alert(
      "Please upload an image first"
    );
  }

  const res = await api(
    `/api/admin/flowers/${id}`,
    {
      method: "PUT",

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify({
        categoryId: Number(categoryId),
        name,
        price: Number(price),
        weight: weight ? Number(weight) : null,
        potSize,
        description,
        imageUrl
      })
    }
  );

  if (!res) return;

  if (!res.ok) {

    const err = await res.json();

    return alert(err.message);
  }

  alert(" Flower updated successfully!");

  cancelEdit();

  await loadFlowers();
}

function clearFlowerForm() {

  document.getElementById(
    "flowerName"
  ).value = "";

  document.getElementById(
    "flowerPrice"
  ).value = "";

  document.getElementById(
    "flowerWeight"
  ).value = "";

  document.getElementById(
    "flowerPotSize"
  ).value = "";

  document.getElementById(
    "flowerDesc"
  ).value = "";

  document.getElementById(
    "flowerImgUrl"
  ).value = "";

  document.getElementById(
    "flowerImgFile"
  ).value = "";
}

// ✅ Initial load
loadCategories();
loadFlowers();