// 가격을 한국 돈으로 변환하는 함수
function convertToKRW(priceUSD) {
    const exchangeRate = 1150; // 1 USD에 대한 대략적인 환율 (변경 가능)
    const priceKRW = Math.round(priceUSD * exchangeRate);
    return priceKRW.toLocaleString("ko-KR") + " KRW"; // 통화 단위를 추가하여 반환
}

// 제품 정보를 받아와서 HTML 요소로 변환하는 함수
function createProductElement(product) {
    const productElement = document.createElement("div");
    productElement.classList.add("product");

    productElement.innerHTML = `
        <img src="${product.image}" alt="${product.title}" />
        <div class="product-info">
            <h2>${product.title}</h2>
            <p>${convertToKRW(product.price)}</p>
            <p>${product.category}</p>
        </div>
    `;
    return productElement;
}

document.addEventListener("DOMContentLoaded", () => {
    const productList = document.getElementById("product-list");

    // FakeStore API에서 제품 목록 가져오기
    fetch("https://fakestoreapi.com/products")
        .then(response => response.json())
        .then(products => {
            // 가져온 제품 목록을 화면에 출력
            products.forEach(product => {
                const productElement = createProductElement(product);
                // 제품 요소를 제품 목록에 추가
                productList.appendChild(productElement);
            });
        });
});
