package com.example.ictvesti.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException

class ICTVestiScraper {
    
    suspend fun fetchArticles(): List<Article> = withContext(Dispatchers.IO) {
        try {
            val doc = Jsoup.connect("https://www.ictvesti.com")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .timeout(10000)
                .get()

            val articles = mutableListOf<Article>()
            
            // Pronalazi sve članke na početnoj stranici
            val articleElements = doc.select("article, .post, [class*=article]")
            
            articleElements.forEach { element ->
                try {
                    // Naslov
                    val titleElement = element.select("h3 a, h2 a, .entry-title a").first()
                    val title = titleElement?.text()?.trim() ?: ""
                    val url = titleElement?.attr("abs:href") ?: ""
                    
                    if (title.isEmpty() || url.isEmpty()) return@forEach
                    
                    // Slika
                    val imageUrl = element.select("img").first()?.attr("abs:src") ?: ""
                    
                    // Datum
                    val date = element.select(".post-date, time, .entry-date, [class*=date]")
                        .first()?.text()?.trim() ?: ""
                    
                    // Kategorija
                    val category = element.select("a[rel=category], .category a, .post-categories a")
                        .joinToString(", ") { it.text() }
                    
                    // Excerpt
                    val excerpt = element.select("p, .entry-summary, .excerpt")
                        .first()?.text()?.trim() ?: ""
                    
                    articles.add(
                        Article(
                            title = title,
                            url = url,
                            imageUrl = imageUrl,
                            date = date,
                            category = category,
                            excerpt = excerpt
                        )
                    )
                } catch (e: Exception) {
                    // Preskoči problematične članke
                }
            }
            
            articles
        } catch (e: IOException) {
            throw Exception("Greška pri preuzimanju vesti. Proverite internet konekciju.")
        } catch (e: Exception) {
            throw Exception("Greška pri obradi vesti: ${e.message}")
        }
    }
    
    suspend fun fetchArticleContent(url: String): String = withContext(Dispatchers.IO) {
        try {
            val doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .timeout(10000)
                .get()
            
            // Pronalazi sadržaj članka
            val content = doc.select(".entry-content, .post-content, article p")
                .text()
            
            content
        } catch (e: Exception) {
            "Greška pri učitavanju sadržaja članka."
        }
    }
}
