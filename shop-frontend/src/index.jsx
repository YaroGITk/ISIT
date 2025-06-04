import React, { useState, useEffect } from 'react';
import { createRoot } from 'react-dom/client';

function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/products')
      .then(res => res.json())
      .then(setProducts);
  }, []);

  return (
    <div>
      <h1>Products</h1>
      <ul>
        {products.map(p => (
          <li key={p.id}>{p.name} - {p.price}</li>
        ))}
      </ul>
    </div>
  );
}

const root = createRoot(document.getElementById('root'));
root.render(<App />);
