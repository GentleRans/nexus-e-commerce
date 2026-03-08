package com.nexus.ecommerce.config;

import com.nexus.ecommerce.model.Category;
import com.nexus.ecommerce.model.Product;
import com.nexus.ecommerce.model.User;
import com.nexus.ecommerce.repository.CategoryRepository;
import com.nexus.ecommerce.repository.ProductRepository;
import com.nexus.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.*;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private CategoryRepository categoryRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        try {
    if (userRepo.count() > 0) return;
} catch (Exception e) {
    return;
} // already seeded

        // ---- ADMIN USER ----
        User admin = new User("Admin", "NEXUS", "admin@nexus.com", encoder.encode("admin123"), "ROLE_ADMIN");
        userRepo.save(admin);
        User customer = new User("John", "Doe", "john@example.com", encoder.encode("user123"), "ROLE_USER");
        userRepo.save(customer);

        // ---- CATEGORIES ----
        Category electronics = cat("Electronics", "electronics", "💻", "Gadgets and tech gear");
        Category fashion     = cat("Fashion", "fashion", "👗", "Clothing and accessories");
        Category home        = cat("Home & Living", "home", "🏡", "Home decor and essentials");
        Category sports      = cat("Sports & Fitness", "sports", "⚽", "Gear for active lifestyles");
        Category beauty      = cat("Beauty", "beauty", "💄", "Skincare and cosmetics");
        Category books       = cat("Books", "books", "📚", "Knowledge and literature");
        Category food        = cat("Food & Drinks", "food", "🍎", "Organic and healthy foods");
        Category toys        = cat("Toys & Games", "toys", "🎮", "Fun for all ages");

        // ---- ELECTRONICS ----
        p("4K Ultra HD Smart TV 55\"",        "Crystal-clear 4K display with built-in streaming apps and voice control.", 699, 899, "📺", "Hot",  "popular", 4.8, 1240, electronics);
        p("Wireless Noise-Cancelling Headphones","Studio-quality sound with 30hr battery and active noise cancellation.",249, 329, "🎧", "Sale", "sale",    4.7,  980, electronics);
        p("Gaming Laptop RTX 4060",            "Dominate every game with 144Hz display and 16GB RAM.",                  1199, null,"💻", "New",  "new",     4.9,  564, electronics);
        p("Smartphone Pro Max 256GB",          "Triple camera system, 5G, and all-day battery life.",                    999,1099,"📱", "Sale", "sale",    4.8, 2100, electronics);
        p("Bluetooth Speaker Waterproof",      "360° sound with 24hr playtime, perfect for outdoors.",                    89, 120,"🔊", null,   "popular", 4.6,  730, electronics);
        p("Mechanical Gaming Keyboard",        "RGB backlit with tactile switches for competitive gaming.",               129, null,"⌨️","New",  "new",     4.7,  412, electronics);
        p("Wireless Gaming Mouse",             "Ultra-precise sensor with 70hr battery life.",                             79,  99,"🖱️",null,   "popular", 4.6,  620, electronics);
        p("iPad Air 11\" M2",                  "The most versatile iPad ever with M2 chip.",                             749, null,"📟","New",  "new",     4.9, 1800, electronics);
        p("Smartwatch Series 10",              "Health monitoring, GPS, and 36hr battery.",                               399, 449,"⌚","Sale", "sale",    4.7,  890, electronics);
        p("Drone 4K Camera",                   "Obstacle avoidance, 30min flight time, 4K video.",                       449, 599,"🚁","Sale", "sale",    4.5,  310, electronics);
        p("USB-C Hub 12-in-1",                 "Expand your connectivity with HDMI, SD card, and more.",                  59,  79,"🔌",null,   "popular", 4.6,  540, electronics);
        p("Portable SSD 1TB",                  "Transfer speeds up to 1050MB/s in a pocket-sized form.",                 109, 139,"💾",null,   "popular", 4.8,  760, electronics);
        p("Webcam 4K 60fps",                   "Crystal-clear video calls with auto-focus and HDR.",                     149, null,"📷","New",  "new",     4.7,  420, electronics);
        p("True Wireless Earbuds",             "30hr total battery with spatial audio and transparency mode.",            149, 199,"🎵",null,   "popular", 4.6, 1560, electronics);
        p("Ring Light 18\" Professional",      "3 color modes for perfect content creation lighting.",                     79, null,"💡","New",  "new",     4.5,  380, electronics);
        p("Smart Home Hub",                    "Control all your smart devices from one central hub.",                   129, 159,"🏠",null,   "popular", 4.4,  280, electronics);
        p("Curved Gaming Monitor 27\"",        "165Hz refresh rate, 1ms response, WQHD resolution.",                     329, 429,"🖥️","Sale","sale",    4.8,  670, electronics);
        p("Wireless Charging Pad 3-in-1",      "Charge phone, watch, and earbuds simultaneously.",                        49,  69,"⚡",null,   "popular", 4.5,  480, electronics);
        p("Action Camera 5K",                  "Waterproof to 10m, 4K 120fps slow motion.",                              299, 359,"🎥","Sale", "sale",    4.7,  390, electronics);
        p("Mini Projector Portable",           "100\" screen, built-in speakers, connects to any device.",               189, 249,"📽️","Sale","sale",    4.4,  320, electronics);
        p("Digital Picture Frame 10\"",        "Wi-Fi enabled, share photos from anywhere in the world.",                 89, null,"🖼️","New", "new",     4.3,  210, electronics);
        p("Electric Standing Desk",            "Height-adjustable with memory presets and cable management.",             599, 799,"🖥️","Sale","sale",    4.8,  234, electronics);

        // ---- FASHION ----
        p("Oversized Wool Trench Coat",        "Classic silhouette in premium wool blend, fully lined.",                 189, 260,"🧥","Sale", "sale",    4.8,  430, fashion);
        p("High-Waist Yoga Leggings",          "4-way stretch fabric with hidden waistband pocket.",                      59, null,"🩱",null,   "popular", 4.9, 2100, fashion);
        p("Linen Button-Down Shirt",           "Breathable 100% linen for effortless summer style.",                      69,  89,"👕","Sale", "sale",    4.7,  670, fashion);
        p("Leather Chelsea Boots",             "Full-grain leather with elastic side panels, rubber sole.",               199, 279,"👢","Sale", "sale",    4.8,  520, fashion);
        p("Cashmere Turtleneck Sweater",       "Ultra-soft Grade-A cashmere in 8 seasonal colors.",                      149, null,"🧶","New",  "new",     4.9,  380, fashion);
        p("Wide-Leg Tailored Trousers",        "Relaxed tailoring in wrinkle-resistant fabric.",                          89, 119,"👖",null,   "popular", 4.6,  340, fashion);
        p("Canvas Tote Bag",                   "Durable canvas with interior pockets and zip top.",                       39, null,"👜","New",  "new",     4.5,  890, fashion);
        p("Silk Slip Dress",                   "100% silk charmeuse in a bias-cut silhouette.",                          129, 169,"👗","Sale", "sale",    4.7,  280, fashion);
        p("Running Sneakers Air Cushion",      "Responsive cushioning for all-day comfort.",                             139, 179,"👟","Sale", "sale",    4.8, 1400, fashion);
        p("Denim Jacket Vintage Wash",         "Classic fit with vintage-washed finish and brass buttons.",               99, null,"🧥",null,   "popular", 4.6,  560, fashion);
        p("Knit Beanie & Scarf Set",           "Matching chunky knit set in 6 cozy colors.",                              49,  69,"🧣","Sale", "sale",    4.7,  320, fashion);
        p("Formal Blazer Slim Fit",            "Italian fabric, half-canvas construction, slim silhouette.",             169, 229,"👔","Sale", "sale",    4.8,  240, fashion);
        p("Sports Bra Multi-Pack x3",          "Medium impact support with moisture-wicking fabric.",                     45, null,"🩲","New",  "new",     4.6,  760, fashion);
        p("Polarized Sunglasses",              "UV400 protection with lightweight titanium frame.",                        79, 109,"🕶️","Sale","sale",    4.7,  480, fashion);
        p("Leather Belt Classic Brown",        "Full-grain leather with antique brass buckle.",                           49, null,"👔",null,   "popular", 4.5,  390, fashion);
        p("Graphic Print T-Shirt",             "100% organic cotton with unique artistic prints.",                        35, null,"👕","New",  "new",     4.4,  980, fashion);
        p("Mini Crossbody Bag",                "Pebbled leather with adjustable chain strap.",                            89, 119,"👛","Sale", "sale",    4.6,  310, fashion);
        p("Floral Midi Skirt",                 "Chiffon fabric with a flowy A-line silhouette.",                          69, null,"👗","New",  "new",     4.7,  410, fashion);
        p("Merino Wool Socks 5-Pack",          "Itch-free merino with arch compression support.",                         35,  45,"🧦","Sale", "sale",    4.8, 1200, fashion);
        p("Puffer Vest Lightweight",           "Lightweight 90/10 down fill, packable into itself.",                      89, 119,"🦺",null,   "popular", 4.5,  280, fashion);
        p("Slip-On Loafers Suede",             "Premium suede with memory foam insole.",                                 129, null,"🥿","New",  "new",     4.7,  360, fashion);
        p("Flare Yoga Pants",                  "High-rise with flared hem for a retro-modern look.",                      65,  85,"🩱","Sale", "sale",    4.6,  540, fashion);
        p("Straw Bucket Hat",                  "Natural straw with UPF 50+ sun protection.",                              29, null,"🎩","New",  "new",     4.4,  220, fashion);
        p("Workout Shorts 2-in-1",             "Built-in liner with quick-dry fabric and zip pocket.",                    45,  59,"🩳","Sale", "sale",    4.7,  630, fashion);
        p("Platform Sandals",                  "3-inch platform with adjustable ankle strap.",                           109, 139,"👡","Sale", "sale",    4.5,  190, fashion);

        // ---- HOME & LIVING ----
        p("Scented Candle Set 6-Pack",         "Hand-poured soy wax in calming botanical scents.",                       49,  65,"🕯️","Sale","sale",    4.9, 1800, home);
        p("Ceramic Dinner Set 12-Piece",       "Microwave-safe matte ceramic in modern earth tones.",                   139, 189,"🍽️","Sale","sale",    4.8,  420, home);
        p("Bamboo Cutting Board XL",           "Extra-large sustainable bamboo with juice groove.",                       45, null,"🔪",null,   "popular", 4.7,  640, home);
        p("Linen Duvet Cover King",            "Stonewashed 100% French linen for year-round comfort.",                  129, 169,"🛏️","Sale","sale",    4.8,  390, home);
        p("Smart Air Purifier",                "HEPA H13 filter covering 500 sq ft with air quality display.",           249, 299,"💨",null,   "popular", 4.7,  310, home);
        p("Aromatic Diffuser Wood",            "Whisper-quiet ultrasonic diffuser with LED mood light.",                  59,  79,"🌿","Sale", "sale",    4.6,  560, home);
        p("Cast Iron Dutch Oven 5.5qt",        "Enameled cast iron that heats evenly and lasts forever.",                 89, null,"🫕",null,   "popular", 4.9, 1100, home);
        p("Minimalist Wall Clock 12\"",        "Silent sweep mechanism in brushed brass finish.",                         39, null,"🕐","New",  "new",     4.5,  280, home);
        p("Throw Blanket Chunky Knit",         "100% cotton chunky knit in 10 cozy colors.",                              79,  99,"🧣","Sale", "sale",    4.8,  870, home);
        p("Indoor Plant Pot Set x4",           "Terracotta pots in graduated sizes with drainage holes.",                 55, null,"🪴","New",  "new",     4.6,  430, home);
        p("Coffee Pour-Over Set",              "Borosilicate glass carafe with gooseneck kettle.",                        69,  89,"☕","Sale", "sale",    4.8,  680, home);
        p("Memory Foam Pillow 2-Pack",         "Adjustable fill with cooling gel layer.",                                 59, null,"💤",null,   "popular", 4.7,  920, home);
        p("Bathroom Vanity Mirror LED",        "3 lighting modes with touch dimmer and magnification.",                  119, 159,"🪞","Sale", "sale",    4.6,  340, home);
        p("Woven Storage Basket Set x3",       "Handwoven seagrass in graduated natural shades.",                         65, null,"🧺","New",  "new",     4.5,  260, home);
        p("Smart Plug 4-Pack",                 "Voice & app control, energy monitoring, timer schedules.",                35,  49,"🔌","Sale", "sale",    4.6,  760, home);
        p("Marble Effect Table Lamp",          "Resin marble base with linen shade, dimmer switch.",                      89, null,"🪔","New",  "new",     4.7,  190, home);
        p("Insulated Water Bottle 1L",         "24hr cold, 12hr hot, BPA-free double-wall stainless.",                   35, null,"🧴",null,   "popular", 4.9, 2400, home);
        p("Kitchen Knife Set 8-Piece",         "High-carbon stainless with ergonomic pakkawood handles.",                 99, 139,"🔪","Sale", "sale",    4.8,  570, home);
        p("Velvet Accent Chair",               "Channel-tufted velvet with gold legs and lumbar support.",               299, 399,"🪑","Sale", "sale",    4.7,  180, home);
        p("Wall Mounted Bookshelf",            "Floating walnut wood shelf, holds up to 30 lbs.",                         79, null,"📚","New",  "new",     4.6,  240, home);
        p("Yoga Mat Premium 6mm",              "Non-slip alignment lines, microfiber surface.",                           49,  65,"🧘","Sale", "sale",    4.7, 1100, home);
        p("Food Storage Container Set",        "14-piece borosilicate glass set, oven and freezer safe.",                 45, null,"🥡","New",  "new",     4.6,  480, home);
        p("Cordless Stick Vacuum",             "60-min battery, 25kPa suction, self-standing design.",                  179, 229,"🌀","Sale", "sale",    4.7,  650, home);

        // ---- SPORTS ----
        p("Professional Tennis Racket",        "Carbon graphite frame, 300g for power and control.",                     149, 199,"🎾","Sale", "sale",    4.8,  340, sports);
        p("Adjustable Dumbbells 5-52lb",       "Replace 15 sets of weights in one compact design.",                      329, 449,"🏋️","Sale","sale",    4.9,  870, sports);
        p("Resistance Bands Set 11-Piece",     "5 resistance levels from 10-50lb with handles and anchors.",              29, null,"💪",null,   "popular", 4.7, 1600, sports);
        p("Smart Jump Rope Weighted",          "Counts jumps, calories, tracks workouts via app.",                        45,  59,"🪢","Sale", "sale",    4.6,  480, sports);
        p("Foam Roller Grid Trigger Point",    "Multi-density surface for deep tissue massage.",                          35, null,"🏃",null,   "popular", 4.7,  920, sports);
        p("Basketball Spalding Official",      "Official NBA size 7, composite leather cover.",                           89, 109,"🏀","Sale", "sale",    4.8,  530, sports);
        p("Soccer Cleats Pro Level",           "Carbon fiber plate for maximum traction and speed.",                     119, 159,"⚽","Sale", "sale",    4.7,  290, sports);
        p("Cycling Helmet Aero",               "MIPS safety system, 24 vents, aerodynamic shell.",                       89, null,"🚴","New",  "new",     4.8,  260, sports);
        p("Pull-Up Bar Doorway",               "No screws needed, holds up to 300lb, multi-grip.",                        39,  55,"🏋️","Sale","sale",    4.6,  740, sports);
        p("Running Belt Waist Pack",           "Bounce-free design holds phone, keys, and cards.",                        25, null,"🏃",null,   "popular", 4.5,  860, sports);
        p("Kettlebell 20kg Cast Iron",         "Flat base, powder-coated cast iron for durability.",                      55,  75,"🏋️","Sale","sale",    4.8,  380, sports);
        p("Swim Goggles Anti-Fog",             "UV protection, triple seal for zero-leak swimming.",                      29, null,"🏊","New",  "new",     4.6,  640, sports);
        p("Hiking Backpack 45L",               "Hip belt, rain cover, hydration compatible.",                            129, 169,"🎒","Sale", "sale",    4.8,  310, sports);
        p("Compression Knee Sleeves Pair",     "Graduated compression for joint support during workouts.",                35, null,"🦵",null,   "popular", 4.7,  720, sports);
        p("Badminton Set Complete",            "2 rackets, 3 shuttlecocks, portable carry bag.",                          49,  69,"🏸","Sale", "sale",    4.5,  180, sports);
        p("Yoga Block Set x2 + Strap",         "High-density EVA foam for stability and alignment.",                      25, null,"🧘","New",  "new",     4.6,  560, sports);
        p("Sports Water Bottle 1.5L",          "Motivational time markers, leak-proof flip lid.",                         29, null,"💧",null,   "popular", 4.7, 1100, sports);
        p("Punching Bag Hanging 100lb",        "Triple-layer canvas fill, includes chains and gloves.",                  179, 229,"🥊","Sale", "sale",    4.8,  240, sports);
        p("Ski Goggles Anti-Fog OTG",          "Fits over glasses, spherical lens, magnetic lens swap.",                  69,  99,"⛷️","Sale","sale",    4.6,  190, sports);
        p("Ab Wheel Roller with Mat",          "Dual wheels for stability, ergonomic foam handles.",                      25, null,"🏋️","New", "new",     4.5,  820, sports);
        p("Gym Bag Duffel 40L",                "Wet/dry compartment, shoe pocket, USB port.",                             59,  79,"🎒","Sale", "sale",    4.7,  450, sports);

        // ---- BEAUTY ----
        p("Vitamin C Serum 20%",               "Brightening formula with hyaluronic acid and ferulic acid.",              39,  55,"✨","Sale", "sale",    4.8, 2400, beauty);
        p("Jade Gua Sha + Roller Set",         "Real jade stone for facial massage and lymphatic drainage.",               29, null,"💆",null,   "popular", 4.7, 1800, beauty);
        p("Retinol Night Cream",               "0.3% retinol with peptides for anti-aging results.",                      49,  65,"🌙","Sale", "sale",    4.9, 1100, beauty);
        p("Professional Hair Dryer 2200W",     "Ionic technology for 60% faster drying, less frizz.",                    89, 129,"💨","Sale", "sale",    4.8,  870, beauty);
        p("Lip Balm Set SPF 30 x6",            "6 tinted shades with shea butter and SPF 30 protection.",                 25, null,"💋",null,   "popular", 4.6, 1300, beauty);
        p("Hyaluronic Acid Moisturizer",       "Oil-free, 4 types of HA for all-day plump hydration.",                   35,  49,"💧","Sale", "sale",    4.8, 1600, beauty);
        p("Eyeshadow Palette 24 Shades",       "Matte, shimmer and glitter shades in one curated palette.",               45, null,"👁️","New", "new",     4.7,  940, beauty);
        p("Electric Face Cleanser",            "Silicone bristles with 3 speeds and waterproof design.",                  59,  79,"🔄","Sale", "sale",    4.7,  630, beauty);
        p("Sunscreen SPF 50+ Tinted",          "Lightweight formula with iron oxides for hyperpigmentation.",             29, null,"☀️",null,   "popular", 4.9, 2100, beauty);
        p("Niacinamide Serum 10%",             "Minimizes pores and controls excess oil production.",                     15, null,"💊","New",  "new",     4.8, 3200, beauty);
        p("Perfume Floral Oud 50ml",           "Long-lasting oriental floral with rose, oud, and amber.",                 89, 119,"🌹","Sale", "sale",    4.7,  560, beauty);
        p("Mascara Volumizing Waterproof",     "Clump-free lash-building formula that lasts 24 hours.",                   19, null,"👁️",null,  "popular", 4.6, 1800, beauty);
        p("Curling Wand Ceramic 1\"",          "Ceramic tourmaline barrel for shiny, frizz-free curls.",                  49,  69,"💈","Sale", "sale",    4.6,  480, beauty);
        p("Sheet Mask Bundle x20",             "20 different formulas for brightening, hydrating, and firming.",          25, null,"🧖","New",  "new",     4.7, 1400, beauty);
        p("AHA/BHA Exfoliating Toner",         "8% AHA + 2% BHA for smooth, clear, glowing skin.",                       32,  45,"🧪","Sale", "sale",    4.8,  900, beauty);
        p("Nail Polish Set 12 Colors",         "Long-wear gel-like formula, quick dry, chip resistant.",                  29, null,"💅","New",  "new",     4.5,  760, beauty);
        p("Eyebrow Pencil Microblading",       "Hair-stroke precision tip for natural brow filling.",                     18, null,"✏️",null,   "popular", 4.7, 1200, beauty);
        p("Under Eye Patches 60-Pack",         "Collagen and caffeine patches for dark circles and puffiness.",           22,  30,"👁️","Sale","sale",    4.6,  880, beauty);
        p("Face Mist Rosewater Spray",         "Pure Bulgarian rosewater to hydrate and set makeup.",                     19, null,"🌸",null,   "popular", 4.7, 1100, beauty);
        p("Teeth Whitening Strips 28-Pack",    "14-day treatment for up to 25 shades whiter.",                            35,  49,"😁","Sale", "sale",    4.5,  640, beauty);
        p("Hair Growth Serum 60ml",            "Biotin and caffeine formula to stimulate follicles.",                     45, null,"💆","New",  "new",     4.4,  380, beauty);
        p("Lip Liner Set 6 Shades",            "Creamy formula that lasts all day without feathering.",                   20, null,"💄","New",  "new",     4.5,  420, beauty);

        // ---- BOOKS ----
        p("Atomic Habits",                     "Tiny changes, remarkable results. Transform your habits forever.",         18, null,"📖",null,   "popular", 4.9,45000, books);
        p("The Alchemist",                     "A magical journey to pursue your personal legend.",                        14, null,"📗",null,   "popular", 4.8,38000, books);
        p("Dune",                              "The greatest sci-fi novel of all time. Epic and timeless.",               16,  22,"🏜️","Sale","sale",    4.9,29000, books);
        p("Psychology of Money",               "Timeless lessons on wealth, greed, and happiness.",                       19, null,"💰",null,   "popular", 4.8,22000, books);
        p("Think and Grow Rich",               "The original success philosophy that has shaped millions.",               12, null,"🧠",null,   "popular", 4.7,18000, books);
        p("The 48 Laws of Power",              "Distilled wisdom from history's most powerful figures.",                  22,  29,"👑","Sale", "sale",    4.7,16000, books);
        p("Educated — Tara Westover",          "A jaw-dropping memoir about knowledge and family.",                       15, null,"🎓",null,   "popular", 4.8,14000, books);
        p("Rich Dad Poor Dad",                 "What the rich teach their kids about money.",                             14, null,"🏦",null,   "popular", 4.7,32000, books);
        p("1984 — George Orwell",              "A haunting vision of a totalitarian future.",                             13, null,"📕",null,   "popular", 4.9,41000, books);
        p("The Great Gatsby",                  "The quintessential American novel of the Jazz Age.",                      11, null,"✨",null,   "popular", 4.6,28000, books);
        p("Deep Work",                         "Rules for focused success in a distracted world.",                        18,  24,"🧘","Sale", "sale",    4.8,12000, books);
        p("Ikigai",                            "The Japanese secret to a long and happy life.",                           16, null,"🌸","New",  "new",     4.7, 9800, books);
        p("Zero to One",                       "Notes on startups, or how to build the future.",                          20, null,"🚀",null,   "popular", 4.7,11000, books);
        p("The 5 AM Club",                     "Own your morning, elevate your life.",                                    17,  22,"🌅","Sale", "sale",    4.6, 8900, books);
        p("Sapiens",                           "A brief history of humankind. Mind-expanding.",                           19, null,"🌍",null,   "popular", 4.8,24000, books);
        p("Man's Search for Meaning",          "Psychiatrist's powerful account of surviving Auschwitz.",                 13, null,"🕊️",null,  "popular", 4.9,19000, books);
        p("The 7 Habits",                      "Highly effective principles for personal and professional life.",         18,  24,"🔄","Sale", "sale",    4.8,17000, books);
        p("Start with Why",                    "How great leaders inspire everyone to take action.",                      17, null,"❓","New",  "new",     4.7,13000, books);
        p("The Power of Now",                  "A guide to spiritual enlightenment and presence.",                        16, null,"⏰",null,   "popular", 4.7,21000, books);
        p("Meditations — Marcus Aurelius",     "Stoic wisdom from Rome's philosopher-emperor.",                           12, null,"🏛️",null,  "popular", 4.9,15000, books);

        // ---- FOOD & DRINKS ----
        p("Organic Matcha Powder 100g",        "Ceremonial grade from Uji, Japan. Earthy and smooth.",                   29, null,"🍵",null,   "popular", 4.8, 1400, food);
        p("Cold Brew Coffee Kit",              "Includes ground coffee and reusable steeper for 1L batches.",             35,  45,"☕","Sale", "sale",    4.7,  820, food);
        p("Raw Manuka Honey MGO 400+",         "Certified MGO 400+ from New Zealand, antibacterial grade.",              49, null,"🍯",null,   "popular", 4.9,  960, food);
        p("Dark Chocolate Box 85% Cacao",      "Single-origin cacao bars with notes of coffee and fruit.",               25, null,"🍫","New",  "new",     4.8, 1100, food);
        p("Himalayan Pink Salt 1kg",           "Coarse grind with trace minerals, perfect for cooking.",                  15,  22,"🧂","Sale", "sale",    4.6,  640, food);
        p("Protein Powder Whey Vanilla 2lb",   "25g protein per serving, grass-fed whey, no fillers.",                   45,  59,"💪","Sale", "sale",    4.7, 2300, food);
        p("Organic Quinoa Grain 2kg",          "Complete protein superfood, pre-washed and ready to cook.",               22, null,"🌾",null,   "popular", 4.7,  480, food);
        p("Extra Virgin Olive Oil 1L",         "Cold-pressed from Calabrian groves, harvest dated.",                      29, null,"🫒","New",  "new",     4.8,  780, food);
        p("Herbal Tea Collection 60 Bags",     "6 premium blends including chamomile, peppermint, and hibiscus.",         19, null,"🌿",null,   "popular", 4.7, 1200, food);
        p("Mixed Nuts Snack Pack 1kg",         "Roasted cashews, almonds, walnuts, and pecans, lightly salted.",          25,  35,"🥜","Sale", "sale",    4.7,  860, food);
        p("Coconut Oil Organic 500ml",         "Virgin cold-pressed, ideal for cooking and skin care.",                   18, null,"🥥",null,   "popular", 4.6,  540, food);
        p("Apple Cider Vinegar Raw 1L",        "Unfiltered with mother for maximum health benefits.",                     16,  22,"🍎","Sale", "sale",    4.7, 1800, food);
        p("Collagen Peptides Powder 400g",     "Hydrolyzed bovine collagen types I & III, flavorless.",                  39, null,"✨","New",  "new",     4.8, 1500, food);
        p("Granola Clusters Almond & Berry",   "Low sugar, high fiber clusters baked with real fruits.",                  14, null,"🫐","New",  "new",     4.6,  380, food);
        p("Hot Sauce Collection 5-Pack",       "From mild to nuclear, 5 artisan sauces for heat lovers.",                 35, null,"🌶️","New", "new",     4.8,  620, food);
        p("Dried Fruit Mix Premium 500g",      "Mango, pineapple, cranberry, and more, no added sugar.",                  19,  25,"🍇","Sale", "sale",    4.5,  290, food);
        p("Natural Peanut Butter Crunchy 500g","Only roasted peanuts and salt. No palm oil.",                             12, null,"🥜",null,   "popular", 4.7, 2100, food);
        p("Green Superfood Powder 300g",       "40+ organic greens, probiotics, and digestive enzymes.",                  45,  59,"🥦","Sale", "sale",    4.6,  480, food);
        p("Oat Milk Barista Edition 6-Pack",   "Barista formula that froths perfectly for lattes.",                       18, null,"🥛","New",  "new",     4.7,  740, food);
        p("Turmeric Latte Blend 200g",         "Golden milk blend with black pepper for absorption.",                     22,  29,"🌟","Sale", "sale",    4.5,  360, food);
        p("Sea Salt Caramel Popcorn 400g",     "Kettle-popped with real caramel and flaky sea salt.",                     11, null,"🍿","New",  "new",     4.8,  930, food);

        // ---- TOYS & GAMES ----
        p("LEGO Technic Race Car 560pcs",      "Working steering and detailed engine for ages 10+.",                      79,  99,"🏎️","Sale","sale",    4.9, 2800, toys);
        p("Remote Control Monster Truck",      "2.4GHz, 45km/h top speed, all-terrain tires.",                           59, null,"🚗","New",  "new",     4.7, 1200, toys);
        p("Catan Board Game",                  "The classic strategy game for 3-4 players, ages 10+.",                   45,  59,"🎲","Sale", "sale",    4.9, 5400, toys);
        p("Puzzle 1000 Pieces World Map",      "Educational world map puzzle with glossy finish.",                        25, null,"🗺️",null,  "popular", 4.8,  960, toys);
        p("Kids Drawing Tablet 10\"",          "Pressure-sensitive stylus, 50,000 erasures battery life.",                35,  49,"✏️","Sale", "sale",    4.7,  640, toys);
        p("Magnetic Tiles Building Set 100pcs","STEM-certified creative building tiles with magnet edges.",               65, null,"🧲",null,   "popular", 4.9, 1800, toys);
        p("Uno Card Game Classic",             "Classic card game for 2-10 players, all ages.",                           12, null,"🃏",null,   "popular", 4.8, 7800, toys);
        p("Chess Set Wooden Deluxe",           "Solid wood board with weighted pieces and storage box.",                  55,  75,"♟️","Sale", "sale",    4.8,  840, toys);
        p("Nerf Blaster Elite 2.0",            "12-dart clip, 27m range, tactical customizable design.",                  39, null,"🔫","New",  "new",     4.6, 1400, toys);
        p("Slime Making Kit 50-Piece",         "Safe non-toxic ingredients for 50+ slime creations.",                    29, null,"🟢","New",  "new",     4.7,  920, toys);
        p("Rubik's Cube Speed Stickerless",    "Smooth corner cutting and strong magnets for speed solving.",             15, null,"🎲",null,   "popular", 4.8, 3200, toys);
        p("Stuffed Animal Elephant XL",        "Super soft plush, hypoallergenic fill, 18 inches tall.",                  35,  45,"🐘","Sale", "sale",    4.9, 1100, toys);
        p("Science Experiment Kit Kids",       "30 STEM experiments with real chemistry equipment.",                      45, null,"🔬","New",  "new",     4.8,  760, toys);
        p("Bluetooth Karaoke Microphone",      "Echo & reverb effects, connects to any speaker or TV.",                   39,  55,"🎤","Sale", "sale",    4.6,  580, toys);
        p("Mini Foosball Table",               "Tabletop foosball with metal rods and score counters.",                   89, 119,"⚽","Sale", "sale",    4.7,  340, toys);
        p("Glow in the Dark Stars 200pcs",     "Ceiling stars that glow bright for 8 hours after light.",                 12, null,"⭐","New",  "new",     4.7, 1600, toys);
        p("Hot Wheels 20-Car Gift Set",        "20 unique die-cast vehicles, ages 3+.",                                   25, null,"🏎️",null,  "popular", 4.8, 2100, toys);
        p("Watercolor Paint Set 48 Colors",    "Professional-grade pigments for kids and beginners.",                     29, null,"🎨","New",  "new",     4.7,  680, toys);
        p("Jenga Classic Giant Outdoor",       "54 jumbo blocks, stackable up to 5 feet tall.",                           49,  69,"🧱","Sale", "sale",    4.8,  490, toys);
        p("Play-Doh Mega Fun Factory",         "10 cans with 20+ tools and molds for ultimate creativity.",               35, null,"🪀","New",  "new",     4.7, 1300, toys);
        p("Walkie Talkie Set for Kids",        "5km range, rechargeable, weather-resistant design.",                      35,  49,"📻","Sale", "sale",    4.6,  540, toys);
        p("Exploding Kittens Card Game",       "Hilarious strategic card game for 2-5 players.",                          20, null,"🐱",null,   "popular", 4.8, 4200, toys);

        System.out.println("✅ Database seeded successfully with all categories and products!");
    }

    private Category cat(String name, String slug, String icon, String desc) {
        if (categoryRepo.existsBySlug(slug)) return categoryRepo.findBySlug(slug).get();
        return categoryRepo.save(new Category(name, slug, icon, desc));
    }

    private void p(String name, String desc, int price, Integer oldPrice,
                   String emoji, String badge, String tag,
                   double rating, int reviews, Category cat) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(BigDecimal.valueOf(price));
        product.setOldPrice(oldPrice != null ? BigDecimal.valueOf(oldPrice) : null);
        product.setEmoji(emoji);
        product.setBadge(badge);
        product.setTag(tag);
        product.setRating(rating);
        product.setReviewCount(reviews);
        product.setCategory(cat);
        product.setStock(100);
        productRepo.save(product);
    }
}
