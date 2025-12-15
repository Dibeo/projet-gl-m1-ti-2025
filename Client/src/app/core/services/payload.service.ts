import Swal from 'sweetalert2';

export function openPaywall() {
  Swal.fire({
    title: '🚀 Passez à Premium',
    html: `
      <div class="paywall-container" style="
        display: flex; 
        justify-content: center; 
        gap: 20px; 
        font-family: Arial, sans-serif;
        color: #fff;
        padding: 30px;
      ">

        <!-- FREE Plan -->
        <div class="plan-column" style="
          flex: 1; 
          border: 2px solid #555; 
          border-radius: 10px; 
          padding: 20px; 
          text-align: center; 
          background-color: #2a2a3d;
        ">
          <h3 style="margin-bottom: 10px; color: #aaa;">FREE</h3>
          <p class="price" style="font-size: 24px; font-weight: bold; margin-bottom: 15px;">0 €</p>
          <ul style="list-style: none; padding: 0; text-align: left; margin-bottom: 20px;">
            <li>✔ Poster des annonces</li>
            <li>✔ Postuler aux annonces</li>
            <li style="color: #777;">✖ Messagerie privée</li>
            <li style="color: #777;">✖ Photos</li>
          </ul>
          <button class="plan-btn disabled" style="
            padding: 10px 20px; 
            border: none; 
            border-radius: 5px; 
            background-color: #555; 
            color: #ccc; 
            cursor: not-allowed;
          ">Plan actuel</button>
        </div>

        <!-- PREMIUM Plan -->
        <div class="plan-column highlighted" style="
          flex: 1; 
          border: 2px solid #f39c12; 
          border-radius: 10px; 
          padding: 20px; 
          text-align: center; 
          background-color: #3d2e1f; 
          box-shadow: 0 4px 20px rgba(255, 153, 0, 0.5); 
          transform: scale(1.05);
        ">
          <h3 style="margin-bottom: 10px; color: #f39c12;">PREMIUM</h3>
          <p class="price" style="font-size: 24px; font-weight: bold; margin-bottom: 15px;">9,99 € / mois</p>
          <ul style="list-style: none; padding: 0; text-align: left; margin-bottom: 20px;">
            <li>✔ 1 Photos par annonce</li>
            <li>✔ Messagerie privée</li>
            <li>✔ Support prioritaire</li>
          </ul>
          <button class="plan-btn primary" style="
            padding: 10px 20px; 
            border: none; 
            border-radius: 5px; 
            background-color: #f39c12; 
            color: #fff; 
            cursor: pointer;
          ">Choisir</button>
        </div>

        <!-- PRO Plan -->
        <div class="plan-column" style="
          flex: 1; 
          border: 2px solid #2ecc71; 
          border-radius: 10px; 
          padding: 20px; 
          text-align: center; 
          background-color: #1f3d2f;
        ">
          <h3 style="margin-bottom: 10px; color: #2ecc71;">PRO</h3>
          <p class="price" style="font-size: 24px; font-weight: bold; margin-bottom: 15px;">19,99 € / mois</p>
          <ul style="list-style: none; padding: 0; text-align: left; margin-bottom: 20px;">
            <li>✔ Photos illimité</li>
            <li>✔ Mise en avant</li>
            <li>✔ Accès anticipé</li>
          </ul>
          <button class="plan-btn" style="
            padding: 10px 20px; 
            border: none; 
            border-radius: 5px; 
            background-color: #2ecc71; 
            color: #fff; 
            cursor: pointer;
          ">Choisir</button>
        </div>

      </div>
    `,
    showConfirmButton: false,
    showCloseButton: true,
    width: '1000px',
    customClass: {
      popup: 'paywall-popup',
    },
    // Supprime le scroll vertical
    scrollbarPadding: false,
    theme: 'dark'
  });
}
