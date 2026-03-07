// NEXUS E-Commerce — main.js

// Auto-dismiss alerts after 4 seconds
document.querySelectorAll('.alert').forEach(alert => {
    setTimeout(() => {
        alert.style.opacity = '0';
        alert.style.transition = 'opacity 0.5s';
        setTimeout(() => alert.remove(), 500);
    }, 4000);
});

// Add-to-cart button feedback
document.querySelectorAll('.add-to-cart').forEach(btn => {
    btn.addEventListener('click', function() {
        const original = this.textContent;
        this.textContent = '✓ Added!';
        this.style.background = '#2ecc71';
        this.style.color = '#fff';
        setTimeout(() => {
            this.textContent = original;
            this.style.background = '';
            this.style.color = '';
        }, 1500);
    });
});

// Quantity input: prevent values below 0
document.querySelectorAll('.qty-input').forEach(input => {
    input.addEventListener('change', function() {
        if (parseInt(this.value) < 0) this.value = 0;
    });
});

// Animate product cards on scroll
const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
        }
    });
}, { threshold: 0.1 });

document.querySelectorAll('.product-card').forEach((card, i) => {
    card.style.opacity = '0';
    card.style.transform = 'translateY(20px)';
    card.style.transition = `opacity 0.4s ease ${i * 0.05}s, transform 0.4s ease ${i * 0.05}s`;
    observer.observe(card);
});
