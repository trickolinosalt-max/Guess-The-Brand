package com.example.model

import com.example.R
import java.util.Calendar

object BrandCatalog {
    val allBrands: List<BrandItem> = listOf(
        // LEVEL 1: ESSENTIAL & TECH BRANDS (1-10)
        BrandItem(
            id = 1,
            name = "APPLE",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_apple,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1976 by Steve Jobs, Steve Wozniak, and Ronald Wayne. The bite prevents it from being confused with a cherry!",
            levelNumber = 1,
            hint = "Creator of the iPhone, Mac, and iPad"
        ),
        BrandItem(
            id = 2,
            name = "AQUAFINA",
            category = "BEVERAGES",
            logoRes = R.drawable.logo_aquafina,
            difficulty = Difficulty.MEDIUM,
            funFact = "Pure water, perfect taste! Originating in Wichita, Kansas in 1994, it became America's top bottled water brand.",
            levelNumber = 1,
            hint = "Famous bottled water brand produced by PepsiCo"
        ),
        BrandItem(
            id = 3,
            name = "ARCADE",
            category = "GAMING",
            logoRes = R.drawable.logo_arcade,
            difficulty = Difficulty.MEDIUM,
            funFact = "Classic amusement arcade gaming center where legendary games like Pac-Man and Space Invaders began!",
            levelNumber = 1,
            hint = "Classic coin-operated video gaming hall emblem"
        ),
        BrandItem(
            id = 4,
            name = "ARIEL",
            category = "HOUSEHOLD",
            logoRes = R.drawable.logo_ariel,
            difficulty = Difficulty.EASY,
            funFact = "Developed by Procter & Gamble in 1967. Famous for its vibrant atomic orbital cleaning swirl icon.",
            levelNumber = 1,
            hint = "Leading global laundry detergent brand with atomic swirl"
        ),
        BrandItem(
            id = 5,
            name = "ARSENAL",
            category = "SPORTS",
            logoRes = R.drawable.logo_arsenal,
            difficulty = Difficulty.MEDIUM,
            funFact = "Founded in 1886 by Royal Arsenal munition workers in Woolwich, hence the nickname 'The Gunners'!",
            levelNumber = 1,
            hint = "Premier League football club from North London"
        ),
        BrandItem(
            id = 6,
            name = "ADIDAS",
            category = "SPORTSWEAR",
            logoRes = R.drawable.logo_adidas,
            difficulty = Difficulty.EASY,
            funFact = "German athletic shoe and apparel brand with the iconic three stripes, founded by Adi Dassler in 1949.",
            levelNumber = 1,
            hint = "Global sportswear giant with the three slanted mountain stripes"
        ),
        BrandItem(
            id = 7,
            name = "ADOBE",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_adobe,
            difficulty = Difficulty.EASY,
            funFact = "Named after Adobe Creek in Los Altos, California, which ran behind co-founder John Warnock's house.",
            levelNumber = 1,
            hint = "Creative software giant behind Photoshop and PDF"
        ),
        BrandItem(
            id = 8,
            name = "AMAZON",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_amazon,
            difficulty = Difficulty.EASY,
            funFact = "The orange smile arrow stretches from 'A' to 'Z', symbolizing that they carry everything from A to Z!",
            levelNumber = 1,
            hint = "World's largest e-commerce and cloud platform"
        ),
        BrandItem(
            id = 9,
            name = "AMD",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_amd,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1969 in Silicon Valley. Known worldwide for Ryzen processors and Radeon graphics cards.",
            levelNumber = 1,
            hint = "Advanced Micro Devices — major CPU and GPU semiconductor maker"
        ),
        BrandItem(
            id = 10,
            name = "ACER",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_acer,
            difficulty = Difficulty.EASY,
            funFact = "Originally named Multitech when founded in 1976 before rebranding to Acer in 1987.",
            levelNumber = 1,
            hint = "Taiwanese PC, monitor, and laptop manufacturer"
        ),

        // LEVEL 2: AUTOMOTIVE & PERFORMANCE BRANDS (11-20)
        BrandItem(
            id = 11,
            name = "ABARTH",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_abarth,
            difficulty = Difficulty.MEDIUM,
            funFact = "Founded by Carlo Abarth in Bologna, Italy in 1949. His astrological sign was Scorpio, inspiring the iconic scorpion badge!",
            levelNumber = 2,
            hint = "Italian performance sports brand famed for tuned Fiat 500s"
        ),
        BrandItem(
            id = 12,
            name = "ACURA",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_acura,
            difficulty = Difficulty.MEDIUM,
            funFact = "Honda launched Acura in 1986 as the first luxury division from a Japanese automaker, preceding Lexus and Infiniti.",
            levelNumber = 2,
            hint = "Japanese luxury performance division of Honda with caliper logo"
        ),
        BrandItem(
            id = 13,
            name = "ALFA ROMEO",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_alfaromeo,
            difficulty = Difficulty.MEDIUM,
            funFact = "The logo merges the red cross of Milan and the legendary crowned green Visconti serpent (Biscione).",
            levelNumber = 2,
            hint = "Historic Italian sports car maker from Milan"
        ),
        BrandItem(
            id = 14,
            name = "ALPINA",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_alpina,
            difficulty = Difficulty.HARD,
            funFact = "Boutique German manufacturer that works closely with BMW to produce ultra-high performance grand tourers.",
            levelNumber = 2,
            hint = "German bespoke tuner with carburetor and crankshaft crest"
        ),
        BrandItem(
            id = 15,
            name = "ALPINE",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_alpine,
            difficulty = Difficulty.MEDIUM,
            funFact = "French sports racing car manufacturer founded in 1955 by Jean Rédélé, famed for winning the World Rally Championship.",
            levelNumber = 2,
            hint = "French sports car and Formula 1 team maker of the A110"
        ),
        BrandItem(
            id = 16,
            name = "ARTEGA",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_artega,
            difficulty = Difficulty.HARD,
            funFact = "German sports car manufacturer from Delbrück founded in 2006, acclaimed for the Henrik Fisker-designed Artega GT.",
            levelNumber = 2,
            hint = "German boutique supercar brand with berry tree & rampant hound"
        ),
        BrandItem(
            id = 17,
            name = "ASTON MARTIN",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_astonmartin,
            difficulty = Difficulty.MEDIUM,
            funFact = "British luxury grand tourer maker founded in 1913. Synonymous with secret agent James Bond since Goldfinger in 1964!",
            levelNumber = 2,
            hint = "British luxury supercar manufacturer with iconic winged badge"
        ),
        BrandItem(
            id = 18,
            name = "ASUS",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_asus,
            difficulty = Difficulty.EASY,
            funFact = "Named after Pegasus, the winged stallion of Greek mythology embodying creativity and wisdom.",
            levelNumber = 2,
            hint = "Taiwanese PC, motherboard, and ROG gaming hardware giant"
        ),
        BrandItem(
            id = 19,
            name = "ASICS",
            category = "SPORTSWEAR",
            logoRes = R.drawable.logo_asics,
            difficulty = Difficulty.EASY,
            funFact = "Acronym for the Latin phrase 'Anima Sana In Corpore Sano' — 'A Sound Mind in a Sound Body'!",
            levelNumber = 2,
            hint = "Japanese performance athletic footwear & running gear brand"
        ),
        BrandItem(
            id = 20,
            name = "AIR JORDAN",
            category = "SPORTSWEAR",
            logoRes = R.drawable.logo_airjordan,
            difficulty = Difficulty.EASY,
            funFact = "The Jumpman logo was created from a photoshoot of Michael Jordan executing a ballet jump move, not a slam dunk!",
            levelNumber = 2,
            hint = "Iconic Nike basketball line named after Michael Jordan"
        ),

        // LEVEL 3: AVIATION & AIRLINES (21-30)
        BrandItem(
            id = 21,
            name = "AIR ASIA",
            category = "AIRLINES",
            logoRes = R.drawable.logo_asiaairlines,
            difficulty = Difficulty.MEDIUM,
            funFact = "Pioneered low-cost air travel in Southeast Asia with the motto 'Now Everyone Can Fly'.",
            levelNumber = 3,
            hint = "Leading Asian low-cost airline with red arrow logo"
        ),
        BrandItem(
            id = 22,
            name = "AIR BERLIN",
            category = "AIRLINES",
            logoRes = R.drawable.logo_airberlin,
            difficulty = Difficulty.MEDIUM,
            funFact = "At its peak, Air Berlin was Germany's second-largest airline and carried over 31 million passengers annually.",
            levelNumber = 3,
            hint = "Former German airline connecting European holiday destinations"
        ),
        BrandItem(
            id = 23,
            name = "AIRBUS",
            category = "AVIATION",
            logoRes = R.drawable.logo_airbus,
            difficulty = Difficulty.EASY,
            funFact = "European aerospace consortium that created the A380, the world's largest commercial passenger jet airliner.",
            levelNumber = 3,
            hint = "European aircraft manufacturing giant and rival of Boeing"
        ),
        BrandItem(
            id = 24,
            name = "AIR CANADA",
            category = "AIRLINES",
            logoRes = R.drawable.logo_aircanada,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1937 as Trans-Canada Air Lines, operating out of Montreal-Trudeau International Airport.",
            levelNumber = 3,
            hint = "Flag carrier of Canada with the red maple leaf rondelle"
        ),
        BrandItem(
            id = 25,
            name = "AIR FRANCE",
            category = "AIRLINES",
            logoRes = R.drawable.logo_airfrance,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1933 and was one of only two airlines to operate the supersonic Concorde passenger airliner.",
            levelNumber = 3,
            hint = "National flag carrier airline of France"
        ),
        BrandItem(
            id = 26,
            name = "AIR JAMAICA",
            category = "AIRLINES",
            logoRes = R.drawable.logo_airjamaica,
            difficulty = Difficulty.MEDIUM,
            funFact = "National airline of Jamaica featuring the yellow Doctor Bird (Trochilus polytmus), Jamaica's national bird.",
            levelNumber = 3,
            hint = "Caribbean airline featuring the yellow hummingbird"
        ),
        BrandItem(
            id = 27,
            name = "AIR NEW ZEALAND",
            category = "AIRLINES",
            logoRes = R.drawable.logo_airnewzealand,
            difficulty = Difficulty.MEDIUM,
            funFact = "Features the Māori Koru, a spiral shape based on an unfolding silver fern frond symbolizing new life and growth.",
            levelNumber = 3,
            hint = "National carrier of New Zealand with the Koru symbol"
        ),
        BrandItem(
            id = 28,
            name = "AEROFLOT",
            category = "AIRLINES",
            logoRes = R.drawable.logo_aeroflot,
            difficulty = Difficulty.MEDIUM,
            funFact = "One of the oldest airlines in the world, founded in 1923, retaining its iconic winged hammer and sickle emblem.",
            levelNumber = 3,
            hint = "Russian flag carrier airline with winged hammer and sickle"
        ),
        BrandItem(
            id = 29,
            name = "ALITALIA",
            category = "AIRLINES",
            logoRes = R.drawable.logo_alitalia,
            difficulty = Difficulty.MEDIUM,
            funFact = "Historic flag carrier of Italy famed for its Landor-designed green and red stylized 'A' aircraft tail fin.",
            levelNumber = 3,
            hint = "Historic Italian airline with the green and red A tail"
        ),
        BrandItem(
            id = 30,
            name = "AIRWALK",
            category = "SPORTSWEAR",
            logoRes = R.drawable.logo_airwalk,
            difficulty = Difficulty.MEDIUM,
            funFact = "Founded in Carlsbad, California in 1986 by George Yohn, becoming a cornerstone of 90s skate and snowboard culture.",
            levelNumber = 3,
            hint = "California skate shoes and action sports lifestyle brand"
        ),

        // LEVEL 4: MUSIC, MEDIA & GAMING (31-42)
        BrandItem(
            id = 31,
            name = "ABBA",
            category = "MUSIC",
            logoRes = R.drawable.logo_abba,
            difficulty = Difficulty.EASY,
            funFact = "Formed in Stockholm in 1972 by Agnetha, Björn, Benny, and Anni-Frid. The reversed 'B' was adopted in 1976.",
            levelNumber = 4,
            hint = "Swedish pop sensation behind Mamma Mia and Dancing Queen"
        ),
        BrandItem(
            id = 32,
            name = "ACDC",
            category = "MUSIC",
            logoRes = R.drawable.logo_acdc,
            difficulty = Difficulty.EASY,
            funFact = "Named after seeing the abbreviation 'AC/DC' on their sister Margaret's sewing machine, meaning 'alternating current/direct current'.",
            levelNumber = 4,
            hint = "Australian rock band with the iconic lightning bolt logo"
        ),
        BrandItem(
            id = 33,
            name = "ADELE",
            category = "MUSIC",
            logoRes = R.drawable.logo_adele,
            difficulty = Difficulty.EASY,
            funFact = "Her record-breaking album '21' became the best-selling album of the 21st century with over 31 million copies sold.",
            levelNumber = 4,
            hint = "British soul and pop megastar who sang Skyfall and Rolling in the Deep"
        ),
        BrandItem(
            id = 34,
            name = "AEROSMITH",
            category = "MUSIC",
            logoRes = R.drawable.logo_aerosmith,
            difficulty = Difficulty.MEDIUM,
            funFact = "With over 150 million records sold worldwide, they are the best-selling American hard rock band of all time.",
            levelNumber = 4,
            hint = "American rock band fronted by Steven Tyler with winged emblem"
        ),
        BrandItem(
            id = 35,
            name = "ACTIVISION",
            category = "GAMING",
            logoRes = R.drawable.logo_activision,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1979 by former Atari game designers, becoming the world's very first independent third-party console video game developer.",
            levelNumber = 4,
            hint = "Video game titan publisher of Call of Duty"
        ),
        BrandItem(
            id = 36,
            name = "ALIENWARE",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_alienware,
            difficulty = Difficulty.EASY,
            funFact = "Founded in Miami in 1996 and inspired by hit sci-fi shows like The X-Files, which gave rise to their iconic extraterrestrial emblem.",
            levelNumber = 4,
            hint = "High-end gaming computer brand with alien head logo"
        ),
        BrandItem(
            id = 37,
            name = "AL JAZEERA",
            category = "MEDIA",
            logoRes = R.drawable.logo_aljazeera,
            difficulty = Difficulty.MEDIUM,
            funFact = "The gold emblem is written in decorative Arabic calligraphy depicting the station's name in the shape of a drop of water.",
            levelNumber = 4,
            hint = "International news broadcaster headquartered in Qatar"
        ),
        BrandItem(
            id = 38,
            name = "ABC",
            category = "MEDIA",
            logoRes = R.drawable.logo_abc,
            difficulty = Difficulty.EASY,
            funFact = "The legendary minimalist circular Bauhaus logo was designed in 1962 by graphic design icon Paul Rand.",
            levelNumber = 4,
            hint = "American television network inside a black circle"
        ),
        BrandItem(
            id = 39,
            name = "AJAX",
            category = "SPORTS",
            logoRes = R.drawable.logo_ajax,
            difficulty = Difficulty.MEDIUM,
            funFact = "Named after the mythological Greek hero Ajax. The club's crest is composed of exactly 11 lines, representing the 11 players on the pitch!",
            levelNumber = 4,
            hint = "Historic Dutch football club from Amsterdam"
        ),
        BrandItem(
            id = 40,
            name = "ADIO",
            category = "SPORTSWEAR",
            logoRes = R.drawable.logo_adio,
            difficulty = Difficulty.HARD,
            funFact = "Founded in 1998 by former pro skateboarder Chris Miller, sponsoring legendary riders like Tony Hawk and Bam Margera.",
            levelNumber = 4,
            hint = "Skateboarding shoe company with wave swoop and AD"
        ),
        BrandItem(
            id = 41,
            name = "AIRNESS",
            category = "SPORTSWEAR",
            logoRes = R.drawable.logo_airness,
            difficulty = Difficulty.HARD,
            funFact = "French sports brand founded by boxer Malamine Koné in 1999, famous for kit deals across top European football clubs.",
            levelNumber = 4,
            hint = "French sportswear brand with the crouching black panther"
        ),
        BrandItem(
            id = 42,
            name = "AGV",
            category = "MOTORCYCLE",
            logoRes = R.drawable.logo_agv,
            difficulty = Difficulty.MEDIUM,
            funFact = "Stands for 'Amisano Gino Valenza'. Famous for outfitting MotoGP legend Valentino Rossi throughout his nine world championship titles.",
            levelNumber = 4,
            hint = "Italian racing helmet maker with Italian tricolor arch"
        ),

        // LEVEL 5: GLOBAL ENTERPRISE & LIFESTYLE (43-55)
        BrandItem(
            id = 43,
            name = "ABSOLUT",
            category = "BEVERAGES",
            logoRes = R.drawable.logo_absolut,
            difficulty = Difficulty.EASY,
            funFact = "Produced exclusively in the village of Åhus in southern Sweden using locally sourced winter wheat.",
            levelNumber = 5,
            hint = "Premium Swedish vodka known for artistic bottle campaigns"
        ),
        BrandItem(
            id = 44,
            name = "ABB",
            category = "ENGINEERING",
            logoRes = R.drawable.logo_abb,
            difficulty = Difficulty.MEDIUM,
            funFact = "Formed in 1988 by the merger of Sweden's ASEA and Switzerland's Brown, Boveri & Cie, pioneering robotics and grid power.",
            levelNumber = 5,
            hint = "Swiss-Swedish robotics and power grid automation giant"
        ),
        BrandItem(
            id = 45,
            name = "ABBOTT",
            category = "HEALTHCARE",
            logoRes = R.drawable.logo_abbott,
            difficulty = Difficulty.MEDIUM,
            funFact = "Founded in 1888 by Chicago physician Dr. Wallace Abbott, creating breakthroughs in diagnostics, nutrition, and cardiovascular care.",
            levelNumber = 5,
            hint = "Global healthcare and medical devices corporation"
        ),
        BrandItem(
            id = 46,
            name = "ADECCO",
            category = "SERVICES",
            logoRes = R.drawable.logo_adecco,
            difficulty = Difficulty.MEDIUM,
            funFact = "Headquartered in Zurich, Switzerland, it is the second-largest human resources provider and temporary staffing firm in the world.",
            levelNumber = 5,
            hint = "International staffing and recruitment agency with red box logo"
        ),
        BrandItem(
            id = 47,
            name = "AEGON",
            category = "FINANCE",
            logoRes = R.drawable.logo_aegon,
            difficulty = Difficulty.HARD,
            funFact = "Dutch multinational insurance and pension provider with roots dating back over 175 years to 1844.",
            levelNumber = 5,
            hint = "Dutch insurance and pension company with blue square A"
        ),
        BrandItem(
            id = 48,
            name = "AGFA",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_agfa,
            difficulty = Difficulty.MEDIUM,
            funFact = "Stands for 'Actien-Gesellschaft für Anilin-Fabrikation', historically one of Europe's largest photographic film suppliers.",
            levelNumber = 5,
            hint = "European photographic film and imaging company with red diamond"
        ),
        BrandItem(
            id = 49,
            name = "AGUSTA",
            category = "MOTORCYCLE",
            logoRes = R.drawable.logo_agusta,
            difficulty = Difficulty.HARD,
            funFact = "MV Agusta was founded by Count Domenico Agusta in 1945 and won 270 motorcycle Grand Prix races with legend Giacomo Agostini.",
            levelNumber = 5,
            hint = "Italian racing motorcycle firm with blue crown and winged MV"
        ),
        BrandItem(
            id = 50,
            name = "AIR WICK",
            category = "HOUSEHOLD",
            logoRes = R.drawable.logo_airwick,
            difficulty = Difficulty.EASY,
            funFact = "First introduced in the US in 1943, it was one of the first consumer odor eliminators designed for domestic use.",
            levelNumber = 5,
            hint = "Popular home fragrance and air freshener brand"
        ),
        BrandItem(
            id = 51,
            name = "AIWA",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_aiwa,
            difficulty = Difficulty.MEDIUM,
            funFact = "Founded in Tokyo in 1951, Aiwa created Japan's first cassette tape recorder in 1964.",
            levelNumber = 5,
            hint = "Japanese consumer electronics and audio speaker brand"
        ),
        BrandItem(
            id = 52,
            name = "ALCATEL",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_alcatel,
            difficulty = Difficulty.MEDIUM,
            funFact = "Pioneered early GSM mobile phones across Europe in the 1990s and 2000s with the One Touch series.",
            levelNumber = 5,
            hint = "Telecom and mobile handset brand with red downward triangle"
        ),
        BrandItem(
            id = 53,
            name = "ALLIANZ",
            category = "FINANCE",
            logoRes = R.drawable.logo_allianz,
            difficulty = Difficulty.EASY,
            funFact = "Munich-based insurance leader and naming partner of Munich's iconic color-changing football stadium, the Allianz Arena.",
            levelNumber = 5,
            hint = "German global insurance and asset management giant"
        ),
        BrandItem(
            id = 54,
            name = "ALTRIA",
            category = "CONGLOMERATE",
            logoRes = R.drawable.logo_altria,
            difficulty = Difficulty.HARD,
            funFact = "Rebranded in 2003 with a distinctive 25-color mosaic square designed by the brand agency Landor Associates.",
            levelNumber = 5,
            hint = "Fortune 500 corporation with the 25-color mosaic tile logo"
        ),
        BrandItem(
            id = 55,
            name = "ALWAYS",
            category = "PERSONAL CARE",
            logoRes = R.drawable.logo_always,
            difficulty = Difficulty.EASY,
            funFact = "Marketed as Always in the US and UK, Whisper in Asia, and Orkid in Turkey, created by Procter & Gamble in 1983.",
            levelNumber = 5,
            hint = "Global feminine hygiene brand with infinity ribbon"
        ),

        // LEVEL 6: COMPUTING, TELECOM & ENTERTAINMENT (56-65)
        BrandItem(
            id = 56,
            name = "ATARI",
            category = "GAMING",
            logoRes = R.drawable.logo_atari,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1972 by Nolan Bushnell and Ted Dabney. The legendary logo is affectionately called the 'Fuji' symbol!",
            levelNumber = 6,
            hint = "Pioneering video game and arcade company behind Pong & 2600"
        ),
        BrandItem(
            id = 57,
            name = "ATI",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_ati,
            difficulty = Difficulty.EASY,
            funFact = "Founded in Markham, Ontario in 1985. Created the Radeon GPU line before being acquired by AMD in 2006.",
            levelNumber = 6,
            hint = "Semiconductor firm famed for Radeon PC graphics cards"
        ),
        BrandItem(
            id = 58,
            name = "ATOMIC",
            category = "SPORTS",
            logoRes = R.drawable.logo_atomic,
            difficulty = Difficulty.MEDIUM,
            funFact = "Austrian ski manufacturer founded in 1955 by Alois Rohrmoser. World Cup champions like Mikaela Shiffrin ski on Atomic!",
            levelNumber = 6,
            hint = "Austrian alpine ski and winter sports equipment company"
        ),
        BrandItem(
            id = 59,
            name = "ATT",
            category = "TELECOM",
            logoRes = R.drawable.logo_att,
            difficulty = Difficulty.EASY,
            funFact = "Traces its lineage directly to Alexander Graham Bell and the Bell Telephone Company founded in 1877.",
            levelNumber = 6,
            hint = "Major American telecommunications giant with blue striped globe"
        ),
        BrandItem(
            id = 60,
            name = "AUDI",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_audi,
            difficulty = Difficulty.EASY,
            funFact = "The four rings represent the 1932 merger of four German car makers: Audi, DKW, Horch, and Wanderer under Auto Union.",
            levelNumber = 6,
            hint = "German luxury automaker famed for quattro all-wheel drive"
        ),
        BrandItem(
            id = 61,
            name = "AUDIOSLAVE",
            category = "MUSIC",
            logoRes = R.drawable.logo_audioslave,
            difficulty = Difficulty.MEDIUM,
            funFact = "Formed in 2001 featuring Soundgarden's Chris Cornell alongside Rage Against the Machine members Tom Morello, Tim Commerford, and Brad Wilk.",
            levelNumber = 6,
            hint = "Legendary hard rock supergroup with flaming silhouette logo"
        ),
        BrandItem(
            id = 62,
            name = "AUNTIE ANNES",
            category = "FOOD",
            logoRes = R.drawable.logo_auntieannes,
            difficulty = Difficulty.EASY,
            funFact = "Started by Anne Beiler at a Pennsylvania farmers market in 1988, famous worldwide for freshly baked soft pretzels.",
            levelNumber = 6,
            hint = "Popular mall pretzel chain with golden pretzel icon"
        ),
        BrandItem(
            id = 63,
            name = "AVG",
            category = "SOFTWARE",
            logoRes = R.drawable.logo_avg,
            difficulty = Difficulty.EASY,
            funFact = "Short for Anti-Virus Guard, founded in the Czech Republic in 1991 and popularized through its free antivirus security suite.",
            levelNumber = 6,
            hint = "Four-colored security and cybersecurity antivirus software"
        ),
        BrandItem(
            id = 64,
            name = "AVIVA",
            category = "FINANCE",
            logoRes = R.drawable.logo_aviva,
            difficulty = Difficulty.MEDIUM,
            funFact = "British multinational insurance company headquartered in London, tracing roots back to the Hand in Hand Fire & Life Insurance Society founded in 1696.",
            levelNumber = 6,
            hint = "British insurance and pensions giant with blue and yellow/green flag"
        ),
        BrandItem(
            id = 65,
            name = "AVON",
            category = "COSMETICS",
            logoRes = R.drawable.logo_avon,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1886 by David H. McConnell, who initially gave away fragrance samples to housewives to help sell books!",
            levelNumber = 6,
            hint = "Pioneer direct-selling cosmetics and beauty company"
        ),

        // LEVEL 7: GLOBAL ICONS & MEDIA (66-75)
        BrandItem(
            id = 66,
            name = "AXA",
            category = "FINANCE",
            logoRes = R.drawable.logo_axa,
            difficulty = Difficulty.EASY,
            funFact = "The name 'AXA' was chosen in 1985 because it is short, easy to pronounce in any language, and begins with the letter A for alphabetical listings.",
            levelNumber = 7,
            hint = "French multinational insurance and asset management giant"
        ),
        BrandItem(
            id = 67,
            name = "AXN",
            category = "ENTERTAINMENT",
            logoRes = R.drawable.logo_axn,
            difficulty = Difficulty.MEDIUM,
            funFact = "Action television channel brand owned by KC Global Media and Sony Pictures Television, broadcasting high-octane drama and blockbusters.",
            levelNumber = 7,
            hint = "Action and thrill entertainment cable TV network"
        ),
        BrandItem(
            id = 68,
            name = "AXE",
            category = "PERSONAL CARE",
            logoRes = R.drawable.logo_axe,
            difficulty = Difficulty.EASY,
            funFact = "Known as Lynx in the United Kingdom, Ireland, Australia, and New Zealand, this Unilever body spray brand was launched in 1983.",
            levelNumber = 7,
            hint = "Popular men's grooming and body spray fragrance brand"
        ),
        BrandItem(
            id = 69,
            name = "AXIAM",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_axiam,
            difficulty = Difficulty.HARD,
            funFact = "French quadricycle and microcar manufacturer based in Aix-les-Bains, producing light vehicles drivable without a standard full car license.",
            levelNumber = 7,
            hint = "French microcar and compact quadricycle vehicle maker"
        ),
        BrandItem(
            id = 70,
            name = "BABOLAT",
            category = "SPORTS",
            logoRes = R.drawable.logo_babolat,
            difficulty = Difficulty.MEDIUM,
            funFact = "The oldest company specializing in racket sports, founded in Lyon in 1875! Rafael Nadal played his entire professional career with Babolat rackets.",
            levelNumber = 7,
            hint = "Historic French tennis racket and strings manufacturer"
        ),
        BrandItem(
            id = 71,
            name = "BABOR",
            category = "COSMETICS",
            logoRes = R.drawable.logo_babor,
            difficulty = Difficulty.HARD,
            funFact = "German luxury skincare pioneer founded in 1956 by chemist Dr. Michael Babor in Aachen, renowned for inventing the precision beauty ampoule.",
            levelNumber = 7,
            hint = "German luxury spa skincare brand featuring a classic black rose"
        ),
        BrandItem(
            id = 72,
            name = "BACARDI",
            category = "BEVERAGES",
            logoRes = R.drawable.logo_bacardi,
            difficulty = Difficulty.EASY,
            funFact = "Founded in Santiago de Cuba in 1862 by Don Facundo Bacardí Massó. The fruit bat symbolizes good health, fortune, and family unity.",
            levelNumber = 7,
            hint = "World famous rum brand with the gold medallion and black bat"
        ),
        BrandItem(
            id = 73,
            name = "BAIDU",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_baidu,
            difficulty = Difficulty.EASY,
            funFact = "The name Baidu comes from an ancient Chinese Song dynasty poem about a persistent search for a lost beauty amongst crowds.",
            levelNumber = 7,
            hint = "Leading Chinese search engine and AI tech giant with blue paw"
        ),
        BrandItem(
            id = 74,
            name = "BAD ROBOT",
            category = "ENTERTAINMENT",
            logoRes = R.drawable.logo_badrobot,
            difficulty = Difficulty.MEDIUM,
            funFact = "J.J. Abrams' film and television production company behind Lost, Star Trek, Mission: Impossible, and Star Wars: The Force Awakens.",
            levelNumber = 7,
            hint = "J.J. Abrams' film company famous for the red robot in a field"
        ),
        BrandItem(
            id = 75,
            name = "BAD PIGGIES",
            category = "GAMING",
            logoRes = R.drawable.logo_badpiggies,
            difficulty = Difficulty.EASY,
            funFact = "Rovio's hit physics puzzle spin-off released in 2012 where players build contraptions for the green pigs to reach eggs!",
            levelNumber = 7,
            hint = "Rovio puzzle game starring the green pigs from Angry Birds"
        )
    )

    fun getBrandById(id: Int): BrandItem? = allBrands.find { it.id == id }

    fun getDailyChallengeBrand(): BrandItem {
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val index = (dayOfYear + 2) % allBrands.size
        return allBrands[index]
    }

    fun getBrandsByLevel(level: Int): List<BrandItem> {
        return allBrands.filter { it.levelNumber == level }
    }

    val totalLevels: Int = allBrands.maxOf { it.levelNumber }

    val categories: List<String> = allBrands.map { it.category }.distinct()
}
