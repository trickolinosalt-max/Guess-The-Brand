package com.example.model

import com.example.R
import java.util.Calendar

object BrandCatalog {
    val allBrands: List<BrandItem> = listOf(
        BrandItem(
            id = 1,
            name = "APPLE",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_apple,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1976 by Steve Jobs, Steve Wozniak, and Ronald Wayne. The famous bite prevents it from being confused with a cherry!",
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
            name = "BACARDI",
            category = "BEVERAGES",
            logoRes = R.drawable.logo_arcade,
            difficulty = Difficulty.MEDIUM,
            funFact = "Founded in Cuba in 1862. The iconic fruit bat symbol was adopted because bats inhabited the distillery rafters!",
            levelNumber = 1,
            hint = "World famous spirits company with the iconic red arch letters and bat logo"
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
            funFact = "Founded in 1886 by Royal Arsenal munition workers in Woolwich, hence the nickname 'The Gunners' and cannon emblem!",
            levelNumber = 1,
            hint = "Premier League football club from North London"
        ),
        BrandItem(
            id = 6,
            name = "ARTEGA",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_artega,
            difficulty = Difficulty.HARD,
            funFact = "German sports car manufacturer from Delbrück founded in 2006, acclaimed for the lightweight Artega GT coupe.",
            levelNumber = 2,
            hint = "German boutique supercar manufacturer with berry tree & rampant hound crest"
        ),
        BrandItem(
            id = 7,
            name = "AIR ASIA",
            category = "AIRLINES",
            logoRes = R.drawable.logo_asiaairlines,
            difficulty = Difficulty.MEDIUM,
            funFact = "Pioneered low-cost air travel in Southeast Asia with the motto 'Now Everyone Can Fly'.",
            levelNumber = 2,
            hint = "Leading low-cost international carrier airline"
        ),
        BrandItem(
            id = 8,
            name = "ASICS",
            category = "SPORTSWEAR",
            logoRes = R.drawable.logo_asics,
            difficulty = Difficulty.EASY,
            funFact = "Acronym for the Latin phrase 'Anima Sana In Corpore Sano' — 'A Sound Mind in a Sound Body'!",
            levelNumber = 2,
            hint = "Japanese performance athletic footwear & running gear brand"
        ),
        BrandItem(
            id = 9,
            name = "ASTON MARTIN",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_astonmartin,
            difficulty = Difficulty.MEDIUM,
            funFact = "British luxury grand tourer maker founded in 1913. Synonymous with secret agent James Bond since Goldfinger in 1964!",
            levelNumber = 2,
            hint = "British luxury supercar manufacturer with iconic winged badge"
        ),
        BrandItem(
            id = 10,
            name = "ASUS",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_asus,
            difficulty = Difficulty.EASY,
            funFact = "Named after Pegasus, the winged stallion of Greek mythology embodying creativity and wisdom.",
            levelNumber = 2,
            hint = "Taiwanese PC, motherboard, and ROG gaming hardware giant"
        ),
        BrandItem(
            id = 11,
            name = "NIKE",
            category = "SPORTSWEAR",
            logoRes = R.drawable.logo_nike,
            difficulty = Difficulty.EASY,
            funFact = "Named after the Greek goddess of victory. The iconic Swoosh was designed in 1971 for just $35!",
            levelNumber = 3,
            hint = "Just Do It — world's largest athletic apparel brand"
        ),
        BrandItem(
            id = 12,
            name = "STARBUCKS",
            category = "FOOD & DRINK",
            logoRes = R.drawable.logo_starbucks,
            difficulty = Difficulty.EASY,
            funFact = "Founded in 1971 at Pike Place Market in Seattle. The twin-tailed siren is inspired by a 16th-century Norse woodcut.",
            levelNumber = 3,
            hint = "Global coffeehouse chain with the green siren emblem"
        ),
        BrandItem(
            id = 13,
            name = "PEPSI",
            category = "BEVERAGES",
            logoRes = R.drawable.logo_pepsi,
            difficulty = Difficulty.EASY,
            funFact = "Invented in 1893 by Caleb Bradham as 'Brad's Drink' before being renamed Pepsi-Cola in 1898.",
            levelNumber = 3,
            hint = "Iconic cola brand with the red, white, and blue globe"
        ),
        BrandItem(
            id = 14,
            name = "GOOGLE",
            category = "TECHNOLOGY",
            logoRes = R.drawable.logo_google,
            difficulty = Difficulty.EASY,
            funFact = "Originally named 'Backrub'. The name 'Google' originated from a misspelling of the mathematical number 'googol' (1 followed by 100 zeros)!",
            levelNumber = 3,
            hint = "Search engine giant behind Android, Chrome, and YouTube"
        ),
        BrandItem(
            id = 15,
            name = "FERRARI",
            category = "AUTOMOTIVE",
            logoRes = R.drawable.logo_ferrari,
            difficulty = Difficulty.MEDIUM,
            funFact = "Founded in 1939 by Enzo Ferrari in Maranello, Italy. The prancing horse was the symbol of WWI Italian flying ace Francesco Baracca.",
            levelNumber = 3,
            hint = "Legendary Italian supercar & Scuderia Formula 1 team"
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

    val categories: List<String> = allBrands.map { it.category }.distinct()
}
